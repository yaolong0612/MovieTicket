package com.sportsbet.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportsbet.domain.TicketType;
import com.sportsbet.infrastructure.rest.ErrorResponse;
import com.sportsbet.infrastructure.rest.ResultCode;
import com.sportsbet.interfaces.dto.CustomerDTO;
import com.sportsbet.interfaces.dto.TicketDTO;
import com.sportsbet.interfaces.dto.TransactionRequest;
import com.sportsbet.interfaces.dto.TransactionResponse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TransactionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("validParams")
    public void testGetTicketsValidRequestShouldReturn200(TransactionRequest transactionRequest,
        TransactionResponse expectedTransactionResponse) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String url = "/v1/transactions";
        MvcResult mvcResult = mockMvc.perform(
                post(url)
                    .content(objectMapper.writeValueAsString(transactionRequest))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
        TransactionResponse actualTransactionResponse = objectMapper.readValue(
            mvcResult.getResponse().getContentAsString(), TransactionResponse.class);

        assertEquals(expectedTransactionResponse, actualTransactionResponse);
    }

    @ParameterizedTest
    @MethodSource("invalidParams")
    public void testGetTicketsValidRequestShouldReturn400(TransactionRequest transactionRequest,
        ErrorResponse expectedErrorResponse) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String url = "/v1/transactions";
        MvcResult mvcResult = mockMvc.perform(
                post(url)
                    .content(objectMapper.writeValueAsString(transactionRequest))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andReturn();
        ErrorResponse actualErrorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
            ErrorResponse.class);

        assertEquals(expectedErrorResponse.getMessage(), actualErrorResponse.getMessage());
    }

    private static Stream<Arguments> invalidParams() {
        return Stream.of(
            Arguments.of(
                TransactionRequest.builder().transactionId(1L).customers(
                    List.of(
                        CustomerDTO.builder().name("Billy Kidd").age(-1).build()
                    )
                ).build(),
                ErrorResponse.builder().message(ResultCode.PARAM_VALID_ERROR.getMsg())
                    .build()
            ),
            Arguments.of(
                TransactionRequest.builder().transactionId(-11L).customers(
                    List.of(
                        CustomerDTO.builder().name("Billy Kidd").age(1).build()
                    )
                ).build(),
                ErrorResponse.builder().message(ResultCode.PARAM_VALID_ERROR.getMsg())
                    .build()
            ),
            Arguments.of(
                TransactionRequest.builder().transactionId(1L).customers(new ArrayList<>()).build(),
                ErrorResponse.builder().message(ResultCode.PARAM_VALID_ERROR.getMsg())
                    .build()
            ),
            Arguments.of(
                TransactionRequest.builder().transactionId(1L).build(),
                ErrorResponse.builder().message(ResultCode.PARAM_VALID_ERROR.getMsg())
                    .build()
            ),
            Arguments.of(
                TransactionRequest.builder().customers(
                    List.of(
                        CustomerDTO.builder().name("Billy Kidd").age(1).build()
                    )
                ).build(),
                ErrorResponse.builder().message(ResultCode.PARAM_VALID_ERROR.getMsg())
                    .build()
            ),
            Arguments.of(
                TransactionRequest.builder().transactionId(1L).customers(
                    List.of(
                        CustomerDTO.builder().name("Billy Kidd").build()
                    )
                ).build(),
                ErrorResponse.builder().message(ResultCode.PARAM_VALID_ERROR.getMsg())
                    .build()
            )
        );
    }

    private static Stream<Arguments> validParams() {
        return Stream.of(
            Arguments.of(
                TransactionRequest.builder().transactionId(1L).customers(
                    List.of(
                        CustomerDTO.builder().name("Billy Kidd").age(70).build()
                    )
                ).build(),
                TransactionResponse.builder().transactionId(1L).tickets(
                    List.of(
                        TicketDTO.builder().ticketType(TicketType.SENIOR).quantity(1).totalCost(17.50).build()
                    )
                ).totalCost(17.50).build()
            ),
            Arguments.of(
                TransactionRequest.builder().transactionId(1L).customers(
                    List.of(
                        CustomerDTO.builder().name("Billy Kidd").age(36).build()
                    )
                ).build(),
                TransactionResponse.builder().transactionId(1L).tickets(
                    List.of(
                        TicketDTO.builder().ticketType(TicketType.ADULT).quantity(1).totalCost(25.00).build()
                    )
                ).totalCost(25.00).build()
            ),
            Arguments.of(
                TransactionRequest.builder().transactionId(1L).customers(
                    List.of(
                        CustomerDTO.builder().name("Jane Doe").age(5).build()
                    )
                ).build(),
                TransactionResponse.builder().transactionId(1L).tickets(
                    List.of(
                        TicketDTO.builder().ticketType(TicketType.CHILDREN).quantity(1).totalCost(5.00).build()
                    )
                ).totalCost(5.00).build()
            ),
            Arguments.of(
                TransactionRequest.builder().transactionId(1l).customers(
                    List.of(CustomerDTO.builder().name("John Smith").age(70).build(),
                        CustomerDTO.builder().name("Jane Doe").age(5).build(),
                        CustomerDTO.builder().name("Bob Doe").age(6).build()
                    )
                ).build(),
                TransactionResponse.builder().transactionId(1l).tickets(
                    List.of(TicketDTO.builder().ticketType(TicketType.CHILDREN).quantity(2).totalCost(10.00).build(),
                        TicketDTO.builder().ticketType(TicketType.SENIOR).quantity(1).totalCost(17.50).build())
                ).totalCost(27.50).build()
            ),
            Arguments.of(
                TransactionRequest.builder().transactionId(2L).customers(
                    List.of(
                        CustomerDTO.builder().name("Billy Kidd").age(36).build(),
                        CustomerDTO.builder().name("Zoe Daniels").age(3).build(),
                        CustomerDTO.builder().name("George White").age(8).build(),
                        CustomerDTO.builder().name("Tommy Anderson").age(9).build(),
                        CustomerDTO.builder().name("Joe Smith").age(17).build()
                    )
                ).build(),
                TransactionResponse.builder().transactionId(2L).tickets(
                    List.of(
                        TicketDTO.builder().ticketType(TicketType.ADULT).quantity(1).totalCost(25.00).build(),
                        TicketDTO.builder().ticketType(TicketType.CHILDREN).quantity(3).totalCost(11.25).build(),
                        TicketDTO.builder().ticketType(TicketType.TEEN).quantity(1).totalCost(12).build()
                    )
                ).totalCost(48.25).build()
            ),
            Arguments.of(
                TransactionRequest.builder().transactionId(2L).customers(
                    List.of(
                        CustomerDTO.builder().name("Billy Kidd").age(36).build(),
                        CustomerDTO.builder().name("Zoe Daniels").age(3).build(),
                        CustomerDTO.builder().name("George White").age(8).build(),
                        CustomerDTO.builder().name("Tommy Anderson").age(9).build(),
                        CustomerDTO.builder().name("James Anderson").age(9).build(),
                        CustomerDTO.builder().name("Joe Smith").age(17).build()
                    )
                ).build(),
                TransactionResponse.builder().transactionId(2L).tickets(
                    List.of(
                        TicketDTO.builder().ticketType(TicketType.ADULT).quantity(1).totalCost(25.00).build(),
                        TicketDTO.builder().ticketType(TicketType.CHILDREN).quantity(4).totalCost(15).build(),
                        TicketDTO.builder().ticketType(TicketType.TEEN).quantity(1).totalCost(12).build()
                    )
                ).totalCost(52).build()
            ),
            Arguments.of(
                TransactionRequest.builder().transactionId(3L).customers(
                    List.of(
                        CustomerDTO.builder().name("Jesse James").age(36).build(),
                        CustomerDTO.builder().name("Daniel Anderson").age(95).build(),
                        CustomerDTO.builder().name("Mary Jones").age(15).build(),
                        CustomerDTO.builder().name("Michelle Parker").age(10).build()
                    )
                ).build(),
                TransactionResponse.builder().transactionId(3L).tickets(
                    List.of(
                        TicketDTO.builder().ticketType(TicketType.ADULT).quantity(1).totalCost(25.00).build(),
                        TicketDTO.builder().ticketType(TicketType.CHILDREN).quantity(1).totalCost(5.00).build(),
                        TicketDTO.builder().ticketType(TicketType.SENIOR).quantity(1).totalCost(17.50).build(),
                        TicketDTO.builder().ticketType(TicketType.TEEN).quantity(1).totalCost(12.00).build()
                    )
                ).totalCost(59.50).build()
            )
        );
    }

}

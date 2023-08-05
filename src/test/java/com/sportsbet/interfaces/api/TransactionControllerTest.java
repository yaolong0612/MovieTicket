package com.sportsbet.interfaces.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportsbet.application.TransactionService;
import com.sportsbet.domain.Transaction;
import com.sportsbet.infrastructure.rest.ErrorResponse;
import com.sportsbet.infrastructure.rest.GlobalExceptionTranslator;
import com.sportsbet.infrastructure.rest.ResultCode;
import com.sportsbet.infrastructure.utils.EntityConverter;
import com.sportsbet.interfaces.dto.CustomerDTO;
import com.sportsbet.interfaces.dto.TransactionRequest;
import com.sportsbet.interfaces.dto.TransactionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private EntityConverter converter;
    @MockBean
    private TransactionService transactionService;
    @MockBean
    private TransactionController transactionController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController)
            .setControllerAdvice(new GlobalExceptionTranslator())
            .build();
    }

    @Test
    public void testGetTicketsValidRequestShouldReturn200() throws Exception {
        //given
        var transactionId = 1L;
        var customerDTOS = new ArrayList<CustomerDTO>();
        customerDTOS.add(CustomerDTO.builder().name("Billy Kidd").age(1).build());
        TransactionRequest request = TransactionRequest.builder().transactionId(transactionId).customers(customerDTOS)
            .build();

        //when
        when(converter.convert(any(TransactionRequest.class), Transaction.class)).thenReturn(new Transaction());
        when(transactionService.purchaseTickets(ArgumentMatchers.any())).thenReturn(new Transaction());
        when(converter.convert(any(Transaction.class), TransactionResponse.class)).thenReturn(
            new TransactionResponse());

        //should
        mockMvc.perform(
            post("/v1/transactions").contentType(MediaType.APPLICATION_JSON).content(toJSONString(request))
        ).andExpect(status().isOk());
    }

    @Test
    public void testGetTicketsValidRequestShouldReturn400WithInvalidAge() throws Exception {
        //given
        var transactionId = 1L;
        var customerDTOS = new ArrayList<CustomerDTO>();
        customerDTOS.add(CustomerDTO.builder().name("Billy Kidd").age(-1).build());
        TransactionRequest request = TransactionRequest.builder().transactionId(transactionId).customers(customerDTOS)
            .build();

        //when
        when(converter.convert(any(TransactionRequest.class), Transaction.class)).thenReturn(new Transaction());
        when(transactionService.purchaseTickets(ArgumentMatchers.any())).thenReturn(new Transaction());
        when(converter.convert(any(Transaction.class), TransactionResponse.class)).thenReturn(
            new TransactionResponse());

        //should
        MvcResult mvcResult = mockMvc.perform(
            post("/v1/transactions").contentType(MediaType.APPLICATION_JSON).content(toJSONString(request))
        ).andExpect(status().isBadRequest()).andReturn();
        ErrorResponse actualErrorResponse = jsonToObject(mvcResult.getResponse().getContentAsString(),
            ErrorResponse.class);
        ErrorResponse expectedErrorResponse = ErrorResponse.builder()
            .message(ResultCode.PARAM_VALID_ERROR.getMsg())
            .build();
        assertEquals(expectedErrorResponse.getMessage(), actualErrorResponse.getMessage());
    }

    @Test
    public void testGetTicketsValidRequestShouldReturn400WithInvalidTransactionID() throws Exception {
        //given
        var transactionId = -11L;
        var customerDTOS = new ArrayList<CustomerDTO>();
        customerDTOS.add(CustomerDTO.builder().name("Billy Kidd").age(1).build());
        TransactionRequest request = TransactionRequest.builder().transactionId(transactionId).customers(customerDTOS)
            .build();

        //when
        when(converter.convert(any(TransactionRequest.class), Transaction.class)).thenReturn(new Transaction());
        when(transactionService.purchaseTickets(ArgumentMatchers.any())).thenReturn(new Transaction());
        when(converter.convert(any(Transaction.class), TransactionResponse.class)).thenReturn(
            new TransactionResponse());

        //should
        MvcResult mvcResult = mockMvc.perform(
            post("/v1/transactions").contentType(MediaType.APPLICATION_JSON).content(toJSONString(request))
        ).andExpect(status().isBadRequest()).andReturn();
        ErrorResponse actualErrorResponse = jsonToObject(mvcResult.getResponse().getContentAsString(),
            ErrorResponse.class);
        ErrorResponse expectedErrorResponse = ErrorResponse.builder()
            .message(ResultCode.PARAM_VALID_ERROR.getMsg())
            .build();
        assertEquals(expectedErrorResponse.getMessage(), actualErrorResponse.getMessage());
    }

    @Test
    public void testGetTicketsValidRequestShouldReturn400WithEmptyConsumerList() throws Exception {
        //given
        var transactionId = 1L;
        var customerDTOS = new ArrayList<CustomerDTO>();
        TransactionRequest request = TransactionRequest.builder().transactionId(transactionId).customers(customerDTOS)
            .build();

        //when
        when(converter.convert(any(TransactionRequest.class), Transaction.class)).thenReturn(new Transaction());
        when(transactionService.purchaseTickets(ArgumentMatchers.any())).thenReturn(new Transaction());
        when(converter.convert(any(Transaction.class), TransactionResponse.class)).thenReturn(
            new TransactionResponse());

        //should
        MvcResult mvcResult = mockMvc.perform(
            post("/v1/transactions").contentType(MediaType.APPLICATION_JSON).content(toJSONString(request))
        ).andExpect(status().isBadRequest()).andReturn();
        ErrorResponse actualErrorResponse = jsonToObject(mvcResult.getResponse().getContentAsString(),
            ErrorResponse.class);
        ErrorResponse expectedErrorResponse = ErrorResponse.builder()
            .message(ResultCode.PARAM_VALID_ERROR.getMsg())
            .build();
        assertEquals(expectedErrorResponse.getMessage(), actualErrorResponse.getMessage());
    }

    @Test
    public void testGetTicketsValidRequestShouldReturn400WithNullConsumerList() throws Exception {
        //given
        var transactionId = 1L;
        TransactionRequest request = TransactionRequest.builder().transactionId(transactionId).build();

        //when
        when(converter.convert(any(TransactionRequest.class), Transaction.class)).thenReturn(new Transaction());
        when(transactionService.purchaseTickets(ArgumentMatchers.any())).thenReturn(new Transaction());
        when(converter.convert(any(Transaction.class), TransactionResponse.class)).thenReturn(
            new TransactionResponse());

        //should
        MvcResult mvcResult = mockMvc.perform(
            post("/v1/transactions").contentType(MediaType.APPLICATION_JSON).content(toJSONString(request))
        ).andExpect(status().isBadRequest()).andReturn();
        ErrorResponse actualErrorResponse = jsonToObject(mvcResult.getResponse().getContentAsString(),
            ErrorResponse.class);
        ErrorResponse expectedErrorResponse = ErrorResponse.builder()
            .message(ResultCode.PARAM_VALID_ERROR.getMsg())
            .build();
        assertEquals(expectedErrorResponse.getMessage(), actualErrorResponse.getMessage());
    }

    @Test
    public void testGetTicketsValidRequestShouldReturn400WithEmptyTransactionID() throws Exception {
        //given
        var customerDTOS = new ArrayList<CustomerDTO>();
        customerDTOS.add(CustomerDTO.builder().name("Billy Kidd").age(1).build());
        TransactionRequest request = TransactionRequest.builder().customers(customerDTOS).build();

        //when
        when(converter.convert(any(TransactionRequest.class), Transaction.class)).thenReturn(new Transaction());
        when(transactionService.purchaseTickets(ArgumentMatchers.any())).thenReturn(new Transaction());
        when(converter.convert(any(Transaction.class), TransactionResponse.class)).thenReturn(
            new TransactionResponse());

        //should
        MvcResult mvcResult = mockMvc.perform(
            post("/v1/transactions").contentType(MediaType.APPLICATION_JSON).content(toJSONString(request))
        ).andExpect(status().isBadRequest()).andReturn();
        ErrorResponse actualErrorResponse = jsonToObject(mvcResult.getResponse().getContentAsString(),
            ErrorResponse.class);
        ErrorResponse expectedErrorResponse = ErrorResponse.builder()
            .message(ResultCode.PARAM_VALID_ERROR.getMsg())
            .build();
        assertEquals(expectedErrorResponse.getMessage(), actualErrorResponse.getMessage());
    }

    @Test
    public void testGetTicketsValidRequestShouldReturn400WithEmptyCustomerAge() throws Exception {
        //given
        var transactionId = 1L;
        var customerDTOS = new ArrayList<CustomerDTO>();
        customerDTOS.add(CustomerDTO.builder().name("Billy Kidd").build());
        TransactionRequest request = TransactionRequest.builder().transactionId(transactionId).customers(customerDTOS)
            .build();

        //when
        when(converter.convert(any(TransactionRequest.class), Transaction.class)).thenReturn(new Transaction());
        when(transactionService.purchaseTickets(ArgumentMatchers.any())).thenReturn(new Transaction());
        when(converter.convert(any(Transaction.class), TransactionResponse.class)).thenReturn(
            new TransactionResponse());

        //should
        MvcResult mvcResult = mockMvc.perform(
            post("/v1/transactions").contentType(MediaType.APPLICATION_JSON).content(toJSONString(request))
        ).andExpect(status().isBadRequest()).andReturn();
        ErrorResponse actualErrorResponse = jsonToObject(mvcResult.getResponse().getContentAsString(),
            ErrorResponse.class);
        ErrorResponse expectedErrorResponse = ErrorResponse.builder()
            .message(ResultCode.PARAM_VALID_ERROR.getMsg())
            .build();
        assertEquals(expectedErrorResponse.getMessage(), actualErrorResponse.getMessage());
    }

    private static <T> T jsonToObject(String json, Class<T> objectType) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, objectType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toJSONString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

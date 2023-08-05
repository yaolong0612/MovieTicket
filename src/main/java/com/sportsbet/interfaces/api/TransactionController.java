package com.sportsbet.interfaces.api;

import com.sportsbet.application.TransactionService;
import com.sportsbet.domain.Transaction;
import com.sportsbet.infrastructure.rest.ErrorResponse;
import com.sportsbet.infrastructure.utils.EntityConverter;
import com.sportsbet.interfaces.dto.TransactionRequest;
import com.sportsbet.interfaces.dto.TransactionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
    name = "Transaction Controller",
    description = "Transaction Operations"
)
@RestController
@RequestMapping("/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final EntityConverter converter;

    @Operation(summary = "Get transaction with tickets and total cost by transaction details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operation is Successful",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = TransactionResponse.class))),
        @ApiResponse(responseCode = "400", description = "Parameter Validation Error | Message Can't be Read | Parameter Type Mismatch",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping
    public ResponseEntity<TransactionResponse> getTickets(@Valid @RequestBody TransactionRequest transactionRequest) {
        var requestTransaction = converter.convert(transactionRequest, Transaction.class);
        var responseTransaction = transactionService.purchaseTickets(requestTransaction);
        return ResponseEntity.ok(converter.convert(responseTransaction, TransactionResponse.class));
    }

}

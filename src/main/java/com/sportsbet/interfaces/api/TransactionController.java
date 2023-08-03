package com.sportsbet.interfaces.api;

import com.sportsbet.application.TransactionService;
import com.sportsbet.domain.Transaction;
import com.sportsbet.infrastructure.utils.EntityConverter;
import com.sportsbet.interfaces.dto.TransactionRequest;
import com.sportsbet.interfaces.dto.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final EntityConverter converter;

    @PostMapping
    public ResponseEntity<TransactionResponse> getTickets(@RequestBody TransactionRequest transactionRequest) {
        return null;
    }


}

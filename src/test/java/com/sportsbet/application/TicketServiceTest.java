package com.sportsbet.application;

import com.sportsbet.domain.Ticket;
import com.sportsbet.domain.TicketType;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @InjectMocks
    private TicketType ticketType;

}

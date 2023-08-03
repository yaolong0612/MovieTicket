package com.sportsbet.application;

import com.sportsbet.domain.TicketType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Test
    void testDetermineAdultTicketTypeForAge18() {
        //given
        int age = 18;

        //when
        TicketType actualTicketType = ticketService.determineTicketTypeByAge(age);

        //should
        assertEquals(TicketType.ADULT, actualTicketType);
    }

    @Test
    void testDetermineAdultTicketTypeForAgeGreaterThen18ButLessThan65() {
        //given
        int age = generateRandomAge(18, 65);

        //when
        TicketType actualTicketType = ticketService.determineTicketTypeByAge(age);

        //should
        assertEquals(TicketType.ADULT, actualTicketType);
    }

    @Test
    void testDetermineAdultTicketTypeForAge64() {
        //given
        int age = 64;

        //when
        TicketType actualTicketType = ticketService.determineTicketTypeByAge(age);

        //should
        assertEquals(TicketType.ADULT, actualTicketType);
    }

    @Test
    void testDetermineAdultTicketTypeForAge65() {
        //given
        int age = 65;

        //when
        TicketType actualTicketType = ticketService.determineTicketTypeByAge(age);

        //should
        assertEquals(TicketType.SENIOR, actualTicketType);
    }

    @Test
    void testDetermineAdultTicketTypeForAgeGreaterThan65() {
        //given
        int age = generateRandomAge(66, 110);

        //when
        TicketType actualTicketType = ticketService.determineTicketTypeByAge(age);

        //should
        assertEquals(TicketType.SENIOR, actualTicketType);
    }

    @Test
    void testDetermineAdultTicketTypeForAge11() {
        //given
        int age = 11;

        //when
        TicketType actualTicketType = ticketService.determineTicketTypeByAge(age);

        //should
        assertEquals(TicketType.TEEN, actualTicketType);
    }

    @Test
    void testDetermineAdultTicketTypeForAge17() {
        //given
        int age = 17;

        //when
        TicketType actualTicketType = ticketService.determineTicketTypeByAge(age);

        //should
        assertEquals(TicketType.TEEN, actualTicketType);
    }

    @Test
    void testDetermineAdultTicketTypeForAgeGreaterThan11ButLessThan18() {
        //given
        int age = generateRandomAge(12, 18);

        //when
        TicketType actualTicketType = ticketService.determineTicketTypeByAge(age);

        //should
        assertEquals(TicketType.TEEN, actualTicketType);
    }

    @Test
    void testDetermineAdultTicketTypeForAge10() {
        //given
        int age = 10;

        //when
        TicketType actualTicketType = ticketService.determineTicketTypeByAge(age);

        //should
        assertEquals(TicketType.CHILDREN, actualTicketType);
    }

    @Test
    void testDetermineAdultTicketTypeForAgeLessThan10() {
        //given
        int age = generateRandomAge(0, 10);

        //when
        TicketType actualTicketType = ticketService.determineTicketTypeByAge(age);

        //should
        assertEquals(TicketType.CHILDREN, actualTicketType);
    }

    // >=minAge and <maxAge
    public static int generateRandomAge(int minAge, int maxAge) {
        Random random = new Random();
        return random.nextInt(maxAge - minAge) + minAge;
    }

}

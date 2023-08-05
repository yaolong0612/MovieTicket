package com.sportsbet;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class BaseTest {

    protected BaseTest() {
    }

    // >=min and <max
    protected int generateRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    protected String randomString() {
        return UUID.randomUUID().toString();
    }

}

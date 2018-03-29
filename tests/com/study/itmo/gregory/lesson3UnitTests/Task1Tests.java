package com.study.itmo.gregory.lesson3UnitTests;

import org.junit.jupiter.api.Test;

import static com.study.itmo.gregory.lesson3.Task1.getMostLongString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Tests {

    @Test
    public void getMostLongString_MatchesExpected_ReturnsTrue(){

        String actual;
        String expected = "foo123";
        String [] array = {"foo", "ba", "az","foo123", "l", "ddd" };

        actual = getMostLongString(array);

        assertEquals(actual, expected);

    }

}

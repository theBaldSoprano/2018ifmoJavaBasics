package com.study.itmo.gregory.lesson1;

import org.junit.jupiter.api.Test;

import static com.study.itmo.gregory.lesson1.Task6.deleteFirstN;
import static com.study.itmo.gregory.lesson1.Task6.deleteNFromArray;
import static com.study.itmo.gregory.lesson1.Task6.hasMatches;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Task6Tests {

    @Test
    public void HasMatches_GoodMatch_ReturnsTrue() {

        int[] array = {5, 5, 5, 7, 7, 4, 1, 8, 5566, 3, 4, 8};
        int intToMatch = 5;
        int EXPECTED = 3;

        int actual = hasMatches(array, intToMatch);

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void DeleteFirstN_HasNoFirstGivenElement_ReturnsTrue() {

        int[] array = {3, 6, 5, 33, 7};
        int n = 7;

        array = deleteFirstN(array, n);
        int result = hasMatches(array, n);

        assertEquals(result, 0);

    }

    @Test
    public void DeleteNFromAArray_WillHaveNoGivenElements_ReturnsTrue() {

        int[] array = {3, 6, 5, 33, 7,};
        int n = 33;

        array = deleteNFromArray(array, n);

        assertEquals(0, hasMatches(array, n));

    }
}

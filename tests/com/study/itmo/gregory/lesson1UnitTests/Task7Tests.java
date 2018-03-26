package com.study.itmo.gregory.lesson1UnitTests;

import org.junit.jupiter.api.Test;

import static com.study.itmo.gregory.lesson1.Task7.getFirstUniqueElement;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task7Tests {

    @Test
    public void GetFirstUniqueElement_WithGivenArray_Returns5() {

        int[] array = {1, 5, 1, 6, 2, 6};
        int EXPECTED = 5;

        int actual = getFirstUniqueElement(array);

        assertEquals(actual, EXPECTED);

    }

}

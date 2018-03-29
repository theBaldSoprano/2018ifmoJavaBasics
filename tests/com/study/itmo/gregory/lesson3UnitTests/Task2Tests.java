package com.study.itmo.gregory.lesson3UnitTests;

import org.junit.jupiter.api.Test;

import static com.study.itmo.gregory.lesson3.Task2.isPalindrome;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Tests {

    @Test
    public void IsPalindrome_OddPalindrome_ReturnsTrue(){

        String palindrome = "sapas";

        boolean result = isPalindrome(palindrome);

        assertTrue(result);
    }

    @Test
    public void IsPalindrome_EvenPalindrome_ReturnsTrue(){

        String palindrome = "acabbaca";

        boolean result = isPalindrome(palindrome);

        assertTrue(result);
    }
}

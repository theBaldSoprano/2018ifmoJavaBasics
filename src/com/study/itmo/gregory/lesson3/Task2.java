package com.study.itmo.gregory.lesson3;

public class Task2 {

    public static boolean isPalindrome(String s) {

        String start;
        String end;

        //when length = 1
        if (s.length() == 1) return true;

        //when length = 2
        if (s.length() == 2 && s.charAt(0) == s.charAt(1)) return true;
        else if (s.length() == 2 && s.charAt(0) != s.charAt(1)) return false;

        //when length > 2
        if (s.length() % 2 != 0) {
            int centerIndex = (s.length() - 1) / 2;
            start = s.substring(0, centerIndex);
            end = s.substring(centerIndex + 1);
        }else {
            int center = s.length() / 2;
            start = s.substring(0, center);
            end = s.substring(center);
        }

        end = new StringBuilder(end).reverse().toString();

        if (start.equals(end)) return true;
        else return false;

    }


}

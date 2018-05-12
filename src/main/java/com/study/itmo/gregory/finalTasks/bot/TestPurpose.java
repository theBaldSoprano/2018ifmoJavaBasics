package com.study.itmo.gregory.finalTasks.bot;

public class TestPurpose {
    public static void main(String[] args) {
        String test = "WSuggestionMoscow";
        System.out.println(test);
        System.out.println("length before: " + test.length());
        test = test.replaceFirst("WSuggestion", "");
        System.out.println(test);
        System.out.println("length before: " + test.length());

    }
}

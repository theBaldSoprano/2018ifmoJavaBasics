package com.study.itmo.gregory.finalTasks.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Solution {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        // Create the TelegramBotsApi object to register your bots
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot( new MyTelegramBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}

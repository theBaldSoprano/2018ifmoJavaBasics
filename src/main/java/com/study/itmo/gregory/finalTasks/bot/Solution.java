package com.study.itmo.gregory.finalTasks.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

public class Solution {
    public static void main(String[] args) throws IOException, InterruptedException {

        ApiContextInitializer.init();
        // Create the TelegramBotsApi object to register your bots
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new TestInlineBot());
            //botsApi.registerBot( new BaldWeatherBot());
            //botsApi.registerBot( new CommandBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
        System.out.println("bot started");
    }
}

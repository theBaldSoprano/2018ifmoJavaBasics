package com.study.itmo.gregory.finalTasks.bot;

import com.study.itmo.gregory.finalTasks.bot.sqlbottool.SQLiteBotTool;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.ArrayList;

public class BaldWeatherBot extends TelegramLongPollingBot {
    SQLiteBotTool tool;

    public BaldWeatherBot() throws SQLException {
        this.tool = new SQLiteBotTool("C:\\botdb\\botDB");
        tool.createUsersTable();
        ArrayList<Long> allUsers = tool.getAllUsers();
        if (!allUsers.contains(888L)) {
            tool.addUser(888L);
        }
        if (!allUsers.contains(777L)) {
            tool.addUser(777L);
        }
        if (!allUsers.contains(555L)) {
            tool.addUser(555L);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            Long chatId = update.getMessage().getChatId();
            ArrayList<Long> allUsers = new ArrayList<>();
            try {
                allUsers.addAll(tool.getAllUsers());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (message.getText().equals("/start")) {
                SendMessage output = new SendMessage();
                output.setText("hi there neu user!!");
                output.setChatId(chatId);
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                ArrayList<KeyboardRow> replyKeyboard = new ArrayList<>();
                KeyboardRow row = new KeyboardRow();
                if (allUsers.contains(chatId)) {
                    row.add("getWeather");
                    row.add("getSubscrbrs");
                    row.add("UnSubscribe me");
                } else {
                    row.add("getWeather");
                    row.add("getSubscrbrs");
                    row.add("Subscribe me");
                }
                replyKeyboard.add(row);
                keyboardMarkup.setKeyboard(replyKeyboard);
                output.setReplyMarkup(keyboardMarkup);
                try {
                    execute(output);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            } else if (message.getText().equals("getSubscrbrs")) {
                try {
                    execute(
                            new SendMessage()
                                    .setChatId(chatId)
                                    .setText(String
                                            .valueOf(allUsers.size())));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (message.getText().equals("Subscribe me")) {
                if (!allUsers.contains(chatId)) {
                    try {
                        tool.addUser(chatId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                SendMessage output = new SendMessage();
                output.setText("we subscribed you");
                output.setChatId(chatId);
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                ArrayList<KeyboardRow> replyKeyboard = new ArrayList<>();
                KeyboardRow row = new KeyboardRow();
                row.add("getWeather");
                row.add("getSubscrbrs");
                row.add("UnSubscribe me");
                replyKeyboard.add(row);
                keyboardMarkup.setKeyboard(replyKeyboard);
                output.setReplyMarkup(keyboardMarkup);
                try {
                    execute(output);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }else if (message.getText().equals("UnSubscribe me")){
                if (allUsers.contains(chatId)) {
                    try {
                        tool.deleteUser(chatId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                SendMessage output = new SendMessage();
                output.setText("we deleted you");
                output.setChatId(chatId);
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                ArrayList<KeyboardRow> replyKeyboard = new ArrayList<>();
                KeyboardRow row = new KeyboardRow();
                row.add("getWeather");
                row.add("getSubscrbrs");
                row.add("Subscribe me");
                replyKeyboard.add(row);
                keyboardMarkup.setKeyboard(replyKeyboard);
                output.setReplyMarkup(keyboardMarkup);
                try {
                    execute(output);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }else if (message.getText().equals("getWeather")){
                SendMessage output = new SendMessage();
                output.setText("send me city name");
                output.setChatId(chatId);
                try {
                    execute(output);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return Creditals.USERNAME;
    }

    @Override
    public String getBotToken() {
        return Creditals.TOKEN;
    }
}
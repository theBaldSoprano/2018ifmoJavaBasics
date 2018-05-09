package com.study.itmo.gregory.finalTasks.bot;

import com.study.itmo.gregory.finalTasks.bot.owmcitygetter.City;
import com.study.itmo.gregory.finalTasks.bot.sqlbottool.SQLiteBotTool;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import static com.study.itmo.gregory.finalTasks.bot.owmcitygetter.OWMTools.getCities;
import static com.study.itmo.gregory.finalTasks.bot.owmcitygetter.OWMTools.getUnzippedJsonCitiesFile;
import static com.study.itmo.gregory.finalTasks.bot.owmcitygetter.OWMTools.pullCitiesFile;
import static com.study.itmo.gregory.finalTasks.bot.stringcomparator.StringComparator.getSuggestionMap;

public class BaldWeatherBot extends TelegramLongPollingBot {
    Update previousUpdate;
    SQLiteBotTool tool;
    public static List<City> cities;
    static {
        try {
            System.out.println("we now will pull cities");
            pullCitiesFile();
            System.out.println("pulled cities OK@))200");
            cities = getCities(getUnzippedJsonCitiesFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BaldWeatherBot() throws SQLException {
        this.tool = new SQLiteBotTool("C:\\weatherbot\\botDB");
        tool.createUsersTable();
        HashMap<Long, String> allUsers = tool.getAllUsers();
        if (!allUsers.containsKey(888L)) {
            tool.addUser(888L, "cti");
        }
        if (!allUsers.containsKey(777L)) {
            tool.addUser(777L, "cti");
        }
        if (!allUsers.containsKey(555L)) {
            tool.addUser(555L, "cti");
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            Long chatId = update.getMessage().getChatId();
            HashMap<Long, String> allUsers = new HashMap<>();
            try {
                allUsers.putAll(tool.getAllUsers());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //*********************/START*************************
            if (message.getText().equals("/start")) {
                SendMessage output = new SendMessage();
                output.setText("hi there neu user!!");
                output.setChatId(chatId);
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                ArrayList<KeyboardRow> replyKeyboard = new ArrayList<>();
                KeyboardRow row = new KeyboardRow();
                if (allUsers.containsKey(chatId)) {
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
            //*********************/GET SUBSCRIBERS*************************
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
            //*********************/SUBSCRIBE*************************
            } else if (message.getText().equals("Subscribe me")) {
                if (!allUsers.containsKey(chatId)) {
                    try {
                        tool.addUser(chatId, message.getText());
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
            //*********************/UN SUBSCRIBE*************************
            }else if (message.getText().equals("UnSubscribe me")){
                if (allUsers.containsKey(chatId)) {
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
            //*********************/GET WEATHER*************************
            }else if (message.getText().equals("getWeather")){
                SendMessage output = new SendMessage();
                output.setText("send me city name");
                output.setChatId(chatId);
                try {
                    execute(output);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            //*********************/GET WEATHER RESPONSE*************************
            }else if (previousUpdate.getMessage().getText().equals("getWeather")){
                TreeSet<City> sugg = getSuggestionMap(cities, new City(update.getMessage().getText()));
                SendMessage output = new SendMessage();
                StringBuilder sb = new StringBuilder();
                sb.append("Did you mean?");
                for (City c : sugg) sb.append(String.format("%n%s", c.getName()));
                output.setText(sb.toString());
                //output.setText("yes");
                output.setChatId(chatId);
                try {
                    execute(output);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
        previousUpdate = update;
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
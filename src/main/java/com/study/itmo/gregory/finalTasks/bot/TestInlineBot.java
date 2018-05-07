package com.study.itmo.gregory.finalTasks.bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class TestInlineBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        //*******MESSAGE STUFF********
        if (update.hasMessage() && update.getMessage().hasText()){
            Message message = update.getMessage();
            Long chatId = message.getChatId();
            SendMessage output = new SendMessage();
            //*********************/START*************************
            if (message.getText().equals("/start")){
                output.setChatId(chatId).setText(String.format("Heeeeeelloooo children%nThis is Three Dod" +
                                "%nAAAAWWWUUUUUUUU%nAnd your listening to the Galaxy News Radio%n" +
                                "............%n" +
                                "So now we're switching to the weather broadcast%n" +
                                "What u want kid?"));
                row.add(new InlineKeyboardButton().setText("Get me weather").setCallbackData("getWeather"));
                rows.add(row);
                row = new ArrayList<>();
                row.add(new InlineKeyboardButton()
                        .setText("Subscribe me on the Forecast")
                        .setCallbackData("subscribe"));
                rows.add(row);
                keyboard.setKeyboard(rows);
                output.setReplyMarkup(keyboard);
                try {
                    execute(output);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        //*******CALLBACK STUFF********
        }else if (update.hasCallbackQuery()){
            String callData = update.getCallbackQuery().getData();
            Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            //*********************GET WEATHER*************************
            if (callData.equals("getWeather")){
                EditMessageText output = new EditMessageText()
                        .setChatId(chatId)
                        .setMessageId(messageId)
                        .setText("So send me the city name");
                row.add(new InlineKeyboardButton().setText("Back to main menu").setCallbackData("back"));
                rows.add(row);
                keyboard.setKeyboard(rows);
                output.setReplyMarkup(keyboard);
                try {
                    execute(output);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            //*********************GET BACK*************************
            }else if (callData.equals("back")){
                EditMessageText output = new EditMessageText()
                        .setChatId(chatId)
                        .setMessageId(messageId)
                        .setText("What u want kid?");
                row.add(new InlineKeyboardButton().setText("Get me weather").setCallbackData("getWeather"));
                rows.add(row);
                row = new ArrayList<>();
                row.add(new InlineKeyboardButton()
                        .setText("Subscribe me on the Forecast")
                        .setCallbackData("subscribe"));
                keyboard.setKeyboard(rows);
                output.setReplyMarkup(keyboard);
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

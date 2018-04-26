package com.study.itmo.gregory.finalTasks.bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import static com.study.itmo.gregory.finalTasks.bot.Creditals.TOKEN;
import static com.study.itmo.gregory.finalTasks.bot.Creditals.USERNAME;

public class MyTelegramBot extends TelegramLongPollingBot {

    public MyTelegramBot( ){

    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            //String txt = String.format("fuck you, %s", update.getMessage().getContact().getFirstName());
            SendMessage message = new SendMessage()
                             .setChatId(update.getMessage().getChatId())
                            .setText("fuck you");
            try {
                sendMessage(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}

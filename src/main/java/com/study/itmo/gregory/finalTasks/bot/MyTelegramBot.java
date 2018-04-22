package com.study.itmo.gregory.finalTasks.bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.List;

public class MyTelegramBot extends TelegramLongPollingBot {

    public MyTelegramBot(DefaultBotOptions botOptions){
        super(botOptions);
    }

    @Override
    public String getBotToken() {
        return "580617227:AAFWgCitsLUEpzeC6IDCYP4fkXqX50Jqf5U";
    }

    @Override
    public String getBotUsername() {
        return "theBaldSopranoBot";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            SendMessage message = new SendMessage()
                             .setChatId(update.getMessage().getChatId())
                            .setText(update.getMessage().getText());
            try {
                sendMessage(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}

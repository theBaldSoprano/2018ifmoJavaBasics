package com.study.itmo.gregory.finalTasks.bot;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;

public class CommandBot extends TelegramLongPollingBot{
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            long chatId = update.getMessage().getChatId();
            if (update.getMessage().getText().equals("/pic")){
                SendPhoto msg = new SendPhoto()
                        .setChatId(chatId)
                        .setPhoto("AgADAgAD0qgxG46XSEtxISaP5o5BLz_LRg4ABH-Ml_olkiTolLcBAAEC")
                        .setCaption("bicycle");
                try {
                    sendPhoto(msg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }else if (update.getMessage().getText().equals("/markup")){
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText("keyboard incoming...");
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

                ArrayList<KeyboardRow> replyKeyboard = new ArrayList<>();

                KeyboardRow row = new KeyboardRow();
                row.add(EmojiParser.parseToUnicode(":dizzy_face:"));
                //:dizzy_face:
                row.add("row1btn2");
                replyKeyboard.add(row);
                row = new KeyboardRow();
                row.add("row2btn1");
                row.add("hide");
                replyKeyboard.add(row);

                keyboardMarkup.setKeyboard(replyKeyboard);
                message.setReplyMarkup(keyboardMarkup);

                try{
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }else if (update.getMessage().getText().equals("hide")){
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText("KB hidden")
                        .setReplyMarkup(new ReplyKeyboardRemove());
                try{
                    execute(message);
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

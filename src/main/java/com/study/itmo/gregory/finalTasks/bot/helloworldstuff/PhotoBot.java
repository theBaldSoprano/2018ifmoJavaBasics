package com.study.itmo.gregory.finalTasks.bot.helloworldstuff;

import com.study.itmo.gregory.finalTasks.bot.Creditals;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Comparator;
import java.util.List;

public class PhotoBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasPhoto()){
            Long chatId = update.getMessage().getChatId();
            List<PhotoSize> photo = update.getMessage().getPhoto();

            PhotoSize photoSize = photo.stream()
                                        .sorted(Comparator.comparing(PhotoSize::getFileSize)).findFirst().get();
            String fileId = photoSize.getFileId();
            Integer height = photoSize.getHeight();
            Integer width = photoSize.getWidth();
            String caption = String.format("pic id is: %s%npic height is: %d%npic width is: %d", fileId, height, width);
            SendPhoto sendPhoto = new SendPhoto().setChatId(chatId)
                    .setPhoto(fileId)
                    .setCaption(caption);

            PhotoSize photoSize1 = photo.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed()).findFirst().orElse(null);
            String fileId1 = photoSize1.getFileId();
            Integer height1 = photoSize1.getHeight();
            Integer width1 = photoSize1.getWidth();
            String caption1 = String.format("pic id is: %s%npic height is: %d%npic width is: %d", fileId1, height1, width1);
            SendPhoto sendPhoto1 = new SendPhoto().setChatId(chatId)
                    .setPhoto(fileId1)
                    .setCaption(caption1);
            try {
                sendPhoto(sendPhoto);
                sendPhoto(sendPhoto1);
            } catch (TelegramApiException e) {
                e.printStackTrace();
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

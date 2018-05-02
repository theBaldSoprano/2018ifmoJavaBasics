package com.study.itmo.gregory.finalTasks.bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Contact;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.stickers.Sticker;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import static com.study.itmo.gregory.finalTasks.bot.Creditals.TOKEN;
import static com.study.itmo.gregory.finalTasks.bot.Creditals.USERNAME;

public class TestMethodsTelegramBot extends TelegramLongPollingBot {

    public TestMethodsTelegramBot() {

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
        if (update.hasMessage() && update.getMessage().hasText()) {

            /**
             * MESSAGE methods
             */
            Message m = update.getMessage();
            long chatId = m.getChatId();
            String text = m.getText();
            String authorSignature = m.getAuthorSignature();
            String caption = m.getCaption();
            String connectedWebsite = m.getConnectedWebsite();
            Integer date = m.getDate();
            String mediaGroupId = m.getMediaGroupId();
            //todo message id of who?
            Integer messageId = m.getMessageId();
            SendMessage messageUpdate = new SendMessage()
                    .setChatId(chatId)
                    .setText(String.format("MESSAGE DATA%nchat id:[%d]%ntext[%s]%nauthorSign:[%s]%n" +
                            "caption:[%s]%nwebsite:[%s]%ndate:[%d]%nmediagroupId:[%s]%n" +
                            "messageId:[%d]%n", chatId, text, authorSignature, caption, connectedWebsite, date, mediaGroupId, messageId));


            /**
             * STICKER methods
             */
            Sticker sticker = m.getSticker();

            /**
             * CONTACT methods
             * they are for received contact cards
             */
            /*Contact contact = m.getContact();
            //String firstName1 = contact.getFirstName();
            //String lastName1 = contact.getLastName();
            String phoneNumber = contact.getPhoneNumber();
            Integer userID = contact.getUserID();
            SendMessage messageContact = new SendMessage()
                    .setChatId(chatId)
                    .setText(String.format("CONTACT DATA%nfirst name:[%s]%nlast name is:[%s]%nphone number:[%s]%nuserID[%d]",
                              phoneNumber, userID));*/


            /**
             * CHAT methods
             */
            Chat chat = m.getChat();
            String description = chat.getDescription();
            String firstName = chat.getFirstName();
            Long secondid = chat.getId();//the same as @chatId message.getChatId()
            String inviteLink = chat.getInviteLink();
            String lastName = chat.getLastName();
            String title = chat.getTitle();
            String userName = chat.getUserName();
            SendMessage messageChat = new SendMessage().setChatId(chatId)
                    .setText(String.format("CHAT DATA%ndescription:[%s]%nfirstName:[%s]%nlastName:[%s]%nid:[%d]%ninviteLink:[%s]%ntitle:[%s]%nuserName:[%s]",
                            description, firstName, lastName, secondid, inviteLink, title, userName));

            try {
                execute(messageUpdate);
                execute(messageChat);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}

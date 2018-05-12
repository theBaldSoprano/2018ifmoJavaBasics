package com.study.itmo.gregory.finalTasks.bot;

import com.study.itmo.gregory.finalTasks.bot.owmtools.City;
import com.study.itmo.gregory.finalTasks.bot.owmtools.WeatherForecast;
import com.study.itmo.gregory.finalTasks.bot.sqlbottool.SQLiteBotTool;
import com.study.itmo.gregory.finalTasks.bot.stringcomparator.StringComparator;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import static com.study.itmo.gregory.finalTasks.bot.Creditals.INTRO_PHRASE;
import static com.study.itmo.gregory.finalTasks.bot.owmtools.OWMTools.*;

public class TestInlineBot extends TelegramLongPollingBot {

    Update prevUpdate = new Update();
    Update prevprevUpdate = new Update();
    SQLiteBotTool tool;
    public static List<City> cities;
    static {
        try {
            pullCitiesFile();
            System.out.println("pulled cities OK 200 @)) ;)");
            cities = getCities(getUnzippedJsonCitiesFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public TestInlineBot() throws SQLException {
        this.tool = new SQLiteBotTool("C:\\weatherbot\\botDB");
        tool.createUsersTable();
    }
    @Override
    public void onUpdateReceived(Update update) {
        /**
         * in common part we pull all subscribers from DB
         * to work with em differently
         * notice: i pull db on every update iteration
         * coz its not time expensive and i dont want
         * difficult logic in sync part
         * where we need keep java users map and db table up-to-date
         *
         * second thing is that we always need a keyboard
         * so we will create one
         */
        HashMap<Long, String> allUsers = new HashMap<>();
        try {
            allUsers.putAll(tool.getAllUsers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                output.setChatId(chatId).setText(INTRO_PHRASE);
                row.add(new InlineKeyboardButton()
                        .setText("Get me weather")
                        .setCallbackData("getWeather"));
                rows.add(row);
                row = new ArrayList<>();
                if (!allUsers.containsKey(chatId)){
                    row.add(new InlineKeyboardButton()
                            .setText("Subscribe me on the Forecast")
                            .setCallbackData("subscribe"));
                }else {
                    row.add(new InlineKeyboardButton()
                            .setText("UNsubscribe me on the Forecast")
                            .setCallbackData("unsubscribe"));
                }
                rows.add(row);
                keyboard.setKeyboard(rows);
                output.setReplyMarkup(keyboard);
                try {
                    execute(output);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            //*********************WEATHER REQUEST RESPONSE*************************
            }else if (prevUpdate.hasCallbackQuery() && prevUpdate.getCallbackQuery().getData().equals("getWeather")){
                //output.setChatId(chatId).setText(String.format("the weather in %s is fine", message.getText()));
                String cityName = message.getText();
                boolean match = false;
                for (City city : cities){
                    //EXACT MATCH!!!!!!!!!!!!
                    if (city.getName().equals(cityName)){
                        match = true;
                        WeatherForecast weather = new WeatherForecast();
                        try {
                            weather = getWeather(city);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String tmp = String.format("temp is %f degrees%n%s%nfuck you gregory", weather.getMain().getTemp(), weather.getWeather().get(0).getDescription());
                        output.setText(tmp).setChatId(chatId);
                        break;
                    }
                }
                if (!match){
                    TreeSet<City> suggestionMap = StringComparator.getSuggestionMap(cities, new City(cityName));
                    output.setText("i suppose you mean:").setChatId(chatId);
                    for (City city : suggestionMap){
                        row = new ArrayList<>();
                        row.add(new InlineKeyboardButton().setText(city.getName())
                                .setCallbackData("WSuggestion" + city.getName()));
                        rows.add(row);
                    }
                }
                row = new ArrayList<>();
                row.add(new InlineKeyboardButton()
                        .setText("Back to main menu")
                        .setCallbackData("back"));
                rows.add(row);
                keyboard.setKeyboard(rows);
                output.setReplyMarkup(keyboard);
                try {
                    execute(output);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            //*********************?????????????*************************
            /*else if (){
                
            }*/
        //*******CALLBACK STUFF********
            /**'
             * if user pressed inline button - the update generated
             * will have callback query
             * and will not have text
             */
        }else if (update.hasCallbackQuery()){
            String callData = update.getCallbackQuery().getData();
            Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            //*********************GET WEATHER*************************
            if (callData.equals("getWeather")){
                EditMessageText output = new EditMessageText()
                        .setChatId(chatId)
                        .setMessageId(messageId)
                        .setText("Send me the city name or location");
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
                if (!allUsers.containsKey(chatId)){
                    row.add(new InlineKeyboardButton()
                            .setText("Subscribe me on the Forecast")
                            .setCallbackData("subscribe"));
                }else {
                    row.add(new InlineKeyboardButton()
                            .setText("UNsubscribe me on the Forecast")
                            .setCallbackData("unsubscribe"));
                }
                rows.add(row);
                keyboard.setKeyboard(rows);
                output.setReplyMarkup(keyboard);
                try {
                    execute(output);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            //*********************SUBSCRIBE*************************
            }else if (callData.equals("subscribe")){
                EditMessageText output = new EditMessageText()
                        .setChatId(chatId)
                        .setMessageId(messageId)
                        .setText("So send me the name or location%nof the city%nYou want to subscribe to");
                row.add(new InlineKeyboardButton().setText("Back to main menu").setCallbackData("back"));
                rows.add(row);
                keyboard.setKeyboard(rows);
                output.setReplyMarkup(keyboard);
                try {
                    execute(output);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            //*********************PROCESS SUGGESTION OF CITY NAME WHEN GETTING WEATHER*************************
            else if (callData.startsWith("WSuggestion")){
                SendMessage output = new SendMessage();
                output.setChatId(chatId);
                String cityName = update.getCallbackQuery().getData().replaceFirst("WSuggestion", "");

                String tmp = "error";
                for (City city : cities){
                    //EXACT MATCH!!!!!!!!!!!!
                    if (city.getName().equals(cityName)){
                        WeatherForecast weather = new WeatherForecast();
                        try {
                            weather = getWeather(city);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        tmp = String.format("temp is %f degrees%n%s%nfuck you gregory", weather.getMain().getTemp(), weather.getWeather().get(0).getDescription());
                        break;
                    }
                }
                output.setText(tmp);
                row = new ArrayList<>();
                row.add(new InlineKeyboardButton()
                        .setText("Back to main menu")
                        .setCallbackData("back"));
                rows.add(row);
                keyboard.setKeyboard(rows);
                output.setReplyMarkup(keyboard);
                try {
                    execute(output);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }
        //LOCATION STUFF
        }else if (update.hasMessage() && update.getMessage().hasLocation()){

            if (prevUpdate.hasCallbackQuery() && prevUpdate.getCallbackQuery().getData().equals("getWeather")){

            }

        }
        prevprevUpdate = prevUpdate;
        prevUpdate = update;
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

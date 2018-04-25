package com.study.itmo.gregory.finalTasks.bot;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.telegram.telegrambots.ApiContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;




public class Solution {

    private static String PROXY_HOST = "gyadd.tgproxy.me" /* proxy host */;
    private static Integer PROXY_PORT = 443;/* proxy port */;
    private static String PROXY_USER = "telegram" /* proxy user */;
    private static String PROXY_PASSWORD = "telegram" /* proxy password */;


    public static void main(String[] args) {
        ApiContextInitializer.init();
        // Create the TelegramBotsApi object to register your bots
        TelegramBotsApi botsApi = new TelegramBotsApi();

        /*// Set up Http proxy
        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope(PROXY_HOST, PROXY_PORT),
                new UsernamePasswordCredentials(PROXY_USER, PROXY_PASSWORD));

        HttpHost httpHost = new HttpHost(PROXY_HOST, PROXY_PORT);

        RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).setAuthenticationEnabled(true).build();

        botOptions.setRequestConfig(requestConfig);
        botOptions.setCredentialsProvider(credentialsProvider);
        botOptions.setHttpProxy(httpHost);*/

        try {
            botsApi.registerBot( new MyTelegramBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }

}

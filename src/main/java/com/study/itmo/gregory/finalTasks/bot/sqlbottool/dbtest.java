package com.study.itmo.gregory.finalTasks.bot.sqlbottool;

import java.sql.SQLException;
import java.util.*;

public class dbtest {
    public static void main(String[] args) throws SQLException {
        SQLiteBotTool tool  = new SQLiteBotTool("C:\\botdb\\botDB");
        tool.createUsersTable();
        tool.addUser(555L, "somecity");

        HashMap<Long, String> allUsers = tool.getAllUsers();
        System.out.println(allUsers.size());

        for (Map.Entry<Long, String> pair : allUsers.entrySet()){
            System.out.println(pair.getKey() +  pair.getValue());
        }
        System.out.println("**************************");


        tool.deleteUser(555L);

        allUsers = tool.getAllUsers();
        System.out.println(allUsers.size());
    }
}

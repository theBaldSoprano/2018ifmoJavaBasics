package com.study.itmo.gregory.finalTasks.bot;

import java.sql.SQLException;
import java.util.*;

public class dbtest {
    public static void main(String[] args) throws SQLException {
        SQLiteBotTool tool  = new SQLiteBotTool("C:\\botdb\\botDB");
        tool.createUsersTable();
        tool.addUser(555L);

        ArrayList<Long> allUsers = tool.getAllUsers();
        System.out.println(allUsers.size());
        for (Long l: allUsers) System.out.println(l);
        System.out.println("**************************");


        tool.deleteUser(555L);

        allUsers = tool.getAllUsers();
        System.out.println(allUsers.size());
    }
}

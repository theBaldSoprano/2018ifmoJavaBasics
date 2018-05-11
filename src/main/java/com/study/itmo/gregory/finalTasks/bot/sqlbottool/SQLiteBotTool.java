package com.study.itmo.gregory.finalTasks.bot.sqlbottool;

import java.sql.*;
import java.util.HashMap;

public class SQLiteBotTool {
    private Connection c = null;
    /**
     * pass the path to your db
     * it will create if not exist
     * or connect to db if it exists
     */
    public SQLiteBotTool(String filepath) {
        c = null;
        String url = String.format("jdbc:sqlite:%s", filepath);
        try {
            c = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
        System.out.println("Opened database successfully");
    }
    /**
     * its good thing to start with
     * creates table of users with one field - ID
     * which stands for user's chatID
     * @throws SQLException
     */
    public void createUsersTable() throws SQLException {
        Statement stmt = c.createStatement();
        String sql =
                "CREATE TABLE IF NOT EXISTS USERS (CHATID INTEGER PRIMARY KEY NOT NULL, CITY TEXT NOT NULL)";
        stmt.execute(sql);
        stmt.close();
    }
    /**
     * add subscriber to users list
     * user id is his chatID actualy
     * @param chatId
     * @throws SQLException
     */
    public void addUser(Long chatId, String cityName) throws SQLException {
        Statement stmt;
        stmt = c.createStatement();
        String sql = "INSERT INTO USERS (CHATID, CITY) VALUES (%d, '%s');";
        stmt.execute(String.format(sql, chatId, cityName));
        stmt.close();
    }

    /**
     * gets all chat IDs from USERS table
     * @return arrayList with all existing chat id's
     * @throws SQLException
     */
    public HashMap<Long, String> getAllUsers() throws SQLException {
        HashMap<Long, String> result = new HashMap<>();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT * FROM USERS" );
        while (rs.next()) {
            Long id = rs.getLong("CHATID");
            String city = rs.getString("CITY");
            result.put(id, city);
        }
        rs.close();
        stmt.close();
        return result;
    }

    /**
     * deletes user with provided chatId
     * @param id
     * @throws SQLException
     */
    public void deleteUser(long id) throws SQLException {
        Statement stmt;
        stmt = c.createStatement();
        String sql = "DELETE FROM USERS WHERE CHATID = %d;";
        stmt.execute(String.format(sql, id));
        stmt.close();
    }
}

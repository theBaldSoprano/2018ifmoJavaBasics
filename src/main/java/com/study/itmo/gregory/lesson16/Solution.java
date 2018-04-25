package com.study.itmo.gregory.lesson16;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Solution {

    /**
     * 0 sql
     * 1 JDBC
     * 2 DriverManager
     * 3 Connection
     * 4 Statement
     * 5 ResultSet
     * 6 PreparedStatement
     */

    //1 java database connect

    private Connection c = null;
    public Solution() throws SQLException {
        c = null;
        String url = "jdbc:sqllite:C:/temp/db.sqllite";

        Statement stmt = c.createStatement();

        ResultSet rs;

        rs = stmt.executeQuery("SELECT NAME FROM USERS");

        while (rs.next()){
            String name = rs.getString("name");

        }

    }


}

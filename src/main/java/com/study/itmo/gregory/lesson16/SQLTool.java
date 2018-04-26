package com.study.itmo.gregory.lesson16;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLTool {
    private Connection c = null;

    public SQLTool(){
        c = null;
        String url = "jdbc:sqlite:C:\\tmp\\Chinook_Sqlite.sqlite";
        try {
            c= DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("established successfully");
    }

    public List<ArtistEntity> getAllArtists() throws SQLException {
        List<ArtistEntity> result = new ArrayList<>();
        Statement stmt = c.createStatement();

        ResultSet rs = stmt.executeQuery(
                "SELECT * FROM Artist"
        );

        while (rs.next()){
            result.add(new ArtistEntity(
                    rs.getInt("ArtistId"),
                    rs.getString("Name")
            ));
        }
        rs.close();
        stmt.close();
        return result;
    }

    public List<AlbumEntity> getAlbumsByArtist(ArtistEntity artist) throws SQLException {
        List<AlbumEntity> result = new ArrayList<>();
        PreparedStatement stmt;
        String sql = "SELECT * FROM Album WHERE ArtistId = ?";
        stmt = c.prepareStatement(sql);
        stmt.setInt(1, artist.getId());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            result.add(new AlbumEntity(
                    rs.getInt("AlbumId"),
                    rs.getString("Title")
            ));
        }
        return result;
    }
}

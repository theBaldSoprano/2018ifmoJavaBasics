package com.study.itmo.gregory.lesson16;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        SQLTool tool = new SQLTool();

        List<ArtistEntity> artists = tool.getAllArtists();

        for (ArtistEntity artist : artists){
            System.out.println(artist.toString());
        }

        List<AlbumEntity> albums = tool.getAlbumsByArtist(artists.get(0));
        for (AlbumEntity alb : albums){
            System.out.println(alb.toString());
        }
    }


}

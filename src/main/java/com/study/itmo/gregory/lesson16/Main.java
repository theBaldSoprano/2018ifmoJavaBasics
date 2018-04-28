package com.study.itmo.gregory.lesson16;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        SQLTool tool = new SQLTool();

        List<ArtistEntity> artists = tool.getAllArtists();
        //for (ArtistEntity artist : artists)System.out.println(artist.toString());


        List<AlbumEntity> albums = tool.getAlbumsByArtist(artists.get(3));
        for (AlbumEntity alb : albums) System.out.println(alb.toString());

        AlbumEntity album = albums.get(0);

        ArrayList tracks = tool.getThatByThis(TrackEntity.class, album);

        /*for (Object obj: tracks){
            if (obj instanceof TrackEntity){
                TrackEntity track = (TrackEntity)obj;
                System.out.println(track.toString());
            }
        }*/

        ArtistEntity artist = artists.get(3);
        ArrayList albums2 = tool.getThatByThis(AlbumEntity.class, artist);

        for (Object obj : albums2){
            if (obj instanceof AlbumEntity){
                AlbumEntity alb = (AlbumEntity)obj;
                System.out.println(alb.toString());
            }
        }
    }


}

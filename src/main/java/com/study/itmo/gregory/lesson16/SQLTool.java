package com.study.itmo.gregory.lesson16;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SQLTool {
    private Connection c = null;

    public SQLTool() {
        c = null;
        String url = "jdbc:sqlite:C:\\tmp\\Chinook_Sqlite.sqlite";
        try {
            c = DriverManager.getConnection(url);
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

        while (rs.next()) {
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
        stmt.setInt(1, artist.getArtistId());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            result.add(new AlbumEntity(
                    rs.getInt("AlbumId"),
                    rs.getString("Title")
            ));
        }
        return result;
    }

    public <T, A> ArrayList<T> getThatByThis(Class<T> aClass, A entity) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        ArrayList<T> result = new ArrayList<>();
        //GET TRACK BY ALBUM
        //get annotation from target class
        Annotation[] annotations = aClass.getDeclaredAnnotations();
        MyAnno targetAnno = null;
        for (Annotation ann : annotations){
            if (ann instanceof MyAnno){
                targetAnno = (MyAnno)ann;
            }
        }
        if (targetAnno == null) throw new IllegalArgumentException();
        //check if entity is properly annotated
        annotations = entity.getClass().getDeclaredAnnotations();
        for (Annotation tmp : annotations){
            if (tmp instanceof MyAnno){
                MyAnno entityAnn = (MyAnno)tmp;
                //get true table field name for search
                String tableColName = entityAnn.trueIdTableName();
                //then get table Name where to search
                String tableName = targetAnno.trueTableName();
                //then we need the particular trackId of entity
                int entiryId = -1;
                Field[] entityFields = entity.getClass().getDeclaredFields();
                for (Field field : entityFields){
                    Annotation[] tmpr = field.getAnnotations();
                    for (Annotation ann : tmpr){
                        if (ann instanceof IsId){
                            field.setAccessible(true);
                            entiryId = field.getInt(entity);
                        }
                        //todo валидация и копирование по аннотации isID
                    }
                    /*if (field.getName().toLowerCase().equals("albumid")){
                        field.setAccessible(true);
                        entiryId = field.getInt(entity);
                    }*/
                }
                PreparedStatement stmt;
                String sql = String.format("SELECT * FROM %s WHERE %s = %d",
                                            tableName, tableColName, entiryId);
                stmt = c.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()){
                    T t = aClass.getDeclaredConstructor().newInstance();
                    Field[] fields = aClass.getDeclaredFields();
                    for (Field field : fields){
                        for(Annotation annotation : field.getAnnotations()){
                            if (annotation instanceof IsField){
                                if(field.getType() == String.class){
                                    String value = rs.getString(field.getName());
                                    field.setAccessible(true);
                                    field.set(t, value);
                                }else if(field.getType() == int.class){
                                    int value = rs.getInt(field.getName());
                                    field.setAccessible(true);
                                    field.set(t, value);
                                }
                            }
                        }
                    }
                    result.add(t);
                }
            }
        }
        return result;
    }
}

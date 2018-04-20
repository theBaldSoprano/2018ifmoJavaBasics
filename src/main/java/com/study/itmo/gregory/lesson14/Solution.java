package com.study.itmo.gregory.lesson14;


import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import static java.lang.annotation.ElementType.TYPE;

@UnstableCode
public class Solution {
    /**
     * 1.1 todo google как использовать аннотации в рантайме с помощью рефлекшнов
     * 1.2 аннотации с клиентской стороны - дать разрабу что то понять - выбрасывается при компиляции
     * Аннотация - это интерфейс
     *
     * 2. сети!
     *  -
     * @param args
     */
    public static void main(String[] args) throws IOException {

        URL urla = new URL("https://www.wikipedia.org/");

        URLConnection urlConnection = urla.openConnection();

        if (!(urlConnection instanceof HttpURLConnection)) throw new IllegalArgumentException();

        //urlConnection.getContentType();//тип контента

        System.out.println(String.format("type is [%s] length = %d", urlConnection.getContentType(),urlConnection.getContentLength()));

        Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
        for (Map.Entry<String, List<String>> pair : headerFields.entrySet()){
            System.out.print(pair.getKey() + " ");
            for (String s : pair.getValue()){
                System.out.print(s + " ");
            }
            System.out.println();
        }


    }
    @Version(version = "2", clasz = String.class, type = TYPE)
    public void foo(){}

    //

    @interface Version{

        String version() default "1.0";
        //примитивы строки и приммассивы
        Class clasz();
        ElementType type();//енам


    }

}
@Retention(RetentionPolicy.CLASS)
/**
 * аннотацию тоже можно аннотировать
 * source - аннотации только в исхдном коде
 * CLASS - есть после компиляции в состоянии байткода но нет в рантайме
 * RUNTIME - ну угадай
 */
@Target(ElementType.TYPE)
/**
 * тип таргета - это то к чему можно применить аннатацию
 * тайп - это только классы и интерфейсы
 */
@interface UnstableCode{

}


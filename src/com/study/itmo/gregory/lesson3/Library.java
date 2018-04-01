package com.study.itmo.gregory.lesson3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.study.itmo.gregory.lesson3.Task4.getWords;

public class Library {
    /*
    класс словаря текстов
    два метода
    подкласс для конкретного текста

    мапа чтобы хранить слова(ки) и массив интов индексов их текстов(значение)

    метод 1
    добавляешь текст = делишь на слова, добавляешь слово и индекс текста

    метод2
    поиск текста - по слову ищешь индексы текстов у этого слова

    метод 3
    поиск по нескольким словам B
    todo search by several words

    todo релевантный вывод.
    при индексировании надо хранить количество вхождений
    индексирование - долго
    поиск - быстро




     */

    ArrayList<String> texts = new ArrayList<>();

    HashMap<String, ArrayList<Integer>> dictionary = new HashMap<>();

    public void addText(String text){

        text = text.toLowerCase();

        texts.add(text);
        int textIndex = texts.size() - 1;
        String [] words = getWords(text);

        for (int i = 0; i < words.length; i++) {
            ArrayList<Integer> integers = dictionary.get(words[i]);
            if(integers == null){
                integers = new ArrayList<>();
                dictionary.put(words[i], integers);
            }
            if (!integers.contains(textIndex)) {
                integers.add(textIndex);
            }
        }

    }

    public void printMatches(String s){

        s = s.toLowerCase();

        if (!dictionary.containsKey(s)){
            System.out.println("no matches");
            return;
        }
        for (Map.Entry<String, ArrayList<Integer>> word : dictionary.entrySet()){
            if (word.getKey().equals(s)){
                ArrayList<Integer> matches = word.getValue();
                for (Integer index : matches){
                    System.out.println("match in text №: " + index);
                }
            }
        }
    }
}

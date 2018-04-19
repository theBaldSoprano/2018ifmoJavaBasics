package com.study.itmo.gregory.lesson13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CollectionsTasks {
    public static void main(String[] args) throws IOException {
        //readLines();
        getFrequency("C:\\Users\\GregorySSDNB\\Documents\\alphabet.txt");
    }
    public static void readLines() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Queue<String> queue = new LinkedList<>();

        String line;
        while (!(line = reader.readLine()).equals("q")){
            queue.add(line);
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

        List<String> lists = new ArrayList<>();
        lists.forEach(s -> System.out.println(s));//todo google lambda

    }

    public static void getFrequency(String filename) throws IOException {
        Map<Character, Integer> alphabet = new HashMap<>();

        Path path = Paths.get(filename);
        List<String> allLines = Files.readAllLines(path);
        StringBuffer stringBuffer = new StringBuffer();
        for (String s : allLines)stringBuffer.append(s);

        for (char c : stringBuffer.toString().toCharArray()){
            if (c >= 'А' && c <= 'я') {
                if (!alphabet.containsKey(c)) alphabet.put(c, 1);
                alphabet.put(c, alphabet.get(c) + 1);
            }
        }

        List<Map.Entry<Character, Integer>> pairs = new LinkedList<>();
        pairs.addAll(alphabet.entrySet());

        Collections.sort(pairs, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                if (o1.getValue() < o2.getValue()) return -1;
                else if (o1.getValue() == o2.getValue()) return 0;
                else return 0;
            }
        });

        System.out.println(pairs.get(pairs.size() - 1).getKey());

    }

    public static <T> Collection<T> removeDupl (Collection<T> collection){
        /*Set<T> set = new HashSet<>(collection);
        set.addAll(collection);
        collection.clear();
        collection.addAll(set);*/
        //или так:))
        return new HashSet<>(collection);
    }
}


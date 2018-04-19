package com.study.itmo.gregory.lesson12;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import static com.study.itmo.gregory.finalTasks.numbers.idxutils.FilePaths.TRAINING_IMAGES;

public class task1BasicFunc {
    public static final String FILE = "C:\\Users\\GregorySSDNB\\Documents\\testFile.txt";

    public static void main(String[] args) throws IOException {
        /*getInfo(FILE);
        try {
            write("foo", FILE);
            write("bar", FILE);
            write("baz", FILE);

            List<String> list = readList(FILE);

            System.out.println(list);

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*List<String> l = getFileNamesOnStringMatchFiles("C:\\Users\\GregorySSDNB\\Documents", new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return entry.getFileName().toString().contains(".iml");
            }
        });
        System.out.println(l.toString());*/

        List<String> names = getFileNamesOnStringMatchFiles("C:\\Users\\GregorySSDNB\\Documents\\", entry -> entry.getFileName().toString().contains("My"));
        System.out.println(String.format("found %d matches", names.size()));
        for(String s : names) System.out.println(s);
        /*List<String> names1 = getFileNamesOnStringMatchFiles("C:\\Users\\GregorySSDNB\\Documents\\", entry -> entry.getFileName().toString().contains("My"));
        System.out.println(String.format("found %d matches", names1.size()));
        for(String s : names1) System.out.println(s);*/

    }

    public static void getInfo(String filename) {
        Path path = Paths.get(filename);

        if (Files.exists(path)) {
            try {
                System.out.println(Files.getLastModifiedTime(path));
                System.out.println(path.getFileName().toString());
                System.out.println(Files.size(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String> readList(String filename) throws IOException {
        Path path = Paths.get(filename);
        return Files.readAllLines(path);
    }

    public static void write(String line, String inPath) throws IOException {
        //Path path = Paths.get(inPath);
        //Files.write(path, line.getBytes(), StandardOpenOption.APPEND);//todo
        BufferedWriter bw = new BufferedWriter(new FileWriter(inPath, true));
        bw.append(line);
        bw.close();
    }

    public static void concatStrings(String p1, String p2, String nameTo) throws IOException {
        try (BufferedReader reader1 = new BufferedReader(new FileReader(p1));
             BufferedReader reader2 = new BufferedReader(new FileReader(p2));
             BufferedWriter bw = new BufferedWriter(new FileWriter(nameTo, true));) {

            String line;
            while ((line = reader1.readLine()) != null) {
                bw.append(System.lineSeparator()).write(line);
            }
            while ((line = reader2.readLine()) != null) {
                bw.append(System.lineSeparator()).write(line);
            }
        }
    }

    public static void concatBytes(String p1, String p2, String nameTo) throws IOException {

        try (FileInputStream reader1 = new FileInputStream(p1);
             FileInputStream reader2 = new FileInputStream(p2);
             FileOutputStream reader3 = new FileOutputStream(nameTo);) {


            byte[] bytes = new byte[1024];

            int offset;
            while ((offset = reader1.read(bytes)) != -1) {
                reader3.write(bytes, 0, offset);
            }
            while ((offset = reader2.read(bytes)) != -1) {
                reader3.write(bytes, 0, offset);
            }

        }

    }

    public static List<String> getFileNamesOnStringMatch(String dir, DirectoryStream.Filter<String> filter) throws IOException {

        List<String> result = new ArrayList<>();
        File directory = new File(dir);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException();
        }

        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (filter.accept(file.getName())) {
                    if (file.isDirectory())result.add(String.format("[%s]", file.getName()));
                    else result.add(file.getName());
                }
                if (file.isDirectory()) {
                    List<String> nested = getFileNamesOnStringMatch(file.getAbsolutePath(), filter);
                    result.addAll(nested);
                }
            }
        }
        return result;
    }

    public static List<String> getFileNamesOnStringMatchFiles(String dir, DirectoryStream.Filter<Path> filter) throws IOException {

        List<String> result = new ArrayList<>();

        Path directory = Paths.get(dir);
        if (!Files.isDirectory(directory)) throw new IllegalArgumentException();

        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory);//todo WHY throwing access denied exception??
        for (Path path : directoryStream){
            if (filter.accept(path)){
                if (Files.isDirectory(path)) String.format("[%s]", result.add(path.getFileName().toString()));
                else result.add(path.getFileName().toString());
            }
            if (Files.isDirectory(path)){
                result.addAll(getFileNamesOnStringMatchFiles(path.toAbsolutePath().toString(), filter));
            }
        }
        return result;
    }
}

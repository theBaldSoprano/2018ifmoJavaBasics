package com.study.itmo.gregory.lesson12;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.study.itmo.gregory.finalTasks.numbers.idxutils.FilePaths.TRAINING_IMAGES;

public class IOStreamsStudy {
    public static void main(String[] args) throws IOException {
        File file = new File(TRAINING_IMAGES);
        file.exists();
        //file==directory
        file.isFile();
        file.list();

        Path path = Paths.get(TRAINING_IMAGES);


        InputStream inputStream;

        inputStream = new FileInputStream("PATH");
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream); //todo google usecases

        Files.readAllLines(path);//todo WOW  read all lines!!!!


    }
}

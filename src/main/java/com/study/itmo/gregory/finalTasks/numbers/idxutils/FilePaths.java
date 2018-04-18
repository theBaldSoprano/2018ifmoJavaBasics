package com.study.itmo.gregory.finalTasks.numbers.idxutils;

public class FilePaths {
    public static final String TRAINING_LABELS = "C:\\Users\\GregorySSDNB\\Documents\\trainingSetLabels\\train-labels.idx1-ubyte";
    public static final String TRAINING_IMAGES = "C:\\Users\\GregorySSDNB\\Documents\\trainingSetImages\\train-images.idx3-ubyte";
    public static final String TEST_LABELS = "C:\\Users\\GregorySSDNB\\Documents\\testSetLabels\\t10k-labels.idx1-ubyte";
    public static final String TEST_IMAGES = "C:\\Users\\GregorySSDNB\\Documents\\testSetImages\\t10k-images.idx3-ubyte";

    public static final int LABELS_FILE_DATA_OFFSET = 8;
    public static final int IMAGES_FILE_DATA_OFFSET = 16;

    public static final int LABELS_MAGIC_NUMBER = 2049;
    public static final int IMAGES_MAGIC_NUMBER = 2051;


}

package com.study.itmo.gregory.finalTasks.numbers.idxutils;

public class FilePaths {
    public static final String ROOT = "C:\\mnist";
    public static final String TRAINING_LABELS = String.format("%s\\train-labels.idx1-ubyte", ROOT);
    public static final String TRAINING_IMAGES = String.format("%s\\train-images.idx3-ubyte", ROOT);
    public static final String TEST_LABELS = String.format("%s\\t10k-labels.idx1-ubyte", ROOT);
    public static final String TEST_IMAGES = String.format("%s\\t10k-images.idx3-ubyte", ROOT);

    public static final int LABELS_MAGIC_NUMBER = 2049;
    public static final int IMAGES_MAGIC_NUMBER = 2051;

}

package com.study.itmo.gregory.lesson16;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnno {
    String trueIdTableName();
    String trueTableName();
}

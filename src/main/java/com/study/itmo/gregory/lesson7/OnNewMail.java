package com.study.itmo.gregory.lesson7;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface OnNewMail {
    String message() default "its default value";
}

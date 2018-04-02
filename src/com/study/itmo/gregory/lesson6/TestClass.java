package com.study.itmo.gregory.lesson6;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Objects;

public class TestClass {

    //правила переопределения equals
    //если тип объектов разный то фолс
    /*
    x == y y == x
    x == y y == z -> z == x
    x == null -> false
     */

    int age;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestClass testClass = (TestClass) o;
        return age == testClass.age;
    }

    @Override
    public int hashCode() {

        return Objects.hash(age);
        //todo посмотреть про переопределение иквалс и хэшкод!!!
    }
}

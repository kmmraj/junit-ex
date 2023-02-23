package org.example.junit;

public class MyClass {
    protected String methodToBeTested() {
        return anotherMethodInClass();
    }

    protected String anotherMethodInClass() {
        throw new NullPointerException();
    }
}
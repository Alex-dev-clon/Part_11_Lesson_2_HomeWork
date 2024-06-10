package lesson2;

import lesson2.rand.RandomInteger;
import lesson2.rand.RandomIntegerProcessor;

@MyAnno
public class Annotations {

    @MyAnno
    public static void main(String[] args) {

        MyClass myClass = new MyClass();
        RandomIntegerProcessor.processRandomInteger(myClass);

        System.out.println(myClass.integer);
    }

    private static class MyClass {

        @RandomInteger (value = "", min = 90)
        private int integer;
    }
}

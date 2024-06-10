package lesson2.rand;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;

public class RandomIntegerProcessor {

    public static void processRandomInteger (Object obj){
        for (Field declareField : obj.getClass().getDeclaredFields()){
            if (declareField.isAnnotationPresent(RandomInteger.class)){
                RandomInteger annotation = declareField.getAnnotation(RandomInteger.class);
                int min = annotation.min();
                int max = annotation.max();

                declareField.setAccessible(true);
                try {
                    declareField.set(obj, ThreadLocalRandom.current().nextInt(min, max));
                } catch (IllegalAccessException e){
                    System.err.println("Не удалось подставить случайное значение " + e);
                }
            }
        }
    }
}

package lesson2.homework;

import java.lang.reflect.Field;
import java.time.*;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDateProcessor {
    private static final long MILLISECONDS_OF_DAY = 86_400_000L;
    private static final long MILLISECONDS_OF_SEC = 1_000L;

    public static void processRandomDate(Object obj) {
        for (Field declareField : obj.getClass().getDeclaredFields()) {
            if (declareField.isAnnotationPresent(RandomDate.class)) {
                RandomDate annotation = declareField.getAnnotation(RandomDate.class);

                long min = annotation.min();
                long max = annotation.max();

                declareField.setAccessible(true);

                try {
                    if (declareField.getType().equals(Date.class))
                        declareField.set(obj, new Date(ThreadLocalRandom.current().nextLong(min, max)));

                    if (declareField.getType().equals(LocalDate.class))
                        declareField.set(obj, LocalDate.ofEpochDay(ThreadLocalRandom.current()
                                .nextLong(min, max) / MILLISECONDS_OF_DAY));

                    if (declareField.getType().equals(LocalDateTime.class)) {
                        declareField.set(obj, LocalDateTime.ofEpochSecond(ThreadLocalRandom.current()
                                        .nextLong(min, max) / MILLISECONDS_OF_SEC,0, ZoneOffset.ofHours(-3)));
                    }

                    if (declareField.getType().equals(Instant.class))
                        declareField.set(obj, Instant.ofEpochMilli(ThreadLocalRandom.current().nextLong(min, max)));
                    
                } catch (IllegalAccessException e) {
                    System.err.println(("Не удалось подставить случайную дату " + e));
                }
            }
        }
    }
}
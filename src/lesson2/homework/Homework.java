package lesson2.homework;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Homework {
    public static void main(String[] args) {

        Test test = new Test();
        RandomDateProcessor.processRandomDate(test);

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        System.out.println(format.format(test.dateDate1) + " - случайная дата за весь 2024 год");
        System.out.println(format.format(test.dateDate2) + " - случайная дата с 10.06.2024 до конца 2024 года");
        System.out.println();

        DateTimeFormatter localFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.println(test.localDate1.format(localFormat) + " - случайная дата за весь 2024 год");
        System.out.println(test.localDate2.format(localFormat) + " - случайная дата с 10.06.2024 до конца 2024 года");
        System.out.println();

        System.out.println(test.localDateTime1.format(localFormat) + " - случайная дата за весь 2024 год");
        System.out.println(test.localDateTime2.format(localFormat) + " - случайная дата с 10.06.2024 до конца 2024 года");
        System.out.println();


        System.out.println(test.instant1 + " - случайная дата за весь 2024 год");
        System.out.println(test.instant2 + " - случайная дата с 10.06.2024 до конца 2024 года");
        System.out.println();
    }

    private static class Test {

        @RandomDate
        private Date dateDate1; // По умолчанию весь 2024 год
        @RandomDate(min = 1_717_966_800_000L) // с 10.06.2024 до конца 2024 года
        private Date dateDate2;

        @RandomDate
        private LocalDate localDate1; // По умолчанию весь 2024 год
        @RandomDate(min = 1_717_966_800_000L) // с 10.06.2024 до конца 2024 года
        private LocalDate localDate2;

        @RandomDate
        private LocalDateTime localDateTime1; // По умолчанию весь 2024 год
        @RandomDate(min = 1_717_966_800_000L) // с 10.06.2024 до конца 2024 года
        private LocalDateTime localDateTime2;

        @RandomDate
        private Instant instant1; // По умолчанию весь 2024 год
        @RandomDate(min = 1_717_966_800_000L) // с 10.06.2024 до конца 2024 года
        private Instant instant2;
    }
}

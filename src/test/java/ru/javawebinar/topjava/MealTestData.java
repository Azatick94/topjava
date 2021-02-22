package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int ID = START_SEQ;
    public static final int USER_ID = START_SEQ;

    public static final List<Meal> mealsAllReversedSortedTest = Arrays.asList(
            new Meal(100006, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410),
            new Meal(100005, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(100004, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(100003, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(100002, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(100001, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(100000, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(100008, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ланч", 1500),
            new Meal(100007, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510)
    );

    public static final List<Meal> mealsGetBetweenTest = Arrays.asList(
            new Meal(100002, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(100001, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(100000, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500)
    );


}

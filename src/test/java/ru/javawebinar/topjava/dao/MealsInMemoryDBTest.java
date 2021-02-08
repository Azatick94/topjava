package ru.javawebinar.topjava.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MealsInMemoryDBTest {

    List<MealTo> mealsExpected;

    @BeforeEach
    void setUp() {
        mealsExpected = Arrays.asList(
                new MealTo(1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500, false),
                new MealTo(2, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000, false),
                new MealTo(3, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500, false),
                new MealTo(4, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100, true),
                new MealTo(5, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000, true),
                new MealTo(6, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500, true),
                new MealTo(7, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410, true)
        );
    }


    @Test
    void getInstance() {

        List<MealTo> mealsResult = MealsInMemoryDB.getInstance().findAllMeals();

        assertEquals(mealsExpected.toString(), mealsResult.toString());

    }


    @Test
    void deleteMealById() {

        MealsInMemoryDB.getInstance().deleteMealById(5);
        List<MealTo> mealsResult = MealsInMemoryDB.getInstance().findAllMeals();

        List<MealTo> mealsExpectedFiltered = Arrays.asList(
                new MealTo(1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500, false),
                new MealTo(2, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000, false),
                new MealTo(3, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500, false),
                new MealTo(4, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100, false),
                new MealTo(6, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500, false),
                new MealTo(7, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410, false)
        );

        assertEquals(mealsExpectedFiltered.toString(), mealsResult.toString());


    }
}
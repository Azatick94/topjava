package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID_FOR_USER = START_SEQ + 2;
    public static final int MEAL_ID_FOR_ADMIN = START_SEQ + 9;

    public static final Meal meal1 = new Meal(100002, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal meal2 = new Meal(100003, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Meal meal3 = new Meal(100004, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static final Meal meal4 = new Meal(100005, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    public static final Meal meal5 = new Meal(100006, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
    public static final Meal meal6 = new Meal(100007, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
    public static final Meal meal7 = new Meal(100008, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);

    public static final Meal mealAdmin1 = new Meal(100009, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static final Meal mealAdmin2 = new Meal(100010, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);

    public static final List<Meal> mealsAllForUser = Arrays.asList(meal7, meal6, meal5, meal4, meal3, meal2, meal1);
    public static final List<Meal> mealsAllForAdmin = Arrays.asList(mealAdmin2, mealAdmin1);

    public static final List<Meal> meals30thJanuary2020UserData = Arrays.asList(meal3, meal2, meal1);
    public static final List<Meal> meals31thJanuary2020UserData = Arrays.asList(meal7, meal6, meal5, meal4);

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2000, Month.JANUARY, 1, 1, 1), "Created_Meal", 1000);
    }

    public static Meal getUpdated(int ID) {
        Meal updated = new Meal();
        updated.setId(ID);
        updated.setDateTime(LocalDateTime.of(2222, Month.JANUARY, 1, 1, 1));
        updated.setDescription("Updated Value");
        updated.setCalories(999);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields().isEqualTo(expected);
    }

}

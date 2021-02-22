package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.ID;
import static ru.javawebinar.topjava.MealTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    MealService mealService;

    @Test
    public void get() {
        Meal mealCalculated = mealService.get(ID, USER_ID);
        Meal mealExpected = MealTestData.mealsAllReversedSortedTest.get(6);
        assertThat(mealCalculated).isEqualTo(mealExpected);
    }

    @Test
    public void getAll() {
        List<Meal> mealsCalculated = mealService.getAll(USER_ID);
        List<Meal> mealsExpected = MealTestData.mealsAllReversedSortedTest;
        assertThat(mealsCalculated).isEqualTo(mealsExpected);
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> mealsCalculated = mealService.getBetweenInclusive(LocalDate.of(2020, 1, 30),
                LocalDate.of(2020, 1, 30), USER_ID);
        List<Meal> mealsExpected = MealTestData.mealsGetBetweenTest;
        assertThat(mealsCalculated).isEqualTo(mealsExpected);
    }

    @Test
    public void delete() {
        mealService.delete(ID, USER_ID);
        assertThrows(NotFoundException.class, () -> mealService.get(ID, USER_ID));
    }

    @Test
    public void update() {
        Meal mealExpected = new Meal();
        mealExpected.setId(ID);
        mealExpected.setDateTime(LocalDateTime.of(2222, Month.JANUARY, 1, 1, 1));
        mealExpected.setDescription("Updated Value");
        mealExpected.setCalories(999);

        // updating value
        mealService.update(mealExpected, USER_ID);

        Meal mealCalculated = mealService.get(ID, USER_ID);
        assertThat(mealExpected).isEqualTo(mealCalculated);
    }

    @Test
    public void create() {
        // TODO
        Meal mealToCreate = new Meal(LocalDateTime.of(2000, Month.JANUARY, 1, 1, 1), "Created_Meal", 1000);

        Meal mealExpected = mealService.create(mealToCreate, USER_ID);
        assertThat(mealExpected.getId()).isEqualTo(100009);
    }


}

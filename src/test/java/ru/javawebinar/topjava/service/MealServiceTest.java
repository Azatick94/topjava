package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertThrows;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService mealService;

    @Test
    public void getExistingMeal() {
        Meal mealCalculated = mealService.get(MEAL_ID_FOR_USER, USER_ID);
        assertMatch(mealCalculated, meal1);
    }

    @Test
    public void getNonExistingMeal() {
        assertThrows(NotFoundException.class, () -> mealService.get(9999999, USER_ID));
    }

    @Test
    public void getAnotherUserMeal() {
        assertThrows(NotFoundException.class, () -> mealService.get(MEAL_ID_FOR_ADMIN, USER_ID));
    }

    @Test
    public void getAllForUser() {
        List<Meal> mealsCalculated = mealService.getAll(USER_ID);
        assertMatch(mealsCalculated, AllUserMeals);
    }

    @Test
    public void getAllForAdmin() {
        List<Meal> mealsCalculated = mealService.getAll(ADMIN_ID);
        assertMatch(mealsCalculated, AllAdminMeals);
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> mealsCalculated = mealService.getBetweenInclusive(
                LocalDate.of(2020, 1, 30),
                LocalDate.of(2020, 1, 30), USER_ID);
        assertMatch(mealsCalculated, MealTestData.january30th2020UserMeals);
    }

    @Test
    public void getBetweenInclusiveTestNull() {
        List<Meal> mealsCalculated = mealService.getBetweenInclusive(null, null, USER_ID);
        assertMatch(mealsCalculated, AllUserMeals);
    }

    @Test
    public void getBetweenInclusiveTestLeftNull() {
        List<Meal> mealsCalculated = mealService.getBetweenInclusive(null, LocalDate.of(2020, 1, 30), USER_ID);
        assertMatch(mealsCalculated, MealTestData.january30th2020UserMeals);
    }

    @Test
    public void getBetweenInclusiveTestRightNull() {
        List<Meal> mealsCalculated = mealService.getBetweenInclusive(LocalDate.of(2020, 1, 31), null, USER_ID);
        assertMatch(mealsCalculated, january31th2020UserMeals);
    }

    @Test
    public void delete() {
        mealService.delete(MEAL_ID_FOR_USER, USER_ID);
        assertThrows(NotFoundException.class, () -> mealService.get(MEAL_ID_FOR_USER, USER_ID));
    }

    @Test
    public void deleteAnotherUserMeal() {
        assertThrows(NotFoundException.class, () -> mealService.delete(MEAL_ID_FOR_ADMIN, USER_ID));
    }

    @Test
    public void update() {
        Meal mealToUpdate = getUpdated(MEAL_ID_FOR_USER);

        // updating value
        mealService.update(mealToUpdate, USER_ID);

        Meal mealExpected = new Meal(MEAL_ID_FOR_USER, LocalDateTime.of(2222, Month.JANUARY, 1, 1, 1), "Updated Value", 999);
        Meal mealCalculated = mealService.get(MEAL_ID_FOR_USER, USER_ID);

        assertMatch(mealCalculated, mealExpected);
    }

    @Test
    public void updateAnotherUserMeal() {
        Meal mealToUpdate = getUpdated(MEAL_ID_FOR_USER);
        // updating value
        assertThrows(NotFoundException.class, () -> mealService.update(mealToUpdate, ADMIN_ID));
    }

    @Test
    public void create() {
        Meal created = mealService.create(getNew(), USER_ID);
        Integer newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);

        assertMatch(created, newMeal);
        assertMatch(mealService.get(newId, USER_ID), newMeal);

    }

    @Test
    public void createDuplicated() {
        Meal newMeal = getNew();
        newMeal.setDateTime(meal1.getDateTime());
        assertThrows(DataAccessException.class, () -> mealService.create(newMeal, USER_ID));
    }
}

package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealsInMemoryDB;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * CRUD Service to communicate with different DataBase realizations
 */
public class MealService {

    private MealsInMemoryDB database;

    public MealService() {
        database = MealsInMemoryDB.getInstance();
    }

    public List<MealTo> findAllMeals() {
        return database.findAllMeals();
    }

    public void deleteMealById(Integer id) {
        database.deleteMealById(id);
    }

    public void addMeal(LocalDateTime dateTime, String description, int calories) {
        database.addMeal(dateTime, description, calories);
    }

    public void updateMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        database.updateMeal(id, dateTime, description, calories);
    }

}

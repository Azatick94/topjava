package ru.javawebinar.topjava.repository;


import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * simple CRUD interface
 */
public interface MealRepository {

    List<MealTo> findAllMeals();

    MealTo getById(Integer id);

    void deleteMealById(Integer id);

    void addMeal(LocalDateTime dateTime, String description, int calories);

    void updateMeal(Integer id, LocalDateTime dateTime, String description, int calories);


}

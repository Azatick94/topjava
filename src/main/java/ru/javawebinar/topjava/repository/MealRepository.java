package ru.javawebinar.topjava.repository;


import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * simple CRUD interface
 */
public interface MealRepository {

    List<Meal> findAll();

    Meal getById(Integer id);

    void deleteById(Integer id);

    Meal add(Meal meal);

    Meal update(Integer id, LocalDateTime dateTime, String description, int calories);
}

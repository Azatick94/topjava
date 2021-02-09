package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MealsInMemoryDB implements MealRepository {

    private static MealsInMemoryDB instance;
    private List<Meal> meals = new ArrayList<>();

    {
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    private MealsInMemoryDB() {
    }

    /**
     * Singleton Realization
     *
     * @return MealsInMemoryDB instance
     */
    public static synchronized MealsInMemoryDB getInstance() {
        if (instance == null)
            instance = new MealsInMemoryDB();
        return instance;
    }

    @Override
    public List<Meal> findAll() {
        return meals;
    }

    @Override
    public Meal getById(Integer id) {
        List<Meal> meals = findAll();
        return meals.stream().filter(m -> m.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getId().equals(id)) {
                meals.remove(meals.get(i));
            }
        }
    }

    @Override
    public Meal add(Meal meal) {
        meals.add(meal);
        return meal;
    }

    // оставил пока как есть
    @Override
    public Meal update(Integer id, LocalDateTime dateTime, String description, int calories) {
        for (Meal meal : meals) {
            if (meal.getId().equals(id)) {
                meal.setDateTime(dateTime);
                meal.setDescription(description);
                meal.setCalories(calories);
                return meal;
            }
        }
        return null;
    }


}

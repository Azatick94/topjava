package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MealsInMemoryDB implements MealRepository {

    private static MealsInMemoryDB instance;
    private List<Meal> meals = new ArrayList<>();

    {
        addMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
        addMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
        addMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
        addMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
        addMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
        addMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
        addMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);
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
    public List<MealTo> findAllMeals() {
        return MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, 2000);
    }

    @Override
    public MealTo getById(Integer id) {
        List<MealTo> mealTos = findAllMeals();
        return mealTos.stream().filter(m -> m.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public void deleteMealById(Integer id) {
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getId().equals(id)) {
                meals.remove(meals.get(i));
            }
        }
    }

    @Override
    public void addMeal(LocalDateTime dateTime, String description, int calories) {
        meals.add(new Meal(dateTime, description, calories));
    }

    @Override
    public void updateMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        for (Meal meal : meals) {
            if (meal.getId().equals(id)) {
                meal.setDateTime(dateTime);
                meal.setDescription(description);
                meal.setCalories(calories);
            }
        }
    }


}

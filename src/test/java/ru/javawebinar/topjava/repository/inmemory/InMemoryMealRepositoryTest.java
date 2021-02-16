package ru.javawebinar.topjava.repository.inmemory;

import junit.framework.TestCase;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;


public class InMemoryMealRepositoryTest extends TestCase {

    MealRepository mealRepository = new InMemoryMealRepository();

//    public void testGetAll() {
//        List<Meal> mealList = mealRepository.getAll(1);
//        mealList.forEach(System.out::println);
//    }

//    public void testGetFiltered() {
//        List<Meal> filtered = mealRepository.getFiltered(LocalDate.of(2020, Month.JANUARY, 30),
//                LocalDate.of(2020, Month.JANUARY, 30), 1);
//
//        filtered.forEach(System.out::println);
//    }

    public void testGet() {
        Meal meal = mealRepository.get(10, 1);
        assertEquals(null, meal);
    }

    public void testDelete() {
        boolean delete = mealRepository.delete(3, 100);
        assertEquals(false, delete);
    }

}
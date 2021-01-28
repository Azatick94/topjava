package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        System.out.println("Result BY Cycles:");
        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println("\nResult BY Streams:");
        List<UserMealWithExcess> mealsToCycles = filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsToCycles.forEach(System.out::println);
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesByDays = new HashMap<>();
        for (UserMeal current : meals) {
            caloriesByDays.merge(current.getDateTime().toLocalDate(), current.getCalories(), Integer::sum);
        }

        List<UserMealWithExcess> filteredMeals = new ArrayList<>();
        for (UserMeal current : meals) {
            if (caloriesByDays.get(current.getDateTime().toLocalDate()) >= caloriesPerDay &&
                    TimeUtil.isBetweenHalfOpen(current.getDateTime().toLocalTime(), startTime, endTime)) {
                filteredMeals.add(new UserMealWithExcess(current.getDateTime(), current.getDescription(), current.getCalories(), true));
            }
        }

        return filteredMeals;
    }


    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesByDays = meals.stream()
                .collect(Collectors.toMap(
                        date -> date.getDateTime().toLocalDate(),
                        UserMeal::getCalories,
                        Integer::sum
                ));

        return meals.stream()
                .filter(x -> caloriesByDays.get(x.getDateTime().toLocalDate()) >= caloriesPerDay)
                .filter(x -> TimeUtil.isBetweenHalfOpen(x.getDateTime().toLocalTime(), startTime, endTime))
                .map(x -> new UserMealWithExcess(x.getDateTime(), x.getDescription(), x.getCalories(), true))
                .collect(Collectors.toList());
    }
}

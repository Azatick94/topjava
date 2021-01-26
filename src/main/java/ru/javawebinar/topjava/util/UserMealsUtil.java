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
        // TODO return filtered list with excess. Implement by cycles

        // preparing Dictionary with calories
        Map<LocalDate, Integer> caloriesEachDay = new HashMap();
        for (UserMeal currentUserMeal : meals) {
            caloriesEachDay.merge(currentUserMeal.getDateTime().toLocalDate(),
                    currentUserMeal.getCalories(), Integer::sum);
        }

        List<UserMealWithExcess> filteredMeals = new ArrayList<>();
        for (UserMeal currentUserMeal : meals) {
            // check by sum calories per day AND filtering by Time
            if (caloriesEachDay.get(currentUserMeal.getDateTime().toLocalDate()) >= caloriesPerDay &&
                    TimeUtil.isBetweenHalfOpen(currentUserMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                filteredMeals.add(new UserMealWithExcess(currentUserMeal.getDateTime(), currentUserMeal.getDescription(), currentUserMeal.getCalories(), true));
            }
        }

        return filteredMeals;
    }


    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams

        Map<String, Integer> caloriesEachDay = meals.stream()
                .collect(Collectors.toMap(
                        p->p.getDateTime().toLocalDate().toString(),
                        k->k.getCalories(),
                        (a, b) -> a+b
                ));

        List<UserMealWithExcess> filteredMeals =  meals.stream()
                .filter(x -> caloriesEachDay.containsKey(x.getDateTime().toLocalDate().toString()))
                .filter(x -> caloriesEachDay.get(x.getDateTime().toLocalDate().toString())>=caloriesPerDay)
                .filter(x-> TimeUtil.isBetweenHalfOpen(x.getDateTime().toLocalTime(), startTime, endTime))
                .map(x-> new UserMealWithExcess(x.getDateTime(), x.getDescription(), x.getCalories(), true))
                .collect(Collectors.toList());

        return filteredMeals;
    }
}

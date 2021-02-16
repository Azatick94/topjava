package ru.javawebinar.topjava.util;

import junit.framework.TestCase;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class DateTimeUtilTest extends TestCase {

//
//    public void testIsBetweenHalfOpen() {
//
//        List<Meal> meals = MealsUtil.meals;
//
//        List<Meal> lstFilteredByTime = meals.stream()
//                .filter(m -> DateTimeUtil.isBetweenHalfOpen(LocalTime.of(10, 0, 0), LocalTime.of(19, 0, 0), 1))
//                .collect(Collectors.toList());
//
//        List<Meal> lstFilteredByDay = meals.stream()
//                .filter(m -> DateTimeUtil.isBetweenHalfOpen(LocalDate.of(2020, 1, 30), LocalDate.of(2020, 1, 30), 1))
//                .collect(Collectors.toList());
//
//        System.out.println();
//
//
//    }
}
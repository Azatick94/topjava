package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository.USER_ID;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);

    // Map with following structure -> {userId: {mealId: Meal}}
    private final Map<Integer, Map<Integer, Meal>> mealsMap = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        // adding init meals with userId=1;
        MealsUtil.meals.forEach(m -> save(m, USER_ID));
    }

    @Override
    public Meal save(Meal meal, int userId) {

        Map<Integer, Meal> meals = mealsMap.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meals.put(meal.getId(), meal);
            return meal;
        }
        return meals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {

        Map<Integer, Meal> meals = mealsMap.get(userId);
        return meals == null ? null : meals.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {

        Map<Integer, Meal> meals = mealsMap.get(userId);
        return meals == null ? null : meals.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getFilteredPredicate(userId, m -> true);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDate startDate, LocalDate endDate, int userId) {
        return getFilteredPredicate(userId, m -> DateTimeUtil.isBetweenHalfOpen(m.getDate(), startDate, endDate));

    }

    private List<Meal> getFilteredPredicate(int userId, Predicate<Meal> filter) {
        Map<Integer, Meal> meals = mealsMap.get(userId);

        return meals.values().stream()
                .filter(filter)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }


}


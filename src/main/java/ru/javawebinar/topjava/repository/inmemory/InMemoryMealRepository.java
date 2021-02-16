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
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);

    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        // adding init meals with userId=1;
        MealsUtil.meals.forEach(m -> save(m, 1));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.info("save {}", meal);

        try {
            if (meal.isNew()) {
                meal.setId(counter.incrementAndGet());
                meal.setUserId(userId);
                repository.put(meal.getId(), meal);
                return meal;
            }
            // handle case: update, but not present in storage
            if (meal.getUserId() == userId) {
                return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
            } else {
                return null;
            }
        } catch (NullPointerException e) {
            return null;
        }

    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {}", id);

        Meal meal = repository.get(id);

        try {
            if (meal.getUserId() == userId) {
                return repository.remove(id) != null;
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            return false;
        }

    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {}", id);
        Meal meal = repository.get(id);

        try {
            if (meal.getUserId() == userId) {
                return meal;
            } else {
                return null;
            }
        } catch (NullPointerException e) {
            return null;
        }

    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll");
        return repository.values().stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Get List of Meals filtered by Date and sorted by Descending DateTime.
     *
     * @param startDate
     * @param endDate
     * @param userId
     * @return
     */
    @Override
    public List<Meal> getFiltered(LocalDate startDate, LocalDate endDate, int userId) {
        log.info("getFiltered");

        return getAll(userId).stream()
                .filter(m -> DateTimeUtil.isBetweenHalfOpen(m.getDate(), startDate, endDate))
                .collect(Collectors.toList());
    }


}


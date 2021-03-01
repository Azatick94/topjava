package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            User u = em.getReference(User.class, userId);
            meal.setUser(u);
            em.persist(meal);
            return meal;
        } else {
            Meal found = em.find(Meal.class, meal.getId());
            if (found.getUser().getId() == userId) {
                meal.setUser(found.getUser());
                return em.merge(meal);
            } else {
                return null;
            }
        }
//        if (meal.isNew()) {
//            User u = em.getReference(User.class, userId);
//            meal.setUser(u);
//            em.persist(meal);
//        } else {
//            int result = em.createNamedQuery(Meal.UPDATE)
//                    .setParameter("description", meal.getDescription())
//                    .setParameter("calories", meal.getCalories())
//                    .setParameter("date_time", meal.getDateTime())
//                    .setParameter("id", meal.getId())
//                    .setParameter("user_id", userId)
//                    .executeUpdate();
//            if (result == 0) {
//                return null;
//            } else {
//                return meal;
//            }
//            //            return em.merge(meal);
//        }
//        return meal;

    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("user_id", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = em.find(Meal.class, id);
        if (meal != null) {
            return meal.getUser().getId() == userId ? meal : null;
        } else {
            return null;
        }
//        List<Meal> result = em.createNamedQuery(Meal.GET_BY_ID_USER_ID, Meal.class)
//                .setParameter("id", id)
//                .setParameter("user_id", userId)
//                .getResultList();
//        return DataAccessUtils.singleResult(result);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
                .setParameter("user_id", userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return em.createNamedQuery(Meal.GET_BETWEEN, Meal.class)
                .setParameter("user_id", userId)
                .setParameter("startDateTime", startDateTime)
                .setParameter("endDateTime", endDateTime)
                .getResultList();
    }
}
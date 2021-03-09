package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:user_id")
    int delete(@Param("id") int id, @Param("user_id") int userId);

    @Query(value = "SELECT * FROM meals m WHERE m.user_id=:user_id AND date_time>=:startDateTime AND date_time<:endDateTime " +
            "ORDER BY date_time DESC",
            nativeQuery = true)
    List<Meal> getBetweenHalfOpen(@Param("startDateTime") LocalDateTime startDateTime,
                                  @Param("endDateTime") LocalDateTime endDateTime,
                                  @Param("user_id") int userId);

    @Query(value = "SELECT * FROM meals m WHERE m.user_id=:user_id ORDER BY date_time DESC",
            nativeQuery = true)
    List<Meal> getAll(@Param("user_id") int userId);

}

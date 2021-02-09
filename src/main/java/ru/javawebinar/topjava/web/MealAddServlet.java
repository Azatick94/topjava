package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealsInMemoryDB;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class MealAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        try {
            LocalDateTime date = LocalDateTime.parse(request.getParameter("date"));
            String description = request.getParameter("description");
            int calories = Integer.parseInt(request.getParameter("calories"));

            MealsInMemoryDB.getInstance().add(new Meal(date, description, calories));

            List<Meal> meals = MealsInMemoryDB.getInstance().findAll();
            List<MealTo> mealsTo = MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, 2000);

            request.setAttribute("mealsList", mealsTo);

            request.getRequestDispatcher("/meals_success.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("/meals_failure.jsp").forward(request, response);
        }


    }

}

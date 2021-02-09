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
import java.time.LocalTime;
import java.util.List;

public class MealServlet extends HttpServlet {

    private final static String LIST_MEAL = "/meals.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (action != null) {
            if (action.equalsIgnoreCase("delete")) {
                int userId = Integer.parseInt(request.getParameter("userId"));
                MealsInMemoryDB.getInstance().deleteById(userId);
            }
        }

        List<Meal> meals = MealsInMemoryDB.getInstance().findAll();
        List<MealTo> mealsTo = MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, 2000);

        request.setAttribute("mealsList", mealsTo);

        request.getRequestDispatcher(LIST_MEAL).forward(request, response);
    }
}

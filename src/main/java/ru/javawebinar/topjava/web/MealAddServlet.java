package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.MealService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class MealAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        try {
            LocalDateTime date = LocalDateTime.parse(request.getParameter("date"));
            String description = request.getParameter("description");
            int calories = Integer.parseInt(request.getParameter("calories"));

            new MealService().addMeal(date, description, calories);

            List<MealTo> meals = new MealService().findAllMeals();
            request.setAttribute("mealsList", meals);

            request.getRequestDispatcher("/meals_success.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("/meals_failure.jsp").forward(request, response);
        }


    }

}

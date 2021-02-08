package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.service.MealService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MealServlet extends HttpServlet {

    private final static String LIST_MEAL = "/meals.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");

        MealService mealService = new MealService();
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            mealService.deleteMealById(userId);
        }
        request.setAttribute("mealsList", mealService.findAllMeals());

        request.getRequestDispatcher(LIST_MEAL).forward(request, response);
    }
}

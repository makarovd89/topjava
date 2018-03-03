package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.MockMealDAO;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.getFilteredWithExceeded;

public class MealServlet extends HttpServlet{
    private static final Logger log = getLogger(MealServlet.class);

    private MealDAO mealDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        mealDAO = new MockMealDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);
        switch (action) {
            case "create": {
                log.debug("forward to mealForm(create)");
                req.setAttribute("meal", new Meal(null, LocalDateTime.now(), "", 500));
                req.getRequestDispatcher("/mealForm.jsp").forward(req, resp);
            } break;
            case "update":{
                log.debug("forward to mealForm(update)");
                req.setAttribute("meal", mealDAO.get(getId(req)));
                req.getRequestDispatcher("/mealForm.jsp").forward(req, resp);
            } break;
            case "delete": {
                int id = getId(req);
                log.debug("delete Meal id: {}", id);
                mealDAO.delete(id);
                resp.sendRedirect("meals");
            } break;
            default: {
                log.debug("forward to meals");
                List<MealWithExceed> mealsWithExceeded = getFilteredWithExceeded(mealDAO.getAll(), LocalTime.MIN,LocalTime.MAX,2000);
                req.setAttribute("meals",mealsWithExceeded);
                req.getRequestDispatcher("/meals.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.debug("doPost()");
        req.setCharacterEncoding("UTF-8");
        Meal meal =  new Meal(getId(req), LocalDateTime.parse(req.getParameter("dateTime")),
                req.getParameter("description"), Integer.parseInt(req.getParameter("calories")));
        if (meal.getId()==null){
            log.debug("create Meal {}", meal);
            mealDAO.create(meal);
        } else {
            log.debug("update Meal {}", meal);
            mealDAO.update(meal);
        }
        log.debug("redirect to meals");
        resp.sendRedirect("meals");
    }

    private Integer getId(HttpServletRequest req){
        String id = req.getParameter("id");
        return (id == null || id.isEmpty()) ? null : Integer.parseInt(id);
    }

    private String getAction(HttpServletRequest req){
        String action = req.getParameter("action");
        return action != null ? action : "";
    }
}

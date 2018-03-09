package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;
import static ru.javawebinar.topjava.util.MealsUtil.getWithExceeded;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;

@Controller
public class MealRestController {
    @Autowired
    private MealService service;

    public Meal create(Meal meal) {
        return service.create(meal, AuthorizedUser.id());
    }

    public void delete(int id) throws NotFoundException {
        service.delete(id, AuthorizedUser.id());
    }

    public Meal get(int id) throws NotFoundException {
        return service.get(id, AuthorizedUser.id());
    }

    public void update(Meal meal, int id) {
        assureIdConsistent(meal, id);
        service.update(meal, AuthorizedUser.id());
    }

    public List<MealWithExceed> getAll() {
        return getWithExceeded(service.getAll(AuthorizedUser.id()), DEFAULT_CALORIES_PER_DAY);
    }
}
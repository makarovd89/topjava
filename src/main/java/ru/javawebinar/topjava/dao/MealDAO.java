package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import java.util.List;

public interface MealDAO {
    void create(Meal meal);

    void update(Meal meal);

    List<Meal> getAll();

    Meal get(int id);

    void delete(int id);
}
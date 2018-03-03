package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MockMealDAO implements MealDAO {

    private AtomicInteger counter =  new AtomicInteger(0);

    private ConcurrentHashMap<Integer,Meal> mealsMap = new ConcurrentHashMap<>();

    public MockMealDAO() {
        MealsUtil.getMeals().forEach(this::create);
    }

    private int generateId(){
        return counter.getAndIncrement();
    }

    @Override
    public void create(Meal meal) {
        int id = generateId();
        meal.setId(id);
        mealsMap.put(id, meal);
    }

    @Override
    public void update(Meal meal) {
        mealsMap.put(meal.getId(), meal);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(mealsMap.values());
    }

    @Override
    public Meal get(int id) {
        return mealsMap.get(id);
    }

    @Override
    public void delete(int id) {
        mealsMap.remove(id);
    }
}

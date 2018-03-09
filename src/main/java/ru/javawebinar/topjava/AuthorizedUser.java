package ru.javawebinar.topjava;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class AuthorizedUser {
    private static int authorizedId = 1;

    public static int id() {
        return authorizedId;
    }

    public static int getCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }

    public static void setId(int id){
        authorizedId = id;
    }
}
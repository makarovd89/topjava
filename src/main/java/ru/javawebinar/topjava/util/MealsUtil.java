package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.*;
import java.util.*;

import static java.util.stream.Collectors.*;

public class MealsUtil {

    public static void main(String[] args) {
        List<Meal> mealList = Arrays.asList(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед´", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000).forEach(System.out::println);
    }

    public static List<MealWithExceed> getFilteredWithExceeded(List<Meal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> map = mealList.stream()
            .collect(groupingBy(um -> um.getDateTime().toLocalDate(), summingInt(Meal::getCalories)));

        return mealList.stream()
            .filter((um) -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
            .map(um -> new MealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(), (((Integer) map.get(um.getDateTime().toLocalDate()) > caloriesPerDay))))
            .collect(toList());
    }
}

package ru.javawebinar.topjava.service.meal;

import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles({Profiles.DATAJPA})
public class MealServiceSpringDataJpaTest extends AbstractMealServiceTest {
    @Test
    public void testGetWithUser() throws Exception {
        Meal actual = mealService.getMealByIdWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        assertMatch(actual, ADMIN_MEAL1);
        assertMatch(actual.getUser(),ADMIN);
    }

    @Test
    public void testGetWithUserNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        mealService.getMealByIdWithUser(ADMIN_MEAL_ID, USER_ID);
    }
}

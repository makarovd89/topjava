package ru.javawebinar.topjava.service.user;

import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import org.springframework.test.context.ActiveProfiles;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static ru.javawebinar.topjava.UserTestData.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

@ActiveProfiles({Profiles.DATAJPA})
public class UserServiceSpringDataJpaTest extends AbstractUserServiceTest {
    @Test
    public void getWithMeals() throws Exception {
        User user = userService.getUserWithMeals(USER_ID);
        User expected = new User(USER);
        expected.setMeals(MEALS);
        assertMatch(user, USER);
        assertMatch(user.getMeals(),expected.getMeals());
    }

    @Test
    public void getWithEmptyMeals() throws Exception {
        User newUser = new User(START_SEQ + 10, "User", "user2@yandex.ru", "password", Role.ROLE_USER);
        userService.create(newUser);
        User user = userService.getUserWithMeals(START_SEQ + 10);
        assertTrue(user.getMeals().isEmpty());
    }

    @Test
    public void getByIdWithMealsNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        userService.getUserWithMeals(START_SEQ + 11);
    }
}
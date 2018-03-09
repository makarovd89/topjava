package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.AbstractNamedEntity;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static ru.javawebinar.topjava.model.Role.ROLE_ADMIN;
import static ru.javawebinar.topjava.model.Role.ROLE_USER;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private Map<Integer, User> repository = new ConcurrentHashMap<>();

    private AtomicInteger counter = new AtomicInteger(0);

    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    {
        save(new User(null, "User", "user@yahoo.com", "password", ROLE_USER));
        save(new User(null, "Admin", "admin@yahoo.com", "password", ROLE_ADMIN));
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return repository.remove(id) != null;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (user.isNew()){
            user.setId(counter.incrementAndGet());
            repository.put(user.getId(), user);
            return user;
        }
        return repository.computeIfPresent(user.getId(), (id, oldMeal) -> user);
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return repository.values().stream()
                .sorted(comparing(AbstractNamedEntity::getName))
                .collect(toList());
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return repository.values().stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst().orElse(null);
    }
}

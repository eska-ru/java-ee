package ru.writeway.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TemporaryUserRepository extends UserRepository {
    private final Map<Long, User> userMap = new ConcurrentHashMap<>();
    private final AtomicLong identity = new AtomicLong(0);

    public TemporaryUserRepository() {
        saveOrUpdate(new User(null, "Имя 1", "Фамилия 1", "Почта 1", "+70000001"));
        saveOrUpdate(new User(null, "Имя 2", "Фамилия 2", "Почта 2", "+70000002"));
        saveOrUpdate(new User(null, "Имя 3", "Фамилия 3", "Почта 3", "+70000003"));
        saveOrUpdate(new User(null, "Имя 4", "Фамилия 4", "Почта 4", "+70000004"));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public User findById(Long id) {
        return userMap.get(id);
    }

    @Override
    public void saveOrUpdate(User user) {
        if (user == null) {
            return;
        }

        if (user.getId() == null) {
            Long id = identity.incrementAndGet();
            user.setId(id);
        }
        userMap.put(user.getId(), user);
    }

    @Override
    public Boolean deleteById(Long id) {
        if (!userMap.containsKey(id)) {
            return false;
        }
        userMap.remove(id);
        return true;
    }
}
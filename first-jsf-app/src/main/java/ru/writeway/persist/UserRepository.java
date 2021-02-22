package ru.writeway.persist;

import java.util.List;

abstract public class UserRepository {
    abstract public List<User> findAll();
    abstract public User findById(Long id);
    abstract public void saveOrUpdate(User user);
    abstract public Boolean deleteById(Long id);
}

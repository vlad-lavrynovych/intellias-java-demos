package com.demo.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class UserDaoService {

    private static int usersCount = 0;

    private static final List<User> USERS = new ArrayList<>(List.of(
            new User(++usersCount, "Adam", LocalDate.now().minusYears(30)),
            new User(++usersCount, "Eve", LocalDate.now().minusYears(25)),
            new User(++usersCount, "Jim", LocalDate.now().minusYears(20))));

    public List<User> findAll() {
        return USERS;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return USERS.stream().filter(predicate).findFirst().orElseThrow(UserNotFoundException::new);
    }

    public void deleteById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        USERS.removeIf(predicate);
    }

    public User save(User user) {
        user.setId(++usersCount);
        USERS.add(user);
        return user;
    }
}

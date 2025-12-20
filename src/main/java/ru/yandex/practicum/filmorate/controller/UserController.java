package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final List<User> users = new ArrayList<>();
    private int nextId = 1;

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }

        user.setId(nextId++);
        users.add(user);
        return user;
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {

                if (user.getName() == null || user.getName().isBlank()) {
                    user.setName(user.getLogin());
                }

                users.set(i, user);
                log.info("Обновлен пользователь: {}", user);
                return user;
            }
        }

        log.warn("Ошибка обновления пользователя. id {} не найден", user.getId());
        throw new ValidationException("Пользователь с id " + user.getId() + " не найден");
    }

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }
}
package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.dto.user.NewUserRequest;
import ru.yandex.practicum.filmorate.dao.dto.user.UpdateUserRequest;
import ru.yandex.practicum.filmorate.dao.dto.user.UserDto;
import ru.yandex.practicum.filmorate.dao.dto.user.UserMapper;
import ru.yandex.practicum.filmorate.dao.repository.FriendshipRepository;
import ru.yandex.practicum.filmorate.dao.repository.UserRepository;
import ru.yandex.practicum.filmorate.exception.InternalServerException;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final FriendshipRepository friendshipRepository;

    public UserDto create(NewUserRequest request) {
        log.info("Create user login={}", request.getLogin());

        User user = UserMapper.mapToUser(request);
        user = userRepository.create(user);
        return UserMapper.mapToUserDto(user);
    }

    public UserDto update(long userId, UpdateUserRequest request) {
        log.debug("Updating user id={}", request.getId());

        User updatedUser = userRepository.getById(userId)
                .map(user -> UserMapper.updateUserFields(user, request))
                .orElseThrow(() -> new NotFoundException("User with id " + userId + " not found"));
        updatedUser = userRepository.update(updatedUser);

        log.info("User updated successfully id={}", request.getId());
        return UserMapper.mapToUserDto(updateCollections(updatedUser, updatedUser.getId()));
    }

    public Collection<UserDto> getAll() {
        return userRepository.getAll().stream()
                .map(user -> updateCollections(user, user.getId()))
                .map(UserMapper::mapToUserDto)
                .toList();
    }

    public void delete(long id) {
        getById(id);
        boolean deleted = userRepository.delete(id);
        if (!deleted) {
            throw new InternalServerException("Failed to delete user with id=" + id);
        }
    }

    public UserDto getById(long id) {
        User user = userRepository.getById(id)
                .orElseThrow(() -> {
                    log.warn("User not found id={}", id);
                    return new NotFoundException("User not found");
                });

        return UserMapper.mapToUserDto(updateCollections(user, id));
    }

    public void validateUserExists(long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User with id " + userId + " not found");
        }
    }

    public User updateCollections(User user, long userId) {
        user.setFriends(friendshipRepository.findAllByUserId(userId));
        return user;
    }
}
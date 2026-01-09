package ru.yandex.practicum.filmorate.storage.friend;

import ru.yandex.practicum.filmorate.model.Friendship;

import java.util.Collection;
import java.util.Optional;

public interface FriendshipStorage {

    void save(Friendship friendship);

    void delete(long userId, long friendId);

    Collection<Friendship> findAllByUserId(long userId);
}
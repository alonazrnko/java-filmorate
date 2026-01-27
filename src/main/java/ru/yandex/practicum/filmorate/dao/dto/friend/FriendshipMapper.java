package ru.yandex.practicum.filmorate.dao.dto.friend;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.filmorate.model.Friendship;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FriendshipMapper {

    public static Friendship mapToFriendship(Long userId, NewFriendshipRequest request) {
        Friendship friendship = new Friendship();
        friendship.setUserId(userId);
        friendship.setFriendId(request.getFriendId());
        return friendship;
    }

    public static FriendshipDto mapToFriendDto(Friendship friendship) {
        FriendshipDto dto = new FriendshipDto();
        dto.setUserId(friendship.getUserId());
        dto.setFriendId(friendship.getFriendId());
        return dto;
    }
}
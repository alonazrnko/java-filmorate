package ru.yandex.practicum.filmorate.dao.dto.friend;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class NewFriendshipRequest {
    @NotNull(message = "User ID cannot be empty")
    @Positive(message = "User ID must be positive")
    private Long userId;

    @NotNull(message = "Friend ID cannot be empty")
    @Positive(message = "Friend ID must be positive")
    private Long friendId;
}

package ru.yandex.practicum.filmorate.dao.dto.like;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class NewLikeRequest {
    @NotNull(message = "Film ID cannot be empty")
    @Positive(message = "Film ID must be positive")
    private Long filmId;

    @NotNull(message = "User ID cannot be empty")
    @Positive(message = "User ID must be positive")
    private Long userId;
}

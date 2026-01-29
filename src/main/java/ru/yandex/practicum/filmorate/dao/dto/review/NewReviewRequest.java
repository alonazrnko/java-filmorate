package ru.yandex.practicum.filmorate.dao.dto.review;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NewReviewRequest {
    @NotBlank(message = "The content cannot be empty")
    private String content;

    @NotNull(message = "The content type is required")
    private Boolean isPositive;

    @NotNull(message = "User ID is required")

    private Long userId;

    @NotNull(message = "Film ID is required")

    private Long filmId;
}

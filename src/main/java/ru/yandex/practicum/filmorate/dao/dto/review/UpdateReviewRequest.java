package ru.yandex.practicum.filmorate.dao.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdateReviewRequest {
    @NotNull(message = "Review ID cannot be empty")
    @Positive(message = "Review ID must be positive")
    private Long reviewId;

    @NotBlank(message = "The content cannot be empty")
    private String content;

    @NotNull(message = "The content type is required")
    private Boolean isPositive;

    public boolean hasContent() {
        return content != null && !content.isBlank();
    }

    public boolean hasIsPositive() {
        return isPositive != null;
    }
}

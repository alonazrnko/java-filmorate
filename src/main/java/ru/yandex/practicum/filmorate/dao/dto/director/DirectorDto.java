package ru.yandex.practicum.filmorate.dao.dto.director;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DirectorDto {
    @NotNull(message = "Director ID cannot be empty")
    @Positive(message = "Director ID must be positive")
    private Long id;

    @NotBlank(message = "Director's name cannot be empty")
    private String name;
}

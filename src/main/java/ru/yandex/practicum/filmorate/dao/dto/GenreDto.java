package ru.yandex.practicum.filmorate.dao.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GenreDto {
    @NotNull
    private Long id;
}

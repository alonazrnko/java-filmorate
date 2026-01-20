package ru.yandex.practicum.filmorate.dao.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MpaDto {
    @NotNull
    private Long id;
}
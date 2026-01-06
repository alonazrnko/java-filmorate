package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import ru.yandex.practicum.filmorate.validation.ReleaseDateConstraint;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class Film {

    private long id;

    @NotBlank
    private String name;

    @Size(max = 200)
    private String description;

    @NotNull
    @ReleaseDateConstraint
    private LocalDate releaseDate;

    @Positive
    private int duration;

    private Set<Genre> genres = new HashSet<>();

    @NotNull(message = "MPA рейтинг обязателен")
    private MpaRating mpa;

    private Set<Long> likes = new HashSet<>();
}
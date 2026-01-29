package ru.yandex.practicum.filmorate.dao.dto.director;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class NewDirectorRequest {

    @NotBlank(message = "Director's name cannot be empty")
    private String name;
}

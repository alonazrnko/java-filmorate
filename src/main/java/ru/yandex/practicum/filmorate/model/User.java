package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "Логин не может содержать пробелы")
    private String login;

    // может быть пустым → тогда используем login
    private String name;

    @PastOrPresent
    private LocalDate birthday;
}

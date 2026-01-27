package ru.yandex.practicum.filmorate.dao.dto.user;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateUserRequest {
    @NotNull(message = "ID is required")
    @Positive(message = "ID must be positive")
    private Long id;

    @Email(message = "Incorrect email format")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Login cannot be empty")
    @Pattern(regexp = "\\S+", message = "The login cannot contain spaces")
    private String login;

    private String name;

    @Past(message = "The date of birth cannot be in the future")
    private LocalDate birthday;

    public boolean hasEmail() {
        return email != null && !email.isBlank();
    }

    public boolean hasLogin() {
        return login != null && !login.isBlank();
    }

    public boolean hasName() {
        return name != null && !name.isBlank();
    }

    public boolean hasBirthday() {
        return birthday != null;
    }
}
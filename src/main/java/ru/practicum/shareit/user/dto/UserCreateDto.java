package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class UserCreateDto {
    @Email(message = "Некорректный формат емэйла")
    @NotBlank(message = "Email обязательный")
    private String email;
    @NotBlank(message = "Name обязательный")
    private String name;
}

package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;


@Getter
public class UserUpdateDto {
    @Email(message = "Некорректный формат емэйла")
    private String email;
    private String name;
}

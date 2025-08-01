package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentCreateDto {
    @NotBlank(message = "Пустой комментарий")
    private String text;
}

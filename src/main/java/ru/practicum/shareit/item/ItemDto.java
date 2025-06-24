package ru.practicum.shareit.item;

import lombok.Data;

@Data
public class ItemDto {
    private String name;
    private String description;
    private Boolean available;
    private Long request;

    public ItemDto(String name, String description, boolean available, Long request) {
        this.name = name;
        this.description = description;
        this.available = available;
        this.request = request;
    }
}

package ru.practicum.shareit.item;

public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        return new ItemDto(
                item.getName(),
                item.getDescription(),
                item.getAvailable() != null ? item.getAvailable() : null,
                item.getRequest() != null ? item.getRequest().getId() : null
        );
    }

}

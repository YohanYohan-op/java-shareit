package ru.practicum.shareit.item;

import java.util.List;

public interface ItemService {
    ItemDto addNewItem(Long userId, ItemDto item);

    List<ItemDto> getItems(Long userId);

    ItemDto getItem(Long itemId);

    ItemDto updateItem(Long userId, Long itemId, ItemDto item);

    List<ItemDto> findItemForNameOrDescription(String text);

    void deleteItem(Long userId, Long itemId);
}

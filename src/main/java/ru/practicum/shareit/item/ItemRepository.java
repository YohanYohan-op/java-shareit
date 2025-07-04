package ru.practicum.shareit.item;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository {

    List<Item> findByUserId(Long userId);

    Item findByItemId(Long itemId);

    List<Item> findForNameOrDesc(String text);
    /*List<Item> findAll();*/
    /*Item update(Long userId, Item item);*/

    Item update(Long userId, Long itemId, Item item);

    Item save(Item item);

    void deleteByUserIdAndItemId(Long userId, Long itemId);
}

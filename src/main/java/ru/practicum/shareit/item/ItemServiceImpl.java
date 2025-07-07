package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.exceptions.UserNotFoundException;
import ru.practicum.shareit.user.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserService userService;


    public ItemDto addNewItem(Long userId, ItemDto item) {
        if (userService.getAllUsers().stream().noneMatch(u -> u.getId().equals(userId))) {
            throw new UserNotFoundException("User not found");
        }
        item.setUserId(userId);
        return ItemMapper.toItemDto(itemRepository.save(ItemMapper.toItem(item)));
    }

    public List<ItemDto> getItems(Long userId) {
        return itemRepository.findByUserId(userId).stream().map(ItemMapper::toItemDto).toList();
    }

    public List<ItemDto> findItemForNameOrDescription(String text) {
        if (text == null || text.isBlank()) {
            return new ArrayList<>();
        }
        return itemRepository.findForNameOrDesc(text).stream().map(ItemMapper::toItemDto).toList();
    }

    public ItemDto updateItem(Long userId, Long itemId, ItemDto item) {
        if (userService.getAllUsers().stream().noneMatch(u -> u.getId().equals(userId))) {
            throw new UserNotFoundException("User not found");
        }
        if (userId <= 0 || itemId < 0) {
            throw new IllegalArgumentException("Invalid userId or itemId");
        }
        return ItemMapper.toItemDto(itemRepository.update(userId, itemId, ItemMapper.toItem(item)));
    }

    public ItemDto getItem(Long itemId) {
        return ItemMapper.toItemDto(itemRepository.findByItemId(itemId));
    }

    public void deleteItem(Long userId, Long itemId) {
        itemRepository.deleteByUserIdAndItemId(userId, itemId);
    }

}

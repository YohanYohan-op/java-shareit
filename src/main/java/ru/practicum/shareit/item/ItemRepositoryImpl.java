package ru.practicum.shareit.item;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ItemRepositoryImpl implements ItemRepository {
    Map<Long, List<Item>> storage = new HashMap<>();
    private long lastId = 1;

    @Override
    public List<Item> findByUserId(Long userId) {
        return storage.get(userId);
    }

    @Override
    public Item findByItemId(Long itemId) {
        for (List<Item> items : storage.values()) {
            for (Item item : items) {
                if (item.getId() == itemId) {
                    return item;
                }
            }
        }
        return null;
    }

    /*@Override
    public Item update(Long userId, Item item) {
        Item updated = storage.get(userId).stream().filter(i -> i.getName().toLowerCase().equals(item.getName().toLowerCase())).findFirst().orElse(null);
        if (updated == null) {
            throw new ItemNotFoundException("Item not found", item);
        }
        updated.setDescription(item.getDescription() == null ? updated.getDescription() : item.getDescription());
        updated.setAvailable(item.getAvailable() == null ? updated.getAvailable() : item.getAvailable());
        updated.setOwner(item.getOwner() == null ? updated.getOwner() : item.getOwner());
        updated.setRequest(item.getRequest() == null ? updated.getRequest() : item.getRequest());
        storage.get(userId).set(updated.getId().intValue(), updated);
        return updated;
    }*/

    @Override
    public Item update(Long userId, Long itemId, Item item) {
        for (Item i : storage.get(userId)) {
            int index = storage.get(userId).indexOf(i);
            if (userId == i.getUserId() && itemId == i.getId()) {
                Item updated = storage.get(userId).get(index);
                updated.setName(item.getName() == null ? updated.getName() : item.getName());
                updated.setDescription(item.getDescription() == null ? updated.getDescription() : item.getDescription());
                updated.setAvailable(item.getAvailable() == null ? updated.getAvailable() : item.getAvailable());
                updated.setOwner(item.getOwner() == null ? updated.getOwner() : item.getOwner());
                updated.setRequest(item.getRequest() == null ? updated.getRequest() : item.getRequest());
                storage.get(userId).set(index, updated);
                return updated;
            }
        }
        return null;
    }

    @Override
    public List<Item> findForNameOrDesc(String text) {
        List<Item> result = new ArrayList<>();
        for (List<Item> items : storage.values()) {
            for (Item item : items) {
                if (Boolean.TRUE.equals(item.getAvailable())
                    && (item.getName().toLowerCase().contains(text.toLowerCase())
                        || item.getDescription().toLowerCase().contains(text.toLowerCase()))) {
                    result.add(item);
                }
            }
        }
        return result;
    }
    /*@Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        for (List<Item> items : storage.values()) {
            for (Item item : items) {
                if (Boolean.TRUE.equals(item.getAvailable())) {
                    result.add(item);
                }
            }
        }
        return result;
    }*/

    @Override
    public Item save(Item item) {
        if (storage.containsKey(item.getUserId())) {
            item.setId(getId());
            storage.get(item.getUserId()).add(item);
            return item;
        } else {
            item.setId(getId());
            ArrayList<Item> items = new ArrayList<>();
            items.add(item);
            storage.put(item.getUserId(), items);
            return item;
        }
    }

    @Override
    public void deleteByUserIdAndItemId(Long userId, Long itemId) {
        storage.get(userId).removeIf(item -> item.getId() == itemId);
    }

    private long getId() {
        return lastId++;
    }
}

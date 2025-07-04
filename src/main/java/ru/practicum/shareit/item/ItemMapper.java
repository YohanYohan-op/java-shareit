package ru.practicum.shareit.item;

public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        return new ItemDto(
                item.getId() != null ? item.getId() : null,
                item.getUserId() != null ? item.getUserId() : null,
                item.getName() != null ? item.getName() : null,
                item.getDescription() != null ? item.getDescription() : null,
                item.getAvailable() != null ? item.getAvailable() : null,
                item.getOwner() != null ? item.getOwner() : null,
                item.getRequest() != null ? item.getRequest() : null
        );
    }

    public static Item toItem(ItemDto itemDto) {
        Item item = new Item();

        item.setId(itemDto.getId());
        item.setUserId(itemDto.getUserId());
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setAvailable(itemDto.getAvailable());
        item.setOwner(itemDto.getOwner());
        item.setRequest(itemDto.getRequest() != null ? itemDto.getRequest() : null);

        return item;
    }

}

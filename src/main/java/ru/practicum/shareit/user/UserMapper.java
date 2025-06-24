package ru.practicum.shareit.user;

public class UserMapper {
    public static UserDto toItemDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getName()
        );
    }
}

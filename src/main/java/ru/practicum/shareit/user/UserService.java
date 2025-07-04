package ru.practicum.shareit.user;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto saveUser(UserDto user);

    void deleteUser(Long userId);

    UserDto update(UserDto user, Long userId);

    UserDto getUser(Long userId);
}

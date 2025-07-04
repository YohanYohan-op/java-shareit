package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.exceptions.UserInvalidEmailNotFoundException;
import ru.practicum.shareit.user.exceptions.UserInvalidException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public List<UserDto> getAllUsers() {
        return repository.findAll().stream().map(UserMapper::toUserDto).toList();
    }

    @Override
    public UserDto getUser(Long userId) {
        if (userId == null || userId <= 0 || repository.findAll().getLast().getId() < userId || repository.findAll().stream().noneMatch(u -> u.getId().equals(userId))) {
            throw new UserInvalidEmailNotFoundException("User with id = " + userId + " not found");
        }
        return UserMapper.toUserDto(repository.getUser(userId));
    }

    @Override
    public void deleteUser(Long userId) {
        if (userId == null || userId <= 0 || repository.findAll().getLast().getId() < userId) {
            throw new UserInvalidEmailNotFoundException("User with id = " + userId + " not found");
        }
        repository.deleteUser(userId);
    }

    @Override
    public UserDto saveUser(UserDto user) {
        if (repository.findAll().stream()
                .anyMatch(u -> u.getEmail().equals(user.getEmail()))) {
            throw new UserInvalidException("Email already exists", UserMapper.toUser(user));
        }
        return UserMapper.toUserDto(repository.save(UserMapper.toUser(user)));
    }

    @Override
    public UserDto update(UserDto user, Long userId) {
        if (userId == null || userId <= 0 || repository.findAll().getLast().getId() < userId
            || repository.findAll().stream().noneMatch(u -> u.getId().equals(userId))) {
            throw new UserInvalidEmailNotFoundException("User with id = " + userId + " not found");
        }
        if (user.getEmail() == null && user.getName() == null) {
            throw new UserInvalidEmailNotFoundException("Email or name is blank", UserMapper.toUser(user));
        }
        if (user.getEmail() != null && user.getName() != null && user.getEmail().contains("@")) {
            if (repository.findAll().stream()
                    .anyMatch(u -> u.getEmail().equals(user.getEmail()))) {
                throw new UserInvalidException("Email already exists", UserMapper.toUser(user));
            }
            return repository.update(UserMapper.toUser(user), userId).isPresent() ? UserMapper.toUserDto(repository.update(UserMapper.toUser(user), userId).get()) : null;
        }
        if (user.getEmail() != null && user.getName() == null && user.getEmail().contains("@")) {
            if (repository.findAll().stream()
                    .anyMatch(u -> u.getEmail().equals(user.getEmail()))) {
                throw new UserInvalidException("Email already exists", UserMapper.toUser(user));
            }
            return repository.update(UserMapper.toUser(user), "email", userId).isPresent()
                    ? UserMapper.toUserDto(repository.update(UserMapper.toUser(user), "email", userId).get()) : null;

        }
        if (user.getEmail() == null) {
            return repository.update(UserMapper.toUser(user), "name", userId).isPresent()
                    ? UserMapper.toUserDto(repository.update(UserMapper.toUser(user), "name", userId).get()) : null;
        }
        return null;
    }
}

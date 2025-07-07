package ru.practicum.shareit.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping(path = "/users")
@Validated
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable("userId") Long userId) {
        return userService.getUser(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @PostMapping
    public UserDto saveNewUser(@RequestBody @Valid UserDto user) {
        return userService.saveUser(user);
    }

    @PatchMapping("/{userId}")
    public UserDto updateUser(@RequestBody UserDto user, @PathVariable("userId") Long userId) {
        return userService.update(user, userId);
    }
}

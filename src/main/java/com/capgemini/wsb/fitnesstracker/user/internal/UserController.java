package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;
    private final UserSimpleMapper userSimpleMapper;
    private final UserEmailSimpleMapper userEmailSimpleMapper;


    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }


    @GetMapping("/simple")
    public List<UserSimpleDto> getAllUsersSimple() {
        return userService.findAllUsers()
                .stream()
                .map(userSimpleMapper::convertToSimpleDto)
                .toList();
    }


    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User with ID: " + userId + " not found"));
    }


    @GetMapping("/email")
    public List<UserEmailSimpleDto> getUserByEmail(@RequestParam String email) {
        return userService.findUsersByEmailContainingIgnoreCase(email)
                .stream()
                .map(userEmailSimpleMapper::convertToEmailSimpleDto)
                .toList();
    }


    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate time) {
        return userService.getUsersOlderThan(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserDto userDto) {
        try {
            User user = userMapper.toEntity(userDto);
            return userService.createUser(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot add user with email: " + userDto.userEmail() + " with error: " + e.getMessage());
        }
    }


    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        try {
            User foundUser = userService.getUser(userId).orElseThrow(() -> new IllegalArgumentException("User with ID: " + userId + " not found"));
            User updatedUser = userMapper.updateEntity(userDto, foundUser);
            return userService.updateUser(updatedUser);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot update user with ID: " + userId + " with error: " + e.getMessage());
        }
    }


    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot delete user with ID: " + userId + " with error: " + e.getMessage());
        }
    }
}

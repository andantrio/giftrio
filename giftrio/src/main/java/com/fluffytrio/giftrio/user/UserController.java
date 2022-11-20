package com.fluffytrio.giftrio.user;

import com.fluffytrio.giftrio.user.dto.UserRequestDto;
import com.fluffytrio.giftrio.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping()
    public UserResponseDto addUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.addUser(userRequestDto);
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @GetMapping()
    public List<UserResponseDto> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/{userId}")
    public UserResponseDto updateUser(@PathVariable Long userId, @RequestBody UserRequestDto newUserInfo) {
        return userService.updateUser(userId, newUserInfo);
    }

    @DeleteMapping("/{userId}")
    public boolean deleteUser(@PathVariable Long userId) {
        System.out.println(userId);
        return userService.deleteUser(userId);
    }
}

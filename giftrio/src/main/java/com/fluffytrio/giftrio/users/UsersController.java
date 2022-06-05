package com.fluffytrio.giftrio.users;

import com.fluffytrio.giftrio.users.dto.UsersRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    @PostMapping()
    public Users addUsers(@RequestBody UsersRequestDto usersRequestDto) {
        return usersService.addUsers(usersRequestDto);
    }

    @GetMapping("/{id}")
    public Optional<Users> getUsers(@PathVariable Long id) {
        return usersService.getUsers(id);
    }

    @GetMapping()
    public List<Users> getUsersList() {
        return usersService.getUsersList();
    }

    @PutMapping("/{id}")
    public Users updateUsers(@PathVariable Long id, @RequestBody Users newUsersInfo) {
        return usersService.updateUsers(id, newUsersInfo);
    }

    @DeleteMapping("/{id}")
    public void deleteUsers(@PathVariable Long id) {
        usersService.deleteUsers(id);
    }
}

package com.fluffytrio.giftrio.users;

import com.fluffytrio.giftrio.users.dto.UsersRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    @PostMapping()
    public Users addUsers(@RequestBody UsersRequestDto usersRequestDto) {
        return usersService.addUsers(usersRequestDto);
    }
}

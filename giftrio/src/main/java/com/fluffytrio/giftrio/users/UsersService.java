package com.fluffytrio.giftrio.users;

import com.fluffytrio.giftrio.users.dto.UsersRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public Users addUsers(UsersRequestDto usersRequestDto) {
        return usersRepository.save(usersRequestDto.toEntity());
    }
}

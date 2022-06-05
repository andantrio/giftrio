package com.fluffytrio.giftrio.users;

import com.fluffytrio.giftrio.users.dto.UsersRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;

    @Transactional
    public Users addUsers(UsersRequestDto usersRequestDto) {
        return usersRepository.save(usersRequestDto.toEntity());
    }

    public Optional<Users> getUsers(Long id) {
        return usersRepository.findById(id);
    }

    public List<Users> getUsersList() {
        return usersRepository.findAll();
    }

    @Transactional
    public Users updateUsers(Long id, Users newUsersInfo) {
        return usersRepository.save(newUsersInfo);
    }

    @Transactional
    public void deleteUsers(Long id) {
        usersRepository.deleteById(id);
    }
}

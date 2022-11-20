package com.fluffytrio.giftrio.user;

import com.fluffytrio.giftrio.user.dto.UserRequestDto;
import com.fluffytrio.giftrio.user.dto.UserResponseDto;
import com.fluffytrio.giftrio.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        if (userRepository.getUsersByEmail(userRequestDto.getEmail()).size() > 0) {
            throw new IllegalArgumentException("이미 가입된 메일 주소입니다.");
        }
        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

        User user = userRepository.save(UserMapper.toUser(userRequestDto));

        return UserMapper.toUserResponseDto(user);
    }

    public UserResponseDto getUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return UserMapper.toUserResponseDto(user.get());
        } else {
            return null;
        }
    }

    public List<UserResponseDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toUserResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserResponseDto updateUser(Long userId, UserRequestDto newUserInfo) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User existingUser = user.get();

            User newUser = User.builder()
                    .id(userId)
                    .role(existingUser.getRole())
                    .email(newUserInfo.getEmail() == null ? existingUser.getEmail() : newUserInfo.getEmail())
                    .nickname(newUserInfo.getNickname() == null ? existingUser.getNickname() : newUserInfo.getNickname())
                    .password(newUserInfo.getPassword() == null ? existingUser.getPassword() : newUserInfo.getPassword())
                    .lastLoginTime(existingUser.getLastLoginTime())
                    .isDelete(existingUser.isDelete())
                    .build();
            User updatedUser = userRepository.save(newUser);

            return UserMapper.toUserResponseDto(updatedUser);
        }
        return null;
    }

    @Transactional
    public boolean deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User deletedUser = user.get();
            deletedUser.delete();
            System.out.println(deletedUser);
            return userRepository.save(deletedUser).isDelete();
        }

        return false;
    }
}

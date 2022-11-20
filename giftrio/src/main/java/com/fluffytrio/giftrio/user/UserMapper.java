package com.fluffytrio.giftrio.user;

import com.fluffytrio.giftrio.user.dto.UserRequestDto;
import com.fluffytrio.giftrio.user.dto.UserResponseDto;
import com.fluffytrio.giftrio.user.entity.User;

public class UserMapper {
    public static UserResponseDto toUserResponseDto (User user) {
        if (user == null) {
            return null;
        }

        return UserResponseDto.builder()
                .id(user.getId())
                .role(user.getRole().getAuthority())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }

    public static User toUser(UserRequestDto userRequestDto) {
        return User.builder()
                .id(userRequestDto.getId())
                .role(userRequestDto.getRole())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .nickname(userRequestDto.getNickname())
                .build();
    }
}

package com.fluffytrio.giftrio.user.dto;

import com.fluffytrio.giftrio.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRequestDto {
    private Long id;
    private String email;
    private String userName;
    private String password;

    public User toEntity() {
        return User.builder()
                .id(id)
                .email(email)
                .userName(userName)
                .password(password)
                .build();
    }
}

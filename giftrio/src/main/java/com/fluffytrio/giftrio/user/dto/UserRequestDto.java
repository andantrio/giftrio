package com.fluffytrio.giftrio.user.dto;

import com.fluffytrio.giftrio.user.Role;
import com.fluffytrio.giftrio.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private Long id;
    private Role role;
    private String email;
    private String userName;
    private String password;

    public User toEntity() {
        return User.builder()
                .id(id)
                .role(role)
                .email(email)
                .userName(userName)
                .password(password)
                .build();
    }
}

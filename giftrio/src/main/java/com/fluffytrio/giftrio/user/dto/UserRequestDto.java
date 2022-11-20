package com.fluffytrio.giftrio.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fluffytrio.giftrio.user.entity.Role;
import com.fluffytrio.giftrio.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestDto {
    private Long id;
    private Role role;
    private String email;
    private String nickname;
    @Setter
    private String password;

    public User toEntity() {
        return User.builder()
                .id(id)
                .role(role)
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }
}

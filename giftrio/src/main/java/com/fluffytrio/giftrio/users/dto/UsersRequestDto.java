package com.fluffytrio.giftrio.users.dto;

import com.fluffytrio.giftrio.users.Users;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UsersRequestDto {
    private Long id;
    private String userId;
    private String userName;
    private String password;

    @Builder
    public UsersRequestDto(Long id, String userId, String userName, String password) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public Users toEntity() {
        return Users.builder()
                .id(id)
                .userId(userId)
                .userName(userName)
                .password(password)
                .build();
    }
}

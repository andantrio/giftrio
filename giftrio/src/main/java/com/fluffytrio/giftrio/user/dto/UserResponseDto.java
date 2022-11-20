package com.fluffytrio.giftrio.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDto {
    private Long id;
    private String role;
    private String email;
    private String nickname;
}

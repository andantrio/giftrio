package com.fluffytrio.giftrio.auth.dto;

import lombok.*;

@Builder
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDto {
    private String email;
    private String password;
}

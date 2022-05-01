package com.fluffytrio.giftrio.users.dto;

import com.fluffytrio.giftrio.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
public class UsersRequestDto {
    private String userId;
    private String userName;
    private String password;

    @Builder
    public UsersRequestDto(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public Users toEntity() {
        return Users.builder()
                .userId(userId)
                .userName(userName)
                .password(password)
                .build();
    }
}

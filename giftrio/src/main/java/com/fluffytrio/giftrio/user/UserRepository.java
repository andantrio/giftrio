package com.fluffytrio.giftrio.user;

import com.fluffytrio.giftrio.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    static final String UPDATE_USER_LAST_LOGIN = "UPDATE Users SET LAST_LOGIN_TIME = :lastLoginTime WHERE EMAIL = :email";

    @Transactional
    @Modifying
    @Query(value = UPDATE_USER_LAST_LOGIN, nativeQuery = true)
    public int updateUserLastLogin(@Param("email") String email, @Param("lastLoginTime") LocalDateTime lastLoginTime);
    Optional<User> getUserByEmail(String email);
    List<User> getUsersByEmail(String email);
}

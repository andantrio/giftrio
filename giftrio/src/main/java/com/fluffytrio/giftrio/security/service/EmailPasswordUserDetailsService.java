package com.fluffytrio.giftrio.security.service;

import com.fluffytrio.giftrio.user.UserRepository;
import com.fluffytrio.giftrio.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmailPasswordUserDetailsService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getUserByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("로그인 정보와 일치하는 계정이 없습니다.");
        }
        return user.get();
    }
}

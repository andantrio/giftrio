package com.fluffytrio.giftrio.auth;

import com.fluffytrio.giftrio.auth.dto.LoginDto;
import com.fluffytrio.giftrio.user.UserRepository;
import com.fluffytrio.giftrio.user.dto.UserRequestDto;
import com.fluffytrio.giftrio.user.dto.UserResponseDto;
import com.fluffytrio.giftrio.user.entity.Role;
import com.fluffytrio.giftrio.user.entity.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class AuthControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @After
    public void tearDown() throws Exception {
        //userRepository.deleteAll();
    }

    @Test
    public void login() throws Exception {
        //given
        String email = "email3@gmail.com";
        String password = "password3";

        LoginDto loginDto = LoginDto
                .builder()
                .email(email)
                .password(password)
                .build();

        String url = "http://localhost:"+port+"/api/v1/login";

        //when
        URI uri = restTemplate.postForLocation(url, loginDto);

        //then
        assertAll(
                () -> assertThat(uri.getPath()).isEqualTo("/api/v1/calendars")
        );
    }

    @Test
    public void logout() throws Exception {
        //given
        String role = "USER";
        String email = "newemail@gmail.com";
        String password = "password";
        String nickname = "userName";

        UserRequestDto requestDto = UserRequestDto
                .builder()
                .role(Role.valueOf(role))
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();

        String url = "http://localhost:"+port+"/api/v1/users";

        //when
        ResponseEntity<UserResponseDto> responseEntity = restTemplate.postForEntity(url, requestDto, UserResponseDto.class);

        //then
        assertAll(
                () -> assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK),
                () -> assertThat(responseEntity.getBody().getRole()).isEqualTo(Role.USER.name()),
                () -> assertThat(responseEntity.getBody().getEmail()).isEqualTo(email),
                () -> assertThat(responseEntity.getBody().getNickname()).isEqualTo(nickname)
        );

        //when
        ResponseEntity<User> responseEntitySecond = restTemplate.postForEntity(url, requestDto, User.class);
        System.out.println(responseEntitySecond.getHeaders().toSingleValueMap());

        //then
        assertAll(
                () -> assertThat(responseEntitySecond.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }
}
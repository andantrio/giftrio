package com.fluffytrio.giftrio.user;

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
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class UserControllerTest {
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
    public void addUser() throws Exception {
        //given
        String role = "USER";
        String email = "email3@gmail.com";
        String password = "password3";
        String nickname = "nickname3";

        UserRequestDto requestDto = UserRequestDto
                .builder()
                .role(Role.valueOf(role))
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();

        String url = "http://localhost:"+port+"/api/v1/users";

        //when
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, requestDto, User.class);

        //then
        assertAll(
                () -> assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK),
                () -> assertThat(responseEntity.getBody().getRole()).isEqualTo(Role.USER),
                () -> assertThat(responseEntity.getBody().getEmail()).isEqualTo(email)
        );
    }

    @Test
    public void addDuplicateUser() throws Exception {
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

    @Test
    public void getUser() throws Exception {
        //given
        String url = "http://localhost:"+port+"/api/v1/users/1";

        //when
        ResponseEntity<UserResponseDto> responseEntity = restTemplate.getForEntity(url, UserResponseDto.class);

        //then
        String email = "email@gmail.com";
        String nickname = "nickname";

        assertAll(
                () -> assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK),
                () -> assertThat(responseEntity.getBody().getEmail()).isEqualTo(email),
                () -> assertThat(responseEntity.getBody().getNickname()).isEqualTo(nickname)
        );
    }

    @Test
    public void getUsers() throws Exception {
        //given
        String url = "http://localhost:"+port+"/api/v1/users";

        //when
        ResponseEntity<UserResponseDto[]> responseEntity = restTemplate.getForEntity(url, UserResponseDto[].class);

        //then
        assertAll(
                () -> assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK),
                () -> assertThat(responseEntity.getBody().length).isEqualTo(2)
        );
    }

    @Test
    public void updateUsers() throws Exception {
        //given
        Long userId = 2L;
        String url = "http://localhost:"+port+"/api/v1/users/"+userId;

        String email = "modifiedEmail@gmail.com";
        String password = "password2";

        UserRequestDto requestDto = UserRequestDto
                .builder()
                .id(userId)
                .email(email)
                .password(password)
                .build();

        //when
        restTemplate.put(url, requestDto, UserResponseDto.class);

        //then
        User responseEntity = userRepository.findById(userId).get();
        assertAll(
                () -> assertThat(responseEntity.getEmail()).isEqualTo(email),
                () -> assertThat(responseEntity.getNickname()).isEqualTo("userName"),
                () -> assertThat(responseEntity.getPassword()).isEqualTo(password)
        );
    }

    @Test
    public void deleteUser() throws Exception {
        //given
        String url = "http://localhost:"+port+"/api/v1/users/2";

        //when
        restTemplate.delete(url);

        //then
        //User responseEntity = userRepository.findById(2L).get();
        //assertThat(responseEntity.isDelete() == true);
    }
}
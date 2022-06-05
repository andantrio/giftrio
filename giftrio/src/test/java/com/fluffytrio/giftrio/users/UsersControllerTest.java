package com.fluffytrio.giftrio.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fluffytrio.giftrio.users.dto.UsersRequestDto;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @After
    public void tearDown() throws Exception {
        //usersRepository.deleteAll();
    }

    @Test
    public void addUsers() throws Exception {
        //given
        String userId = "userId2";
        String userName = "userName2";
        String password = "password2";

        UsersRequestDto requestDto = UsersRequestDto
                .builder()
                .userId(userId)
                .userName(userName)
                .password(password)
                .build();

        String url = "http://localhost:"+port+"/users";

        //when
        ResponseEntity<Users> responseEntity = restTemplate.postForEntity(url, requestDto, Users.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getUserId()).isEqualTo(userId);
    }

    @Test
    public void getUsers() throws Exception {
        //given
        String url = "http://localhost:"+port+"/users/42";

        //when
        ResponseEntity<Users> responseEntity = restTemplate.getForEntity(url, Users.class);

        //then
        String userId = "userId";
        String userName = "userName";
        String password = "password";

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getUserId()).isEqualTo(userId);
        assertThat(responseEntity.getBody().getUserName()).isEqualTo(userName);
        assertThat(responseEntity.getBody().getPassword()).isEqualTo(password);
    }

    @Test
    public void getUsersList() throws Exception {
        //given
        String url = "http://localhost:"+port+"/users";

        //when
        ResponseEntity<Users[]> responseEntity = restTemplate.getForEntity(url, Users[].class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().length).isEqualTo(12);
    }

    @Test
    public void updateUsers() throws Exception {
        //given
        String url = "http://localhost:"+port+"/users/43";

        String userId = "helloId";
        String userName = "helloName";
        String password = "helloPw";

        Users requestDto = UsersRequestDto
                .builder()
                .id(43L)
                .userId(userId)
                .userName(userName)
                .password(password)
                .build().toEntity();

        //when
        restTemplate.put(url, requestDto, Users.class);

        //then
        Users responseEntity = usersRepository.findById(43L).get();
        assertThat(responseEntity.getUserId()).isEqualTo(userId);
        assertThat(responseEntity.getUserName()).isEqualTo(userName);
        assertThat(responseEntity.getPassword()).isEqualTo(password);
    }

    @Test
    public void deleteUsers() throws Exception {
        //given
        String url = "http://localhost:"+port+"/users/46";

        //when
        restTemplate.delete(url);

        //then
        Users responseEntity = usersRepository.findById(46L).get();
        assertThat(responseEntity == null);
    }
}

package com.fluffytrio.giftrio.users;

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

import java.util.List;

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

    @After
    public void tearDown() throws Exception {
        //usersRepository.deleteAll();
    }

    @Test
    public void addUsers() throws Exception {
        //given
        String userId = "userId";
        String userName = "userName";
        String password = "password";

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

        List<Users> all = usersRepository.findAll();
        assertThat(all.get(0).getUserName()).isEqualTo(userName);
        assertThat(all.get(0).getPassword()).isEqualTo(password);
    }
}

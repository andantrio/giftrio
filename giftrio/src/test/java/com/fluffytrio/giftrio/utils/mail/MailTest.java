package com.fluffytrio.giftrio.utils.mail;

import com.fluffytrio.giftrio.user.dto.UserRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MailTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    MailRepository mailRepository;

    @After
    public void cleanup() {
        mailRepository.deleteAll();
    }

    private final String MAIL_URL = "/api/v1/mail";

    @Test
    public void mailConfirm() {
        //given
        UserRequestDto user = UserRequestDto.builder()
                .email("gyp0288@naver.com")
                .userName("지영")
                .password("1234")
                .build();

        String url = "http://localhost:" + port + MAIL_URL + "/login/emailConfirm";
        //when
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, user, String.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}

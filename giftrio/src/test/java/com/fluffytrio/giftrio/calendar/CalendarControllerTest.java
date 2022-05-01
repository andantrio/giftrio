package com.fluffytrio.giftrio.calendar;

import com.fluffytrio.giftrio.calendar.dto.CalendarRequestDto;
import com.fluffytrio.giftrio.settings.Settings;
import com.fluffytrio.giftrio.settings.SettingsRepository;
import com.fluffytrio.giftrio.users.Users;
import com.fluffytrio.giftrio.users.UsersRepository;
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

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalendarControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    SettingsRepository settingsRepository;

    @Autowired
    private CalendarRepository calendarRepository;

    @After
    public void tearDown() throws Exception {
        calendarRepository.deleteAll();
    }

    @Test
    public void Calendar_등록() throws Exception {
        Users users = Users.builder()
                .userId("tester")
                .userName("name")
                .password("123456")
                .build();
        users = usersRepository.save(users);

        Settings settingId = new Settings();
        settingId = settingsRepository.save(settingId);

        String title = "title";
        String detail = "detail";
        LocalDate startDate = LocalDate.of(2022, 05, 01);
        LocalDate endDate = LocalDate.of(2022, 05, 31);

        CalendarRequestDto calendarDto = CalendarRequestDto.builder()
                .userId(users)
                .settingId(settingId)
                .title(title)
                .detail(detail)
                .startDate(startDate)
                .endDate(endDate)
                .backgroundImg("test")
                .build();

        String url = "http://localhost:" + port + "/calendar";
        //when
        ResponseEntity<Calendar> responseEntity = restTemplate.postForEntity(url, calendarDto, Calendar.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Calendar> all = calendarRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getDetail()).isEqualTo(detail);
    }
}

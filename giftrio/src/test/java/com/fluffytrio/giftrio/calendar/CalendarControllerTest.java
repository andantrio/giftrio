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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalendarControllerTest {
    private final static String CALENDAR_URL = "/api/v1/calendars";
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
        settingsRepository.deleteAll();
        usersRepository.deleteAll();
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

        String url = "http://localhost:" + port + CALENDAR_URL;
        //when
        ResponseEntity<Calendar> responseEntity = restTemplate.postForEntity(url, calendarDto, Calendar.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Calendar> all = calendarRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getDetail()).isEqualTo(detail);
    }

    @Test
    public void Calendar_조회() throws Exception {
        //given
        Calendar_등록();
        String url = "http://localhost:" + port + CALENDAR_URL + "/1";

        //when
        ResponseEntity<Calendar> responseEntity = restTemplate.getForEntity(url, Calendar.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getUser().getUserId()).isEqualTo("tester");
    }

    @Test
    public void Calendar_전체_조회() throws Exception {
        //given
        Calendar_등록();
        String url = "http://localhost:" + port + CALENDAR_URL;

        //when
        ResponseEntity<Calendar[]> responseEntity = restTemplate.getForEntity(url, Calendar[].class);
        List<Calendar> calendars = Arrays.asList(responseEntity.getBody());

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(calendars.get(0).getTitle()).isEqualTo("title");
        assertThat(calendars).hasSize(1);
    }

    @Test
    public void Calendar_수정() throws Exception {
        //given
        Calendar_등록();
        Optional<Users> users = usersRepository.findById(1L);
        Optional<Settings> settingId = settingsRepository.findById(1L);

        LocalDate startDate = LocalDate.of(2022, 05, 01);
        LocalDate endDate = LocalDate.of(2022, 05, 31);

        CalendarRequestDto calendarDto = CalendarRequestDto.builder()
                .id(1L)
                .userId(users.get())
                .settingId(settingId.get())
                .title("title2")
                .detail("detail2")
                .startDate(startDate)
                .endDate(endDate)
                .backgroundImg("test")
                .build();

        //when
        String url = "http://localhost:" + port + CALENDAR_URL;
        restTemplate.put(url, calendarDto, Calendar.class);

        //then
        Optional<Calendar> calendar = calendarRepository.findById(1L);
        assertAll(
                () -> assertThat(calendar.get().getTitle()).isEqualTo("title2"),
                () -> assertThat(calendar.get().getDetail()).isEqualTo("detail2")
        );
    }

    @Test
    public void Calendar_삭제() throws Exception {
        //given
        Calendar_등록();

        //when
        String url = "http://localhost:" + port + CALENDAR_URL + "/1";
        restTemplate.delete(url);
        Optional<Calendar> calendar = calendarRepository.findById(1L);

        //then
        assertThat(calendar.get().isDelete()).isTrue();
    }
}

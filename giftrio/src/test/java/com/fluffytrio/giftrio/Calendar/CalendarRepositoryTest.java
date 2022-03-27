package com.fluffytrio.giftrio.Calendar;

import com.fluffytrio.giftrio.calendar.Calendar;
import com.fluffytrio.giftrio.calendar.CalendarRepository;
import com.fluffytrio.giftrio.settings.Settings;
import com.fluffytrio.giftrio.settings.SettingsRepository;
import com.fluffytrio.giftrio.users.Users;
import com.fluffytrio.giftrio.users.UsersRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalendarRepositoryTest {

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    SettingsRepository settingsRepository;

    @After
    public void cleanup() {
        calendarRepository.deleteAll();
    }

    @Test
    public void createCalendar() {
        ///users
        //given
        String userId = "user01";
        String userName = "nickname";
        String password = "password";
        usersRepository.save(Users.builder().userId(userId).userName(userName).password(password).build());
        //when
        List<Users> usersList = usersRepository.findAll();
        //then
        Users postUser = usersList.get(0);

        ///Settings
        //when
        settingsRepository.save(new Settings());
        //then
        Settings postSetting = settingsRepository.findAll().get(0);

        ///Calendar
        Calendar calendar = Calendar.builder()
                .users(postUser)
                .settings(postSetting)
                .build();
        calendarRepository.save(calendar);
        //when
        List<Calendar> calendarList = calendarRepository.findAll();
        //then
        Calendar temp = calendarList.get(0);
        assertThat(temp.getUserId().getUserId()).isEqualTo(userId);
    }
}

package com.fluffytrio.giftrio.calendar;

import com.fluffytrio.giftrio.settings.Settings;
import com.fluffytrio.giftrio.settings.SettingsRepository;
import com.fluffytrio.giftrio.user.User;
import com.fluffytrio.giftrio.user.UserRepository;
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
    UserRepository userRepository;

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
        userRepository.save(User.builder().email(userId).userName(userName).password(password).build());
        //when
        List<User> usersList = userRepository.findAll();
        //then
        User postUser = usersList.get(0);

        ///Settings
        //when
        settingsRepository.save(new Settings());
        //then
        Settings postSetting = settingsRepository.findAll().get(0);

        ///Calendar
        Calendar calendar = Calendar.builder()
                .user(postUser)
                .settingId(postSetting)
                .build();
        calendarRepository.save(calendar);
        //when
        List<Calendar> calendarList = calendarRepository.findAll();
        //then
        Calendar temp = calendarList.get(0);
        assertThat(temp.getUser().getEmail()).isEqualTo(userId);
    }
}

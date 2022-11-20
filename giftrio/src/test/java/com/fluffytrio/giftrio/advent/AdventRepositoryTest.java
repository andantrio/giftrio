package com.fluffytrio.giftrio.advent;

import com.fluffytrio.giftrio.calendar.Calendar;
import com.fluffytrio.giftrio.calendar.CalendarRepository;
import com.fluffytrio.giftrio.settings.Settings;
import com.fluffytrio.giftrio.settings.SettingsRepository;
import com.fluffytrio.giftrio.user.entity.User;
import com.fluffytrio.giftrio.user.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdventRepositoryTest {

    @Autowired
    AdventRepository adventRepository;

    @Autowired
    UserRepository usersRepository;

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    SettingsRepository settingsRepository;

    @After
    public void cleanup() {
        adventRepository.deleteAll();
    }

    @Test
    public void createAdvent(){
        //given
        LocalDate adventDate = LocalDate.now();
        String text = "advent create test";

        // create user
        String userId = "user01";
        String password = "password";
        String nickname = "nickname";
        usersRepository.save(User.builder().email(userId).nickname(nickname).password(password).build());
        User users1 = usersRepository.findAll().get(0);

        // create setting
        settingsRepository.save(new Settings());
        Settings settings1 =  settingsRepository.findAll().get(0);

        // create calendar
        calendarRepository.save(Calendar.builder().user(users1).settingId(settings1).build());
        Calendar calendar1 = calendarRepository.findAll().get(0);

        adventRepository.save(Advent.builder()
                .userId(users1)
                .calendarId(calendar1)
                .adventDate(adventDate)
                .text(text)
                .build());

        //when
        List<Advent> adventList = adventRepository.findAll();

        //then
        Advent advent = adventList.get(0);
        assertThat(advent.getAdventDate()).isEqualTo(adventDate);
        assertThat(advent.getText()).isEqualTo(text);
    }

}

package com.fluffytrio.giftrio.advent;

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

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdventRepositoryTest {

    @Autowired
    AdventRepository adventRepository;

    @Autowired
    UsersRepository usersRepository;

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
        String userName = "nickname";
        String password = "password";
        usersRepository.save(Users.builder().userId(userId).userName(userName).password(password).build());
        Users users1 = usersRepository.findAll().get(0);

        // create setting
        settingsRepository.save(new Settings());
        Settings settings1 =  settingsRepository.findAll().get(0);

        // create calendar
        calendarRepository.save(Calendar.builder().users(users1).settings(settings1).build());
        Calendar calendar1 = calendarRepository.findAll().get(0);

        adventRepository.save(Advent.builder()
                .users(users1)
                .calendar(calendar1)
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

package com.fluffytrio.giftrio.settings;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SettingRepositoryTest {
    @Autowired
    SettingRepository settingRepository;

    @After
    public void cleanup() {
        settingRepository.deleteAll();
    }

    @Test
    public void createSetting() {
        ///settings
        //given
        Setting settings = getSetting();

        //when
        settingRepository.save(settings);

        //then
        Setting postSettings = settingRepository.findAll().get(0);

        assertThat(postSettings.getAccentColor()).isEqualTo("accentColor");
    }

    public static Setting getSetting() {
        String colorScheme = "colorScheme";
        String primaryColor = "primaryColor";
        String accentColor = "accentColor";
        String bgColor = "bgColor";
        String subColor = "subColor";

        return Setting.builder()
                .colorScheme(colorScheme)
                .primaryColor(primaryColor)
                .accentColor(accentColor)
                .bgColor(bgColor)
                .subColor(subColor)
                .build();
    }
}

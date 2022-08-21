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
public class SettingsRepositoryTest {
    @Autowired
    SettingsRepository settingsRepository;

    @After
    public void cleanup() {
        settingsRepository.deleteAll();
    }

    @Test
    public void createSettings() {
        ///settings
        //given
        Settings settings = getSettings();

        //when
        settingsRepository.save(settings);

        //then
        Settings postSettings = settingsRepository.findAll().get(0);

        assertThat(postSettings.getAccentColor()).isEqualTo("accentColor");
    }

    public static Settings getSettings() {
        String colorScheme = "colorScheme";
        String primaryColor = "primaryColor";
        String accentColor = "accentColor";
        String bgColor = "bgColor";
        String subColor = "subColor";

        return Settings.builder()
                .colorScheme(colorScheme)
                .primaryColor(primaryColor)
                .accentColor(accentColor)
                .bgColor(bgColor)
                .subColor(subColor)
                .build();
    }
}

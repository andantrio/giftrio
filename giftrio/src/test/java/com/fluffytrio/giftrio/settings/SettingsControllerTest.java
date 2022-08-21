package com.fluffytrio.giftrio.settings;

import com.fluffytrio.giftrio.settings.dto.SettingsRequestDto;
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

import static com.fluffytrio.giftrio.settings.SettingsRepositoryTest.getSettings;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SettingsControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    SettingsRepository settingsRepository;

    private final String SETTINGS_URL = "/api/v1/settings";

    @After
    public void tearDown() throws Exception {
        settingsRepository.deleteAll();
    }

    @Test
    public void addSettingsTest() throws Exception {
        //given
        SettingsRequestDto settingsRequestDto = SettingsRequestDto.builder()
                .colorScheme("colorScheme")
                .primaryColor("primaryColor")
                .accentColor("accentColor")
                .bgColor("bgColor")
                .subColor("subColor")
                .build();

        String url = "http://localhost:" + port + SETTINGS_URL;

        //when
        ResponseEntity<Settings> responseEntity = restTemplate.postForEntity(url, settingsRequestDto, Settings.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Settings> all = settingsRepository.findAll();
        assertAll(
                () -> assertThat(all.get(0).getAccentColor()).isEqualTo("accentColor"),
                () -> assertThat(all.get(0).getId()).isNotNull()
        );
    }

    @Test
    public void getSettingTest() throws Exception {
        //given
        addSettingsTest();
        String url = "http://localhost:" + port + SETTINGS_URL + "/1";

        //when
        ResponseEntity<Settings> responseEntity = restTemplate.getForEntity(url, Settings.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Settings> all = settingsRepository.findAll();
        assertAll(
                () -> assertThat(all.get(0).getAccentColor()).isEqualTo("accentColor"),
                () -> assertThat(all.get(0).getId()).isNotNull()
        );
    }

    @Test
    public void getSettingsTest() throws Exception {
        //given
        addSettingsTest();
        Settings settings = getSettings();
        settingsRepository.save(settings);
        String url = "http://localhost:" + port + SETTINGS_URL;

        //when
        ResponseEntity<Settings[]> responseEntity = restTemplate.getForEntity(url, Settings[].class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Settings> all = settingsRepository.findAll();
        assertAll(
                () -> assertThat(all.get(0).getAccentColor()).isEqualTo("accentColor"),
                () -> assertThat(all).hasSize(2)
        );
    }
}

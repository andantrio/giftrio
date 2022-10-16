package com.fluffytrio.giftrio.settings;

import com.fluffytrio.giftrio.settings.dto.SettingRequestDto;
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

import static com.fluffytrio.giftrio.settings.SettingRepositoryTest.getSetting;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SettingControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    SettingRepository settingRepository;

    private final String SETTINGS_URL = "/api/v1/setting";

    @After
    public void tearDown() throws Exception {
        settingRepository.deleteAll();
    }

    @Test
    public void addSettingsTest() throws Exception {
        //given
        SettingRequestDto settingRequestDto = SettingRequestDto.builder()
                .colorScheme("colorScheme")
                .primaryColor("primaryColor")
                .accentColor("accentColor")
                .bgColor("bgColor")
                .subColor("subColor")
                .build();

        String url = "http://localhost:" + port + SETTINGS_URL;

        //when
        ResponseEntity<Setting> responseEntity = restTemplate.postForEntity(url, settingRequestDto, Setting.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Setting> all = settingRepository.findAll();
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
        ResponseEntity<Setting> responseEntity = restTemplate.getForEntity(url, Setting.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Setting> all = settingRepository.findAll();
        assertAll(
                () -> assertThat(all.get(0).getAccentColor()).isEqualTo("accentColor"),
                () -> assertThat(all.get(0).getId()).isNotNull()
        );
    }

    @Test
    public void getSettingsTest() throws Exception {
        //given
        addSettingsTest();
        Setting settings = getSetting();
        settingRepository.save(settings);
        String url = "http://localhost:" + port + SETTINGS_URL;

        //when
        ResponseEntity<Setting[]> responseEntity = restTemplate.getForEntity(url, Setting[].class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Setting> all = settingRepository.findAll();
        assertAll(
                () -> assertThat(all.get(0).getAccentColor()).isEqualTo("accentColor"),
                () -> assertThat(all).hasSize(2)
        );
    }
}

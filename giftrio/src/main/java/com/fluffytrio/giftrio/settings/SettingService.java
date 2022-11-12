package com.fluffytrio.giftrio.settings;

import com.fluffytrio.giftrio.settings.dto.SettingRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SettingService {
    private final SettingRepository settingRepository;

    @Transactional
    public Setting addSetting(SettingRequestDto settingRequestDto) {
        return settingRepository.save(settingRequestDto.toEntity());
    }

    public Setting getSetting(Long id) {
        return settingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 설정입니다."));
    }

    public List<Setting> getSettings() {
        return settingRepository.findAll();
    }
}

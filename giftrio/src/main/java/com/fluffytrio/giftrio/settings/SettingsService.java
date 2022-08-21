package com.fluffytrio.giftrio.settings;

import com.fluffytrio.giftrio.settings.dto.SettingsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SettingsService {
    private final SettingsRepository settingsRepository;

    @Transactional
    public Settings addSettings(SettingsRequestDto settingsRequestDto) {
        return settingsRepository.save(settingsRequestDto.toEntity());
    }

    public Settings getSetting(Long id) {
        return settingsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 설정입니다."));
    }

    public List<Settings> getSettings() {
        return settingsRepository.findAll();
    }
}

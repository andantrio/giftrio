package com.fluffytrio.giftrio.settings;

import com.fluffytrio.giftrio.settings.dto.SettingRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/setting")
public class SettingController {
    private final SettingService settingService;

    @PostMapping
    public Setting addSetting(@RequestBody SettingRequestDto settingRequestDto) {
        return settingService.addSetting(settingRequestDto);
    }

    @GetMapping("/{settingId}")
    public Setting getSetting(@PathVariable Long settingId) {
        return settingService.getSetting(settingId);
    }

    @GetMapping
    public List<Setting> getSettings() {
        return settingService.getSettings();
    }
}

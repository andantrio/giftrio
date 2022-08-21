package com.fluffytrio.giftrio.settings;

import com.fluffytrio.giftrio.settings.dto.SettingsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/settings")
public class SettingsController {
    private final SettingsService settingsService;

    @PostMapping
    public Settings addSettings(@RequestBody SettingsRequestDto settingsRequestDto) {
        return settingsService.addSettings(settingsRequestDto);
    }

    @GetMapping("/{settingsId}")
    public Settings getSetting(@PathVariable Long settingsId) {
        return settingsService.getSetting(settingsId);
    }

    @GetMapping
    public List<Settings> getSettings() {
        return settingsService.getSettings();
    }
}

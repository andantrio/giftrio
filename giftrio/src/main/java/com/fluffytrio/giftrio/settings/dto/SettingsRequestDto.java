package com.fluffytrio.giftrio.settings.dto;

import com.fluffytrio.giftrio.settings.Settings;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SettingsRequestDto {
    private Long id;
    private String colorScheme;
    private String primaryColor;
    private String accentColor;
    private String bgColor;
    private String subColor;

    public Settings toEntity() {
        return Settings.builder()
                .colorScheme(colorScheme)
                .primaryColor(primaryColor)
                .accentColor(accentColor)
                .bgColor(bgColor)
                .subColor(subColor)
                .build();
    }
}

package com.fluffytrio.giftrio.settings.dto;

import com.fluffytrio.giftrio.settings.Setting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettingRequestDto {
    private Long id;
    private String colorScheme;
    private String primaryColor;
    private String accentColor;
    private String bgColor;
    private String subColor;

    public Setting toEntity() {
        return Setting.builder()
                .colorScheme(colorScheme)
                .primaryColor(primaryColor)
                .accentColor(accentColor)
                .bgColor(bgColor)
                .subColor(subColor)
                .build();
    }
}

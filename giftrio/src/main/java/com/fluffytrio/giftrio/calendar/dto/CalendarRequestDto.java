package com.fluffytrio.giftrio.calendar.dto;

import com.fluffytrio.giftrio.advent.Advent;
import com.fluffytrio.giftrio.calendar.Calendar;
import com.fluffytrio.giftrio.settings.Settings;
import com.fluffytrio.giftrio.users.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarRequestDto {
    private Long id;
    private Users userId;
    private Settings settingId;
    private List<Advent> adventList;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String detail;
    private String backgroundImg;

    public Calendar toEntity() {
        return Calendar.builder()
                .id(id)
                .user(userId)
                .settingId(settingId)
                .startDate(startDate)
                .endDate(endDate)
                .title(title)
                .detail(detail)
                .backgroundImg(backgroundImg)
                .build();
    }
}

package com.fluffytrio.giftrio.advent.dto;

import com.fluffytrio.giftrio.advent.Advent;
import com.fluffytrio.giftrio.calendar.Calendar;
import com.fluffytrio.giftrio.users.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdventRequestDto {

    private Long id;
    private Users userId;
    private Calendar calendarId;
    private int seqNum;
    private LocalDate adventDate;
    private String text;
    private String img;
    private boolean isOpen;
    private boolean isDelete;

    public Advent toEntity(){
        return Advent.builder()
                .id(id)
                .userId(userId)
                .calendarId(calendarId)
                .seqNum(seqNum)
                .adventDate(adventDate)
                .text(text)
                .img(img)
                .isOpen(isOpen)
                .isDelete(isDelete)
                .build();
    }
}

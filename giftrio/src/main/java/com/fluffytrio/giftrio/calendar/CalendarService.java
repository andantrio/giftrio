package com.fluffytrio.giftrio.calendar;

import com.fluffytrio.giftrio.calendar.dto.CalendarRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CalendarService {
    private final CalendarRepository calendarRepository;

    public Calendar addCalendar(CalendarRequestDto calendarRequestDto) {
        return calendarRepository.save(calendarRequestDto.toEntity());
    }
}

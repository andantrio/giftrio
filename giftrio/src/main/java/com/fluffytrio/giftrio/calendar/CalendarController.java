package com.fluffytrio.giftrio.calendar;

import com.fluffytrio.giftrio.calendar.dto.CalendarRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/calendar")
public class CalendarController {
    private final CalendarService calendarService;

    @PostMapping()
    public Calendar addCalendar(@RequestBody CalendarRequestDto calendarRequestDto) {
        return calendarService.addCalendar(calendarRequestDto);
    }

    @GetMapping("/{id}")
    public Optional<Calendar> getCalendar(@PathVariable Long id) {
        return calendarService.getCalendar(id);
    }

    @GetMapping()
    public List<Calendar> getCalendars() {
        return calendarService.getCalendars();
    }

    @PutMapping()
    public Calendar updateCalendar(@RequestBody CalendarRequestDto calendarRequestDto) {
        return calendarService.updateCalendar(calendarRequestDto.toEntity());
    }

    @DeleteMapping("/{id}")
    public Calendar deleteCalendar(@PathVariable Long id) {
        return calendarService.deleteCalendar(id);
    }
}

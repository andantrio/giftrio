package com.fluffytrio.giftrio.calendar;

import com.fluffytrio.giftrio.calendar.dto.CalendarRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CalendarService {
    private final CalendarRepository calendarRepository;

    @Transactional
    public Calendar addCalendar(CalendarRequestDto calendarRequestDto) {
        return calendarRepository.save(calendarRequestDto.toEntity());
    }

    public Optional<Calendar> getCalendar(Long id) {
        return calendarRepository.findById(id);
    }

    public List<Calendar> getCalendars() {
        return calendarRepository.findAll();
    }

    @Transactional
    public Calendar updateCalendar(Calendar update) {
        Optional<Calendar> findCalendar = calendarRepository.findById(update.getId());
        Calendar calendar = null;
        if (findCalendar.get() == null) {
            throw new IllegalArgumentException("ID값이 없습니다.");
        } else {
            calendar = findCalendar.get();
            return calendarRepository.save(update);
        }
    }

    @Transactional
    public boolean deleteCalendar(Long id) {
        Optional<Calendar> findCalendar = calendarRepository.findById(id);
        if (findCalendar.get() == null) {
            throw new IllegalArgumentException("ID값이 없습니다.");
        } else {
            Calendar calendar = findCalendar.get();
            calendar.delete();
            return calendarRepository.save(calendar).isDelete();
        }
    }
}

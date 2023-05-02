package ru.vlad.salary.util;

import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;

import java.util.Set;

import static java.time.DayOfWeek.*;

@RequiredArgsConstructor
public class DateUtil {

    private final Set<LocalDate> holidays;
    private final Set<DayOfWeek> workingDays = Set.of(
            MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
    );

    public long calculateWorkingDays(LocalDate startDate, LocalDate endDate)
    {
        return startDate.datesUntil(endDate)
                .filter(t -> workingDays.contains(t.getDayOfWeek()))
                .filter(t -> !holidays.contains(t))
                .count();

    }
}

package ru.yandex.practicum.sleeptracker.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SleepingDateHelper {

    private static final LocalTime MIDDAY = LocalTime.of(12, 0);
    private static final LocalTime WAKE_UP = LocalTime.of(6, 0);
    private static final LocalTime TO_SLEEP = LocalTime.of(0, 0);

    private SleepingDateHelper() {
    }

    public static LocalDate calcCurrentDate(LocalDateTime value) {
        return value.toLocalTime().isBefore(MIDDAY)
            ? value.toLocalDate()
            : value.toLocalDate().plusDays(1);
    }

    public static boolean isCross(LocalDateTime start, LocalDateTime end) {
        LocalDate currentDate = SleepingDateHelper.calcCurrentDate(start);
        LocalDateTime midnight = LocalDateTime.of(currentDate, TO_SLEEP);
        LocalDateTime morning = LocalDateTime.of(currentDate, WAKE_UP);

        return (start.isBefore(midnight.plusMinutes(1))) && end.isAfter(midnight)
            || start.isAfter(midnight) && start.isBefore(morning);
    }
}

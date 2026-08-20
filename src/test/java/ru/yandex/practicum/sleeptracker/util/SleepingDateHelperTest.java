package ru.yandex.practicum.sleeptracker.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class SleepingDateHelperTest {

    @Test
    void calcCurrentDateNext() {
        LocalDateTime value = LocalDateTime.of(2026, 8, 20, 23, 0);
        LocalDate expected = LocalDate.of(2026, 8, 21);

        Assertions.assertEquals(expected, SleepingDateHelper.calcCurrentDate(value));
    }

    @Test
    void calcCurrentDatePrev() {
        LocalDateTime value = LocalDateTime.of(2026, 8, 20, 7, 0);
        LocalDate expected = LocalDate.of(2026, 8, 20);

        Assertions.assertEquals(expected, SleepingDateHelper.calcCurrentDate(value));
    }

    @Test
    void crossTrue() {
        List<LocalDateTime[]> dataProvider = List.of(
            new LocalDateTime[]{LocalDateTime.of(2026, 8, 20, 23, 0), LocalDateTime.of(2026, 8, 21, 7, 0)},
            new LocalDateTime[]{LocalDateTime.of(2026, 8, 20, 23, 0), LocalDateTime.of(2026, 8, 21, 5, 0)},
            new LocalDateTime[]{LocalDateTime.of(2026, 8, 21, 1, 0), LocalDateTime.of(2026, 8, 21, 7, 0)},
            new LocalDateTime[]{LocalDateTime.of(2026, 8, 21, 1, 0), LocalDateTime.of(2026, 8, 21, 5, 0)},
            new LocalDateTime[]{LocalDateTime.of(2026, 8, 21, 0, 0), LocalDateTime.of(2026, 8, 21, 6, 0)}
        );

        for (LocalDateTime[] dateTimes : dataProvider) {
            Assertions.assertTrue(SleepingDateHelper.isCross(dateTimes[0], dateTimes[1]));
        }
    }

    @Test
    void crossFalse() {
        List<LocalDateTime[]> dataProvider = List.of(
            new LocalDateTime[]{LocalDateTime.of(2026, 8, 20, 22, 0), LocalDateTime.of(2026, 8, 20, 23, 0)},
            new LocalDateTime[]{LocalDateTime.of(2026, 8, 21, 7, 0), LocalDateTime.of(2026, 8, 21, 8, 0)},
            new LocalDateTime[]{LocalDateTime.of(2026, 8, 20, 23, 0), LocalDateTime.of(2026, 8, 21, 0, 0)}
        );

        for (LocalDateTime[] dateTimes : dataProvider) {
            Assertions.assertFalse(SleepingDateHelper.isCross(dateTimes[0], dateTimes[1]));
        }
    }
}

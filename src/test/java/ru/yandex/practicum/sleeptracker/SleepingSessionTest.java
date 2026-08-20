package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.exception.FormatException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SleepingSessionTest {

    @Test
    void create() throws FormatException {
        String line = "01.10.25 23:15;02.10.25 07:30;GOOD";

        LocalDateTime expectedStart = LocalDateTime.of(2025, 10, 1, 23, 15);
        LocalDateTime expectedEnd = LocalDateTime.of(2025, 10, 2, 7, 30);

        SleepingSession sleepingSession = SleepingSession.Parser.parse(line);
        assertEquals(expectedStart, sleepingSession.getStart());
        assertEquals(expectedEnd, sleepingSession.getEnd());
        assertEquals(SleepQuality.GOOD, sleepingSession.getQuality());
    }

    @Test
    void formatLineError() {
        String line = "01.10.25 23:15;02.10.25 07:30;GOOD;BAD";

        assertThrows(FormatException.class, () -> SleepingSession.Parser.parse(line));
    }

    @Test
    void formatDateError() {
        String line = "01/10/25 23:15;02.10.25 07:30;GOOD";

        assertThrows(FormatException.class, () -> SleepingSession.Parser.parse(line));
    }

    @Test
    void formatEnumError() {
        String line = "01.10.25 23:15;02.10.25 07:30;BOO";

        assertThrows(FormatException.class, () -> SleepingSession.Parser.parse(line));
    }
}

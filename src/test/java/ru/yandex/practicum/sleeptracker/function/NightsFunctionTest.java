package ru.yandex.practicum.sleeptracker.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exception.FormatException;

import java.util.List;
import java.util.Optional;

public class NightsFunctionTest {

    @Test
    void countNights() throws FormatException {
        List<SleepingSession> sessions = List.of(
            SleepingSession.Parser.parse("01.10.25 23:00;01.10.25 23:59;GOOD"),
            SleepingSession.Parser.parse("02.10.25 06:01;02.10.25 07:00;GOOD")
        );

        Assertions.assertEquals(Optional.of(1), new NightsFunction().apply(sessions).value);
    }

    @Test
    void countEmptyNights() throws FormatException {
        List<SleepingSession> sessions = List.of(
            SleepingSession.Parser.parse("30.08.26 23:00;31.08.26 07:00;GOOD"),
            SleepingSession.Parser.parse("31.08.26 23:00;01.09.26 05:59;GOOD"),
            SleepingSession.Parser.parse("02.09.26 01:00;02.09.26 07:00;GOOD"),
            SleepingSession.Parser.parse("03.09.26 01:00;03.09.26 05:00;GOOD"),
            SleepingSession.Parser.parse("04.09.26 00:00;04.09.26 06:00;GOOD")
        );

        Assertions.assertEquals(Optional.of(0), new NightsFunction().apply(sessions).value);
    }

    @Test
    void countEmptySessions() {
        List<SleepingSession> sessions = List.of();

        Assertions.assertEquals(Optional.empty(), new NightsFunction().apply(sessions).value);
    }
}

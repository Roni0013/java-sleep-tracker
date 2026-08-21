package ru.yandex.practicum.sleeptracker.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exception.FormatException;

import java.util.List;
import java.util.Optional;

public class CountSessionFunctionTest {

    @Test
    void countTest() throws FormatException {
        List<SleepingSession> sleepingSessions = List.of(
            SleepingSession.Parser.parse("01.10.25 23:15;02.10.25 07:30;GOOD"),
            SleepingSession.Parser.parse("03.10.25 23:40;04.10.25 08:00;BAD")
        );

        Assertions.assertEquals(Optional.of(2), new CountSessionsFunction().apply(sleepingSessions).value);
    }

    @Test
    void countEmptyTest() {
        List<SleepingSession> sleepingSessions = List.of();

        Assertions.assertEquals(Optional.of(0), new CountSessionsFunction().apply(sleepingSessions).value);
    }
}

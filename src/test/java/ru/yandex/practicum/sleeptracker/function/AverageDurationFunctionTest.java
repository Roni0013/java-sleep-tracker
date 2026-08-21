package ru.yandex.practicum.sleeptracker.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exception.FormatException;

import java.util.List;
import java.util.Optional;

public class AverageDurationFunctionTest {

    @Test
    void averageTest() throws FormatException {
        List<SleepingSession> sleepingSessions = List.of(
            SleepingSession.Parser.parse("06.10.25 06:00;06.10.25 07:00;GOOD"),
            SleepingSession.Parser.parse("11.10.25 23:00;12.10.25 01:00;BAD")
        );

        Assertions.assertEquals(Optional.of(3 * 60 / 2), new AverageDurationFunction().apply(sleepingSessions).value);
    }

    @Test
    void maxEmptyTest() throws FormatException {
        List<SleepingSession> sleepingSessions = List.of();

        Assertions.assertEquals(Optional.empty(), new AverageDurationFunction().apply(sleepingSessions).value);
    }
}

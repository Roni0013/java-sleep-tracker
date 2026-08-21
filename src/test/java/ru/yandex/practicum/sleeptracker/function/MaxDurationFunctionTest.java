package ru.yandex.practicum.sleeptracker.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exception.FormatException;

import java.util.List;
import java.util.Optional;

public class MaxDurationFunctionTest {

    @Test
    void maxTest() throws FormatException {
        List<SleepingSession> sessions = List.of(
            SleepingSession.Parser.parse("06.10.25 06:00;06.10.25 09:00;GOOD"),
            SleepingSession.Parser.parse("11.10.25 23:00;12.10.25 01:00;BAD")
        );

        Assertions.assertEquals(Optional.of(180), new MaxDurationFunction().apply(sessions).value);
    }

    @Test
    void maxEmptyTest() {
        List<SleepingSession> sessions = List.of();

        Assertions.assertEquals(Optional.empty(), new MaxDurationFunction().apply(sessions).value);
    }
}

package ru.yandex.practicum.sleeptracker.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exception.FormatException;

import java.util.List;
import java.util.Optional;

public class MinDurationFunctionTest {

    @Test
    void minTest() throws FormatException {
        List<SleepingSession> sessions = List.of(
            SleepingSession.Parser.parse("10.10.25 13:00;10.10.25 14:30;NORMAL"),
            SleepingSession.Parser.parse("07.10.25 23:45;08.10.25 06:30;GOOD"),
            SleepingSession.Parser.parse("06.10.25 22:30;07.10.25 05:50;GOOD")
        );

        Assertions.assertEquals(Optional.of(90), new MinDurationFunction().apply(sessions).value);
    }

    @Test
    void minEmptyTest() {
        List<SleepingSession> sessions = List.of();

        Assertions.assertEquals(Optional.empty(), new MinDurationFunction().apply(sessions).value);
    }
}

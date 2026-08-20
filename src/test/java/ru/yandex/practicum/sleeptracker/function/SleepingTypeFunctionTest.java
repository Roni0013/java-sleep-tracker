package ru.yandex.practicum.sleeptracker.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exception.FormatException;

import java.util.List;
import java.util.Optional;

public class SleepingTypeFunctionTest {

    @Test
    void countLateTest() throws FormatException {
        List<SleepingSession> sleepingSessions = List.of(
            SleepingSession.Parser.parse("20.08.26 23:15;21.08.26 09:30;GOOD"),
            SleepingSession.Parser.parse("21.08.26 23:15;22.08.26 09:30;GOOD")
        );

        Assertions.assertEquals(Optional.of(HumanType.LATE.getValue()), new SleepingTypeFunction().apply(sleepingSessions).value);

    }

    @Test
    void countMaxEarlyTest() throws FormatException {
        List<SleepingSession> sleepingSessions = List.of(
            SleepingSession.Parser.parse("20.08.26 23:15;21.08.26 09:30;GOOD"),
            SleepingSession.Parser.parse("21.08.26 21:00;22.08.26 06:30;GOOD"),
            SleepingSession.Parser.parse("22.08.26 21:00;23.08.26 06:30;GOOD")
        );

        Assertions.assertEquals(Optional.of(HumanType.EARLY.getValue()), new SleepingTypeFunction().apply(sleepingSessions).value);
    }

    @Test
    void countEqualsEarlyTest() throws FormatException {
        List<SleepingSession> sleepingSessions = List.of(
            SleepingSession.Parser.parse("20.08.26 00:15;20.08.26 09:30;GOOD"),
            SleepingSession.Parser.parse("20.08.26 23:15;21.08.26 09:30;GOOD"),
            SleepingSession.Parser.parse("21.08.26 21:00;22.08.26 06:30;GOOD"),
            SleepingSession.Parser.parse("22.08.26 21:00;23.08.26 05:30;GOOD")
        );

        Assertions.assertEquals(Optional.of(HumanType.MID.getValue()), new SleepingTypeFunction().apply(sleepingSessions).value);
    }
}

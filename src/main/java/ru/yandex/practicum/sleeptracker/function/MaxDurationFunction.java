package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class MaxDurationFunction implements SleepingFunction<Integer> {
    private static final String FUNCTION_NAME = "Максимальная сессия, мин";

    @Override
    public SleepAnalysisResult<Optional<Integer>> apply(List<SleepingSession> sessions) {
        return new SleepAnalysisResult<>(FUNCTION_NAME, sessions.stream()
            .map(session -> (int) Duration.between(session.getStart(), session.getEnd()).toMinutes())
            .max(Integer::compare));
    }
}

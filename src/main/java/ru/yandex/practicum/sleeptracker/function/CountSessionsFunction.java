package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.Optional;

public class CountSessionsFunction implements SleepingFunction<Integer> {
    private static final String FUNCTION_NAME = "Общее количество сессий, шт";

    @Override
    public SleepAnalysisResult<Optional<Integer>> apply(List<SleepingSession> sleepingSessions) {
        return new SleepAnalysisResult<>(FUNCTION_NAME, Optional.of(sleepingSessions.size()));
    }
}

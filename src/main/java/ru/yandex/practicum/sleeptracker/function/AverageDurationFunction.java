package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class AverageDurationFunction implements SleepingFunction<Integer> {
    private static final String FUNCTION_NAME = "Средняя сессия, мин";

    @Override
    public SleepAnalysisResult<Optional<Integer>> apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult<>(FUNCTION_NAME, Optional.empty());
        }
        int sum = sleepingSessions.stream()
            .map(sleepingSession -> (int) Duration.between(sleepingSession.getStart(), sleepingSession.getEnd()).toMinutes())
            .reduce(Integer::sum).orElse(0);
        return new SleepAnalysisResult<>(FUNCTION_NAME, Optional.of(sum / sleepingSessions.size()));
    }
}

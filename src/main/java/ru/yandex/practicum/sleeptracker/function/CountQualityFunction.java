package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.Optional;

public class CountQualityFunction implements SleepingFunction<Integer> {
    private final String name;
    private final SleepQuality sleepQuality;

    public CountQualityFunction(SleepQuality sleepQuality) {
        this.sleepQuality = sleepQuality;
        name = "Качество сессий сна " + sleepQuality.name() + ", шт";
    }

    @Override
    public SleepAnalysisResult<Optional<Integer>> apply(List<SleepingSession> sleepingSessions) {
        return new SleepAnalysisResult<>(name, Optional.of((int) sleepingSessions.stream()
            .filter(sleepingSession -> sleepingSession.getQuality().equals(sleepQuality)).count()));
    }
}

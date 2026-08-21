package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface SleepingFunction<T> extends Function<List<SleepingSession>, SleepAnalysisResult<Optional<T>>> {
}

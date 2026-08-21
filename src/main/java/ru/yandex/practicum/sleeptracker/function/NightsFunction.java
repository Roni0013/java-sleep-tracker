package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.util.SleepingDateHelper;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class NightsFunction implements SleepingFunction<Integer> {
    private static final String FUNCTION_NAME = "Бессонные ночи, шт";

    @Override
    public SleepAnalysisResult<Optional<Integer>> apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult<>(FUNCTION_NAME, Optional.empty());
        }
        Set<LocalDate> nightDates = sessions.stream().map(session -> {
            if (SleepingDateHelper.isCross(session.getStart(), session.getEnd())) {
                return SleepingDateHelper.calcCurrentDate(session.getStart());
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toSet());

        long countNight = SleepingDateHelper.calcCurrentDate(sessions.getFirst().getStart())
            .datesUntil(SleepingDateHelper.calcCurrentDate(sessions.getLast().getStart().plusDays(1)))
            .filter(localDate -> !nightDates.contains(localDate)).count();

        try {
            return new SleepAnalysisResult<>(FUNCTION_NAME, Optional.of(Math.toIntExact(countNight)));
        } catch (ArithmeticException e) {
            return new SleepAnalysisResult<>(FUNCTION_NAME, Optional.empty());
        }
    }
}

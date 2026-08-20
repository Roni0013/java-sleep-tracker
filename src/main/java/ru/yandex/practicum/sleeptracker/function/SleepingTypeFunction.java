package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.util.SleepingDateHelper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SleepingTypeFunction implements SleepingFunction<String> {
    private static final String FUNCTION_NAME = "Классификация: ";

    @Override
    public SleepAnalysisResult<Optional<String>> apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult<>(FUNCTION_NAME, Optional.empty());
        }

        Map<HumanType, Integer> map = sessions.stream()
            .filter(session -> SleepingDateHelper.isCross(session.getStart(), session.getEnd()))
            .collect(Collectors.toConcurrentMap(
                key -> checkHumanType(key.getStart(), key.getEnd()),
                value -> 1,
                Integer::sum
            ));

        int maxValue = Collections.max(map.values());
        List<HumanType> humanTypes = map.entrySet().stream()
            .filter(entry -> entry.getValue().equals(maxValue))
            .map(Map.Entry::getKey).toList();
        HumanType humanType = humanTypes.size() == 1 ? humanTypes.getFirst() : HumanType.MID;

        return new SleepAnalysisResult<>(FUNCTION_NAME, Optional.of(humanType.getValue()));
    }

    private HumanType checkHumanType(LocalDateTime start, LocalDateTime end) {
        LocalDate currentDate = SleepingDateHelper.calcCurrentDate(start);
        if (
            start.isAfter(LocalDateTime.of(currentDate.minusDays(1), LocalTime.of(23, 0)))
                && end.isAfter(LocalDateTime.of(currentDate, LocalTime.of(9, 0)))
        ) {
            return HumanType.LATE;
        } else if (
            start.isBefore(LocalDateTime.of(currentDate.minusDays(1), LocalTime.of(22, 0)))
                && end.isBefore(LocalDateTime.of(currentDate, LocalTime.of(7, 0)))
        ) {
            return HumanType.EARLY;
        } else {
            return HumanType.MID;
        }
    }
}

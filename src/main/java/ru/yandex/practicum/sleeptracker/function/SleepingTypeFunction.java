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
    private static final String FUNCTION_NAME = "Классификация";
    private static final LocalTime EARLY_SLEEP_TIME = LocalTime.of(22, 0);
    private static final LocalTime LATE_SLEEP_TIME = LocalTime.of(23, 0);
    private static final LocalTime EARLY_WAKEUP_TIME = LocalTime.of(7, 0);
    private static final LocalTime LATE_WAKEUP_TIME = LocalTime.of(9, 0);

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
            start.isAfter(LocalDateTime.of(currentDate.minusDays(1), LATE_SLEEP_TIME))
                && end.isAfter(LocalDateTime.of(currentDate, LATE_WAKEUP_TIME))
        ) {
            return HumanType.LATE;
        } else if (
            start.isBefore(LocalDateTime.of(currentDate.minusDays(1), EARLY_SLEEP_TIME))
                && end.isBefore(LocalDateTime.of(currentDate, EARLY_WAKEUP_TIME))
        ) {
            return HumanType.EARLY;
        } else {
            return HumanType.MID;
        }
    }
}

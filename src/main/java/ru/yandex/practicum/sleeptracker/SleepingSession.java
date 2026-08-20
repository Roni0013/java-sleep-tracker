package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.exception.FormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SleepingSession {
    private static final String DATETIME_PATTERN = "dd.MM.yy HH:mm";

    private final LocalDateTime start;
    private final LocalDateTime end;
    private final SleepQuality quality;

    private SleepingSession(LocalDateTime start, LocalDateTime end, SleepQuality quality) {
        this.start = start;
        this.end = end;
        this.quality = quality;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public SleepQuality getQuality() {
        return quality;
    }

    public static class Parser {
        public static SleepingSession parse(String line) throws FormatException {
            String[] lineParts = line.split(";");
            if (lineParts.length > 3) {
                throw new FormatException("Неверный формат строки");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
            try {
                return new SleepingSession(
                    LocalDateTime.parse(lineParts[0], formatter),
                    LocalDateTime.parse(lineParts[1], formatter),
                    SleepQuality.valueOf(lineParts[2])
                );
            } catch (DateTimeParseException | IllegalArgumentException e) {
                throw new FormatException("Невеный формат строки: " + line);
            }
        }
    }
}

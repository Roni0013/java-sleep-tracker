package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.exception.FileLoadException;
import ru.yandex.practicum.sleeptracker.exception.FormatException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

public class SleepSessionsLoader {
    private final String filePath;

    public SleepSessionsLoader(String filePath) {
        this.filePath = filePath;
    }

    public List<SleepingSession> get() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            return reader.lines().map(line -> {
                try {
                    return SleepingSession.Parser.parse(line);
                } catch (FormatException e) {
                    System.out.println(e.getMessage());
                }
                return null;
            }).filter(Objects::nonNull).toList();
        } catch (FileNotFoundException e) {
            throw new FileLoadException(e.getMessage());
        } catch (IOException e) {
            throw new FileLoadException("Ошибка загрузки файла");
        }
    }
}

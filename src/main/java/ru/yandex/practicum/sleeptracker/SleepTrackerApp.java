package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.exception.FileLoadException;
import ru.yandex.practicum.sleeptracker.function.*;

import java.util.List;

public class SleepTrackerApp {
    private static List<SleepingFunction<?>> functions;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Укажите путь к файлу");
            return;
        }
        String filePath = args[0];
        List<SleepingSession> sleepingSessions;
        try {
            sleepingSessions = new SleepSessionsLoader(filePath).get();
        } catch (FileLoadException e) {
            System.out.println(e.getMessage());
            return;
        }

        functions = List.of(
            new CountSessionsFunction(),
            new MinDurationFunction(),
            new MaxDurationFunction(),
            new AverageDurationFunction(),
            new CountQualityFunction(SleepQuality.BAD),
            new NightsFunction(),
            new SleepingTypeFunction()
        );

        functions.stream().map(sleepingFunction -> sleepingFunction.apply(sleepingSessions))
            .forEach(analysisResult -> {
                System.out.print(analysisResult.name + ": ");
                analysisResult.value.ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("Не определено")
                );
            });
    }
}
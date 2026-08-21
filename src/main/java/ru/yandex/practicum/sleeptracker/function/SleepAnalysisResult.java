package ru.yandex.practicum.sleeptracker.function;

public class SleepAnalysisResult<T> {
    public final String name;
    public final T value;

    public SleepAnalysisResult(String name, T value) {
        this.name = name;
        this.value = value;
    }
}

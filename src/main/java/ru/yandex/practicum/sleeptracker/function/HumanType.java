package ru.yandex.practicum.sleeptracker.function;

public enum HumanType {
    EARLY("Жаворонок"),
    MID("Голубь"),
    LATE("Сова");

    private final String value;

    HumanType(String type) {
        this.value = type;
    }

    public String getValue() {
        return value;
    }
}

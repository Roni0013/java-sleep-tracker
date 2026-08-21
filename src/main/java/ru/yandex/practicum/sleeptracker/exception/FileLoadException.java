package ru.yandex.practicum.sleeptracker.exception;

public class FileLoadException extends RuntimeException {
    public FileLoadException(String message) {
        super(message);
    }
}

package by.it_academy.user.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LongToLocalDateTimeUtil {

    public static LocalDateTime fromLong(long millis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.of("UTC"));
    }
}

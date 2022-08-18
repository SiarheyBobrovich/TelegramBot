package by.bobrovich.telegram.bot.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeUtil {

    private LocalDateTimeUtil() {
    }

    public static long localDateTimeToLong(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}

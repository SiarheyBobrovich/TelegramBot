package by.bobrovich.telegram.bot.converters;

import by.bobrovich.telegram.bot.dto.user.SaveUser;
import by.bobrovich.telegram.bot.dto.user.enums.Status;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ChatIdToSaveUserConverter implements Converter<Long, SaveUser> {

    @Override
    public SaveUser convert(Long chatId) {
        return SaveUser.builder()
                .setUsername("" + chatId)
                .setPassword("password")
                .setChatId(chatId)
                .setCity("MINSK")
                .setSize(10)
                .setStatus(Status.ACTIVATED)
                .build();
    }
}

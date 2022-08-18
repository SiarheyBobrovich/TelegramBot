package by.bobrovich.telegram.bot.converters;

import by.bobrovich.telegram.bot.dto.user.SaveUser;
import by.bobrovich.telegram.bot.dto.user.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToSaveUserConverter implements Converter<User, SaveUser> {

    @Override
    public SaveUser convert(User source) {
        return SaveUser.builder()
                .setUsername(source.getUsername())
                .setPassword("password")
                .setChatId(source.getChatId())
                .setStatus(source.getStatus())
                .setCity(source.getCity())
                .setSize(source.getSize())
                .build();
    }
}

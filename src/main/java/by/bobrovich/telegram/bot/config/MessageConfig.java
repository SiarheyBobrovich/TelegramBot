package by.bobrovich.telegram.bot.config;

import by.bobrovich.telegram.bot.core.keyboard.KeyboardName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.Map;

@Configuration
public class MessageConfig {

    @Bean
    public Map<KeyboardName, String> getMessages() {
        KeyboardName[] values = KeyboardName.values();
        Map<KeyboardName, String> messages = new EnumMap<>(KeyboardName.class);

        for (KeyboardName value : values) {
            messages.put(value, value.getMessage());
        }

        return messages;
    }
}

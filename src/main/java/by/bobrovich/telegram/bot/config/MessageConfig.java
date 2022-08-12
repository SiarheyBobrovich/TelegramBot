package by.bobrovich.telegram.bot.config;

import by.bobrovich.telegram.bot.keyboard.menu.KeyboardKeyName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.Map;

@Configuration
public class MessageConfig {

    @Bean
    public Map<KeyboardKeyName, String> getMessages() {
        KeyboardKeyName[] values = KeyboardKeyName.values();
        Map<KeyboardKeyName, String> messages = new EnumMap<>(KeyboardKeyName.class);

        for (KeyboardKeyName value : values) {
            messages.put(value, value.getMessage());
        }

        return messages;
    }
}

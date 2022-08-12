package by.bobrovich.telegram.bot.core.keyboard.query;

import by.bobrovich.telegram.bot.core.keyboard.menu.KeyboardKeyName;
import by.bobrovich.telegram.bot.service.YamlPropertySourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "yaml")
@PropertySource(value = "classpath:cities.yml", factory = YamlPropertySourceFactory.class)
public class CityInlineKeyboardMarkup extends NamedInlineKeyboardMarkup {

    public CityInlineKeyboardMarkup(@Value("#{'${city}'.split(' ')}") List<String> cities) {
        super(KeyboardKeyName.CITY, cities);
    }
}

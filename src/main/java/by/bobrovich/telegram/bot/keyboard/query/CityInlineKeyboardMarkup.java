package by.bobrovich.telegram.bot.keyboard.query;

import by.bobrovich.telegram.bot.keyboard.menu.KeyboardKeyName;
import by.bobrovich.telegram.bot.utils.YamlPropertySourceFactory;
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

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
@PropertySource(value = "classpath:currencies.yml", factory = YamlPropertySourceFactory.class)
public class CurrencyInlineKeyboardMarkup extends NamedInlineKeyboardMarkup {

    public CurrencyInlineKeyboardMarkup(
            @Value("#{'${currency}'.split(';')}") List<String> currencies) {
        super(KeyboardKeyName. CURRENCY,currencies);
    }
}

package by.bobrovich.telegram.bot.keyboard.query;

import by.bobrovich.telegram.bot.utils.YamlPropertySourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource(value = "classpath:/inline_keyboards/currencies.yml", factory = YamlPropertySourceFactory.class)
public class CurrencyInlineKeyboardMarkup extends NamedInlineKeyboardMarkup {

    public CurrencyInlineKeyboardMarkup(
            @Value("#{'${currency}'.split(';')}") List<String> currencies) {
        super("Валюта", currencies);
    }

    public String getMessage() {
        return "Выберите валюту и операцию: ";
    }
}

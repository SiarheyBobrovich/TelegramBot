package by.bobrovich.telegram.bot.keyboard.query;

import by.bobrovich.telegram.bot.utils.YamlPropertySourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource(value = "classpath:/inline_keyboards/cities.yml", factory = YamlPropertySourceFactory.class)
public class CityInlineKeyboardMarkup extends NamedInlineKeyboardMarkup {

    public CityInlineKeyboardMarkup(@Value("#{'${city}'.split(';')}") List<String> cities) {
        super("Город", cities);
    }

    @Override
    public String getMessage() {
        return "Выберите город для сохранения: ";
    }
}
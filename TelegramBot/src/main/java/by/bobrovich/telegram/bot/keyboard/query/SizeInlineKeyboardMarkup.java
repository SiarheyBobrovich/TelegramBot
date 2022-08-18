package by.bobrovich.telegram.bot.keyboard.query;

import by.bobrovich.telegram.bot.utils.YamlPropertySourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource(value = "classpath:/inline_keyboards/sizes.yml", factory = YamlPropertySourceFactory.class)
public class SizeInlineKeyboardMarkup extends NamedInlineKeyboardMarkup {

    public SizeInlineKeyboardMarkup(@Value("#{'${size}'.split(';')}") List<String> cities) {
        super("Размер страницы", cities);
    }

    @Override
    public String getMessage() {
        return "Выберите размер страницы: ";
    }
}
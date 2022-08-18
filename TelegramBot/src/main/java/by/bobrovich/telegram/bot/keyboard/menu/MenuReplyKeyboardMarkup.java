package by.bobrovich.telegram.bot.keyboard.menu;

import by.bobrovich.telegram.bot.utils.YamlPropertySourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource(value = "classpath:/reply_keyboards/menu.yml", factory = YamlPropertySourceFactory.class)
public class MenuReplyKeyboardMarkup extends NamedReplyKeyboardMarkup {

    public MenuReplyKeyboardMarkup(@Value("#{'${menu}'.split(';')}") List<String> keys) {
        super("Меню", keys);
    }

    @Override
    public String getMessage() {
        return "Вы находитесь в меню";
    }
}

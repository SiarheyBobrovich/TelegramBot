package by.bobrovich.telegram.bot.keyboard.menu;

import by.bobrovich.telegram.bot.utils.YamlPropertySourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource(value = "classpath:/reply_keyboards/settings.yml", factory = YamlPropertySourceFactory.class)
public class SettingReplyKeyboardMarkup extends NamedReplyKeyboardMarkup{

    public SettingReplyKeyboardMarkup(@Value("#{'${settings}'.split(';')}") List<String> keys) {
        super("Настройки", keys);
    }

    @Override
    public String getMessage() {
        return "Вы находитесь в настройках";
    }
}

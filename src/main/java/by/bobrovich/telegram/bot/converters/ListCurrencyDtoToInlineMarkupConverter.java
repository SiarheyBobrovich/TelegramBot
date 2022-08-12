package by.bobrovich.telegram.bot.converters;

import by.bobrovich.telegram.bot.dto.CurrencyDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListCurrencyDtoToInlineMarkupConverter implements Converter<List<CurrencyDto>, InlineKeyboardMarkup> {

    @Override
    public InlineKeyboardMarkup convert(List<CurrencyDto> source) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        source.forEach(x -> buttons.add(List.of(
                InlineKeyboardButton.builder().text(x.getAddress()).callbackData("ignore").build(),
                InlineKeyboardButton.builder().text("" + x.getExchangeRate()).callbackData("ignore").build()
        )));

        return new InlineKeyboardMarkup(buttons);
    }
}

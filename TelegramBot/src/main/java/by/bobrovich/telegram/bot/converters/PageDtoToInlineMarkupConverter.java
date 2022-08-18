package by.bobrovich.telegram.bot.converters;

import by.bobrovich.telegram.bot.dto.CurrencyDto;
import by.bobrovich.telegram.bot.dto.PageDtos;
import by.bobrovich.telegram.bot.enums.Currency;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class PageDtoToInlineMarkupConverter {

    public InlineKeyboardMarkup convert(PageDtos<CurrencyDto> source, Currency currency, Currency.Operation operation) {
        String callBackData = "CURRENCY " + operation.name() + " " + currency.name();
        List<InlineKeyboardButton> buttons = new ArrayList<>();

        if (!source.isFirst()) {
            buttons.add(InlineKeyboardButton.builder()
                    .text("back")
                    .callbackData(callBackData + " " + (source.getPage() - 1))
                    .build()
            );
        }

        buttons.add(InlineKeyboardButton.builder()
                .text(source.getPage() + 1 + "/" + source.getTotalPages())
                .callbackData("ignore")
                .build()
        );

        if (!source.isLast()) {
            buttons.add(InlineKeyboardButton.builder()
                    .text("next")
                    .callbackData(callBackData + " " + (source.getPage() + 1))
                    .build()
            );
        }

        return new InlineKeyboardMarkup(List.of(buttons));
    }
}

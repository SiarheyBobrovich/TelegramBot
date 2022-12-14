package by.bobrovich.telegram.bot.converters;

import by.bobrovich.telegram.bot.dto.CurrencyDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListCurrencyDtoToStringConverter implements Converter<List<CurrencyDto>, String> {

    @Override
    public String convert(List<CurrencyDto> source) {
        final StringBuilder builder = new StringBuilder();

        source.forEach(currencyDto -> builder
                .append("<strong>")
                .append(currencyDto.getExchangeRate())
                .append("</strong>")
                .append(" : ")
                .append(currencyDto.getAddress())
                .append("\n"));

        return builder.toString();
    }
}

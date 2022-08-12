package by.bobrovich.telegram.bot.converters;

import by.bobrovich.telegram.bot.core.dto.CurrencyDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListCurrencyDtoToStringConverter implements Converter<List<CurrencyDto>, String> {

    @Override
    public String convert(List<CurrencyDto> source) {
        final StringBuilder builder = new StringBuilder();

        source.forEach(currencyDto -> builder.append(currencyDto.getAddress())
                .append(" = ")
                .append(currencyDto.getExchangeRate())
                .append("\n"));

        return builder.toString();
    }
}

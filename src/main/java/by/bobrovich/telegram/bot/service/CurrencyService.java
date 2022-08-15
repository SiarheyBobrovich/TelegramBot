package by.bobrovich.telegram.bot.service;

import by.bobrovich.telegram.bot.dto.CurrencyDto;
import by.bobrovich.telegram.bot.dto.PageDtos;
import by.bobrovich.telegram.bot.enums.Currency;
import by.bobrovich.telegram.bot.utils.YamlPropertySourceFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConfigurationProperties(prefix = "yaml")
@PropertySource(value = "classpath:uri.yml", factory = YamlPropertySourceFactory.class)
public class CurrencyService {

    private final String uri;
    private final RestTemplate template;
    private final ObjectMapper mapper;

    public CurrencyService(@Value("${currency.service.uri}") String uri, RestTemplate template, ObjectMapper mapper) {
        this.template = template;
        this.uri = uri;
        this.mapper = mapper;
    }

    public PageDtos<CurrencyDto> getCurrencies(Currency currency, Currency.Operation operation) {

        return getCurrenciesPageable(currency, operation, 0, 10);
    }

    public PageDtos<CurrencyDto> getCurrenciesPageable(Currency currency, Currency.Operation operation, long page, int size) {
        final PageDtos<CurrencyDto> dtos;
        PageDtos forObject = template.getForObject(uri + currency.name() + "/" + operation.name() + "?page=" + page + "&size=" + size, PageDtos.class);
        PageDtos<CurrencyDto> currencyDtoPageDtos = mapper.convertValue(forObject, new TypeReference<>() {});

        return currencyDtoPageDtos;
    }

}

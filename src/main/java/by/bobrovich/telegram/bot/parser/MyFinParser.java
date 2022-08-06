package by.bobrovich.telegram.bot.parser;

import by.bobrovich.telegram.bot.parser.core.Bank;
import by.bobrovich.telegram.bot.parser.core.Office;
import by.bobrovich.telegram.bot.service.YamlPropertySourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@ConfigurationProperties(prefix = "yaml")
@PropertySource(value = "classpath:patterns/bank.yml", factory = YamlPropertySourceFactory.class)
public class MyFinParser {

    private final RestTemplate restTemplate;
    private final Pattern bankNamePattern;
    private final String replacer;
    private final String address;

    private final Pattern exchangeRates;

    public MyFinParser(RestTemplate restTemplate,
                       @Value("${bank.pattern}") String bankNamePattern,
                       @Value("${office.replacer}") String replacer,
                       @Value("${office.address}") String address,
                       @Value("${exchange_rates.pattern}") String exchangeRates) {

        this.restTemplate = restTemplate;
        this.bankNamePattern = Pattern.compile(bankNamePattern);
        this.replacer = replacer;
        this.address = address;
        this.exchangeRates = Pattern.compile(exchangeRates);
    }

    public List<Bank> getBanks(){
        List<Bank> banks = new ArrayList<>();

        try {
            ClientHttpRequest request = restTemplate.getRequestFactory()
                    .createRequest(URI.create("https://myfin.by/currency/minsk"), HttpMethod.GET);

            try(ClientHttpResponse execute = request.execute()) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(execute.getBody()));

                String s;
                while ((s = reader.readLine()) != null) {

                    if (s.matches("^\\s+<table>")) {
                        Bank bank = parseTable(reader);

                        if (!Objects.isNull(bank)) {
                            banks.add(bank);
                        }
                    }
                }
            }

        }catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return banks;
    }

    private Bank parseTable(BufferedReader reader) throws IOException {
        String line;
        String thPattern = "(\\s)|(Отделения)|(<th>)|(</th>)";
        Bank bank;

        while ((line = reader.readLine()) != null && !line.matches("^\\s+</table>")) {

            Matcher matcher = bankNamePattern.matcher(line);

            if (matcher.find()) {
                bank = new Bank(line.trim().replaceAll(thPattern, ""));
                bank.setOffices(getOffices(reader));
                return bank;
            }
        }

        return null;
    }

    private List<Office> getOffices(BufferedReader reader) throws IOException {
        List<Office> offices = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null && !line.matches("^\\s+</table>")) {
            Office office;
            String trimmedLine = line.trim();

            if (trimmedLine.startsWith(address)) {

                String officeName = trimmedLine.replaceAll(replacer, "");

                office = new Office(officeName);
                office.setCurrencies(getExchangeRates(reader));
                offices.add(office);
            }
        }

        return offices;
    }

    private List<Double> getExchangeRates(BufferedReader reader) throws IOException {

        List<Double> currencies = new ArrayList<>();
        String line;
        boolean find = false;

        while ((line = reader.readLine()) != null && !find) {
            Matcher matcher = exchangeRates.matcher(line);

            if (matcher.find()) {
                do {
                    currencies.add(Double.parseDouble(matcher.group()));

                }while (matcher.find());

                find = true;
            }
        }

        return currencies;
    }
}

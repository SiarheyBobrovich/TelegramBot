package by.bobrovich.finance_service.parsers;

import by.bobrovich.finance_service.dao.entity.*;
import by.bobrovich.finance_service.parsers.api.IMyFinParser;
import by.bobrovich.finance_service.utils.YamlPropertySourceFactory;
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
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@ConfigurationProperties(prefix = "yaml")
@PropertySource(value = "classpath:parser_conf/patterns/bank.yml", factory = YamlPropertySourceFactory.class)
public class MyFinMinskParser implements IMyFinParser {

    private final RestTemplate restTemplate;
    private final Pattern bankNamePattern;
    private final String replacer;
    private final String address;
    private final Pattern exchangeRates;
    private final String city;

    public MyFinMinskParser(RestTemplate restTemplate,
                            @Value("${bank.pattern}") String bankNamePattern,
                            @Value("${office.replacer}") String replacer,
                            @Value("${office.address}") String address,
                            @Value("${exchange_rates.pattern}") String exchangeRates) {

        this.restTemplate = restTemplate;
        this.bankNamePattern = Pattern.compile(bankNamePattern);
        this.replacer = replacer;
        this.address = address;
        this.exchangeRates = Pattern.compile(exchangeRates);

        city = "Minsk";
    }

    @Override
    public List<Bank> getBanks(){
        List<Bank> banks = new ArrayList<>();

        try {
            ClientHttpRequest request = restTemplate.getRequestFactory()
                    .createRequest(URI.create("https://myfin.by/currency/" + city), HttpMethod.GET);

            try(ClientHttpResponse execute = request.execute()) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(execute.getBody()));

                String s;
                while ((s = reader.readLine()) != null) {

                    if (s.matches("^\\s+<table>")) {
                        Bank bank = parseTable(reader, city);

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

    private Bank parseTable(BufferedReader reader, String city) throws IOException {
        String line;
        String thPattern = "(\\s)|(Отделения)|(<th>)|(</th>)";
        Bank bank;

        while ((line = reader.readLine()) != null && !line.matches("^\\s+</table>")) {

            Matcher matcher = bankNamePattern.matcher(line);

            if (matcher.find()) {
                bank = new Bank();

                bank.setCity(city.toUpperCase());
                bank.setName(line.trim().replaceAll(thPattern, ""));
                bank.setOffices(getOffices(reader, bank));

                return bank;
            }
        }

        return null;
    }

    private Set<Office> getOffices(BufferedReader reader, Bank bank) throws IOException {
        Set<Office> offices = new HashSet<>();
        String line;
        String cityUpperCase = city.toUpperCase();

        while ((line = reader.readLine()) != null && !line.matches("^\\s+</table>")) {
            Office office;
            String trimmedLine = line.trim();

            if (trimmedLine.startsWith(address)) {

                String officeAddress = trimmedLine.replaceAll(replacer, "");
                List<Double> exchangeRates1 = getExchangeRates(reader);

                if (!exchangeRates1.isEmpty() && exchangeRates1.size() > 5) {
                    Usd usd = new Usd();
                    usd.setBuy(exchangeRates1.get(0));
                    usd.setSell(exchangeRates1.get(1));

                    Euro euro = new Euro();
                    euro.setBuy(exchangeRates1.get(2));
                    euro.setSell(exchangeRates1.get(3));

                    Rub rub = new Rub();
                    rub.setBuy(exchangeRates1.get(4));
                    rub.setSell(exchangeRates1.get(5));

                    office = Office.builder()
                            .setBankUuid(bank.getUuid())
                            .setCity(cityUpperCase)
                            .setAddress(officeAddress)
                            .setUsd(usd)
                            .setEuro(euro)
                            .setRub(rub)
                            .build();

                    offices.add(office);
                }
            }
        }

        return offices;
    }

    private List<Double> getExchangeRates(BufferedReader reader) throws IOException {
        int index = 6;
        List<Double> currencies = new ArrayList<>(index);
        String line;
        boolean find = false;

        while ((line = reader.readLine()) != null && !find) {
            Matcher matcher = exchangeRates.matcher(line);

            if (matcher.find()) {
                do {
                    currencies.add(Double.parseDouble(matcher.group(1)));

                }while (matcher.find() && --index > 0);

                find = true;
            }
        }

        return currencies;
    }
}

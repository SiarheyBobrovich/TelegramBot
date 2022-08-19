package by.bobrovich.finance_service.parsers;

import by.bobrovich.finance_service.dao.entity.*;
import by.bobrovich.finance_service.parsers.api.IMyFinParser;
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
public class MyFinGomelParser implements IMyFinParser {

    private final RestTemplate restTemplate;
    private final String city;

    public MyFinGomelParser(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        city = "gomel";
    }

    @Override
    public List<Bank> getBanks(){
        final String upperCaseCity = city.toUpperCase();
        StringBuilder site = new StringBuilder();

        try {
            ClientHttpRequest request = restTemplate.getRequestFactory()
                    .createRequest(URI.create("https://myfin.by/currency/" + city), HttpMethod.GET);

            try(ClientHttpResponse execute = request.execute()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(execute.getBody()));
                String s;

                while ((s = reader.readLine()) != null) {
                    site.append(s);
                }
            }

        }catch (IOException e) {
            System.err.println(e.getMessage());
        }

        List<Bank> banks = findBanks(site.toString());
        banks.forEach(bank -> bank.setCity(upperCaseCity));

        return banks;
    }

    private List<Bank> findBanks(String site) {
        List<Bank> banks = new ArrayList<>();
        Matcher tableMatcher = Pattern.compile("(<table>)(.+?)(</table>)").matcher(site);

        while (tableMatcher.find()) {
            Bank bank = parseTable(tableMatcher.group(2));

            if (!Objects.isNull(bank)) {
                banks.add(bank);
            }
        }

        return banks;
    }

    private Bank parseTable(String table){
        final Matcher tableMatcher = Pattern.compile( "(<thead>(.*)</thead>).*(<tbody>(.*)</tbody>)").matcher(table);
        final Bank bank;

        if (tableMatcher.find()) {
            bank = parseHead(tableMatcher.group(2));

            if (!Objects.isNull(bank)) {
                bank.setOffices(parseBody(tableMatcher.group(4)));
            }
        }else {
            bank = null;
        }

        return bank;
    }

    private Bank parseHead(String table) {
        final Matcher bankNameMatcher = Pattern.compile(
                        "<th>Отдел\\p{LC}+?\\s([\\p{LC}\\p{Punct}\\s]+?)</th>")
                .matcher(table);

        final Bank bank;

        if (bankNameMatcher.find()) {
            bank = new Bank();
            bank.setName(bankNameMatcher.group(1));

        }else {
            bank = null;
        }

        return bank;
    }

    private Set<Office> parseBody(String body) {
        final Set<Office> offices = new HashSet<>();
        final String cityUpperCase = city.toUpperCase();

        final Matcher tableMatcher = Pattern.compile( "<tr>(.+?)</tr>").matcher(body);

        while (tableMatcher.find()) {
            Office office = parseBodyLine(tableMatcher.group());

            if (!Objects.isNull(office)) {
                office.setCity(cityUpperCase);
                offices.add(office);
            }
        }

        return offices;
    }

    private Office parseBodyLine(String bodyLine) {
        final Matcher columnMatcher = Pattern.compile( "<td>(.+?)</td>").matcher(bodyLine);
        final List<String> bodyLineColumn = new ArrayList<>();
        final Office office;

        for (int i = 0; columnMatcher.find() && i < 7; i++) {
            bodyLineColumn.add(columnMatcher.group(1));
        }

        if (bodyLineColumn.size() == 7) {
            office = parseFirstBodyLineColumn(bodyLineColumn.get(0));

            if (!Objects.isNull(office)) {
                Usd usd = new Usd();
                fillBuyAndSell(usd, bodyLineColumn.get(1), bodyLineColumn.get(2));

                Euro euro = new Euro();
                fillBuyAndSell(euro, bodyLineColumn.get(3), bodyLineColumn.get(4));

                Rub rub = new Rub();
                fillBuyAndSell(rub, bodyLineColumn.get(5), bodyLineColumn.get(6));

                office.setUsd(usd);
                office.setEuro(euro);
                office.setRub(rub);
            }

        }else {
            office = null;
        }

        return office;
    }

    private Office parseFirstBodyLineColumn(String bodyLineColumn) {
        final Matcher officeAddressMatcher = Pattern.compile( "<a.*>(.*)</a>").matcher(bodyLineColumn);
        final Office office;

        if (officeAddressMatcher.find()) {
            office = new Office();
            office.setAddress(officeAddressMatcher.group(1));

        } else {
            office = null;
        }

        return office;
    }

    private <T extends AbstractCurrency> void fillBuyAndSell(T currency, String buy, String sell) {
        final String doublePattern = "\\d+\\.?\\d*";
        final Matcher buyMatcher = Pattern.compile(doublePattern).matcher(buy);
        final Matcher sellMatcher = Pattern.compile(doublePattern).matcher(sell);

        if (buyMatcher.find() && sellMatcher.find()) {
            currency.setBuy(Double.parseDouble(buyMatcher.group()));
            currency.setSell(Double.parseDouble(sellMatcher.group()));
        }
    }
}

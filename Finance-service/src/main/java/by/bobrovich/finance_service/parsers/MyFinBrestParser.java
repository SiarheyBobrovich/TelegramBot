package by.bobrovich.finance_service.parsers;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MyFinBrestParser extends MyFinRegionalParser {

    public MyFinBrestParser(RestTemplate restTemplate) {
        super(restTemplate, "brest");
    }
}
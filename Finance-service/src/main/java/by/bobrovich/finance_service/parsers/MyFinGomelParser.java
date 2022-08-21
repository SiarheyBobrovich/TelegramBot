package by.bobrovich.finance_service.parsers;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MyFinGomelParser extends MyFinRegionalParser {

    public MyFinGomelParser(RestTemplate restTemplate) {
        super(restTemplate, "gomel");
    }
}

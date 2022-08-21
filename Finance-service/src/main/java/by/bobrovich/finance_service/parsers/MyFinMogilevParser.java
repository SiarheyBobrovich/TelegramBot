package by.bobrovich.finance_service.parsers;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MyFinMogilevParser extends MyFinRegionalParser {

    public MyFinMogilevParser(RestTemplate restTemplate) {
        super(restTemplate, "mogilev");
    }
}

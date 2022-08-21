package by.bobrovich.finance_service.parsers;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MyFinMinskParser extends MyFinRegionalParser {

    public MyFinMinskParser(RestTemplate restTemplate) {
        super(restTemplate, "minsk");
    }
}
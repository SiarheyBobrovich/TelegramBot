package by.bobrovich.finance_service.parsers;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MyFinVitebskParser extends MyFinRegionalParser {

    public MyFinVitebskParser(RestTemplate restTemplate) {
        super(restTemplate, "vitebsk");
    }
}

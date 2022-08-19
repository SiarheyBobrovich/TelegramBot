package by.bobrovich.finance_service.parsers;

import by.bobrovich.finance_service.dao.entity.*;
import by.bobrovich.finance_service.parsers.api.IMyFinParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {MyFinGomelParser.class, RestTemplate.class})
class MyFinGomelParserTest {

    @Autowired
    private IMyFinParser parser;

    @Test
    void getBrestBanks() {
        List<Bank> gomelBanks = parser.getBanks();

        for (Bank bank : gomelBanks) {
            assertEquals("GOMEL", bank.getCity());
            Set<Office> offices = bank.getOffices();

            assertFalse(offices.isEmpty());

            offices.forEach(office -> {
                assertNotNull(office.getAddress());
                Usd usd = office.getUsd();
                Euro euro = office.getEuro();
                Rub rub = office.getRub();

                assertNotNull(usd);
                assertNotNull(euro);
                assertNotNull(rub);

                assertTrue(usd.getBuy() < usd.getSell());
                assertTrue(euro.getBuy() < euro.getSell());
                assertTrue(rub.getBuy() < rub.getSell());
            });
        }
    }
}
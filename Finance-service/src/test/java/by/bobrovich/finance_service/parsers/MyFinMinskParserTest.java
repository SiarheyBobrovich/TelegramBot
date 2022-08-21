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

@SpringBootTest(classes = {
        MyFinMinskParser.class, RestTemplate.class
})
class MyFinMinskParserTest {

    @Autowired
    private IMyFinParser parser;

    @Test
    void getBrestBanks() {
        List<Bank> minskBanks = parser.getBanks();

        assertNotNull(minskBanks);
        assertFalse(minskBanks.isEmpty());
        assertEquals(22, minskBanks.size());

        for (Bank bank : minskBanks) {
            assertEquals("MINSK", bank.getCity());
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
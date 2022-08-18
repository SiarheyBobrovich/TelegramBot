package by.bobrovich.finance_service.services;

import by.bobrovich.finance_service.dao.api.ICurrencyRepository;
import by.bobrovich.finance_service.dao.entity.Euro;
import org.springframework.stereotype.Service;

@Service
public class EuroService extends AbstractCurrencyService<Euro> {

    public EuroService(ICurrencyRepository<Euro> repository) {
        super(repository);
    }
}

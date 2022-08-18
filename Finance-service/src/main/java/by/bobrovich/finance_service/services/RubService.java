package by.bobrovich.finance_service.services;

import by.bobrovich.finance_service.dao.api.ICurrencyRepository;
import by.bobrovich.finance_service.dao.entity.Rub;
import org.springframework.stereotype.Service;

@Service
public class RubService extends AbstractCurrencyService<Rub> {

    public RubService(ICurrencyRepository<Rub> repository) {
        super(repository);
    }
}
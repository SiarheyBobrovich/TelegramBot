package by.bobrovich.finance_service.services;

import by.bobrovich.finance_service.dao.api.ICurrencyRepository;
import by.bobrovich.finance_service.dao.entity.Usd;
import org.springframework.stereotype.Service;

@Service
public class UsdService extends AbstractCurrencyService<Usd> {

    public UsdService(ICurrencyRepository<Usd> repository) {
        super(repository);
    }
}
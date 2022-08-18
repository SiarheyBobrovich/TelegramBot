package by.bobrovich.finance_service.services;

import by.bobrovich.finance_service.dao.api.ICurrencyRepository;
import by.bobrovich.finance_service.dao.entity.AbstractCurrency;
import by.bobrovich.finance_service.services.api.ISaveInterface;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional(readOnly = true)
public abstract class AbstractCurrencyService<T extends AbstractCurrency> implements ISaveInterface<T> {

    private final ICurrencyRepository<T> repository;

    AbstractCurrencyService(ICurrencyRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void saveAll(Set<T> entities) {
        entities.forEach(this :: save);
    }

    @Override
    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }

}


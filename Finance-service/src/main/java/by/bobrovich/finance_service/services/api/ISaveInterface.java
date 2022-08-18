package by.bobrovich.finance_service.services.api;

import by.bobrovich.finance_service.dao.entity.api.IBankEntity;

import java.util.Set;

public interface ISaveInterface<T extends IBankEntity> {

    void saveAll(Set<T> entity);

    T save(T entity);

}
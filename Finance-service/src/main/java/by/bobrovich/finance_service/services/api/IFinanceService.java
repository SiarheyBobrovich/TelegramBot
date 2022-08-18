package by.bobrovich.finance_service.services.api;

import by.bobrovich.finance_service.dao.entity.Bank;

import java.util.List;

public interface IFinanceService {

    List<Bank> getAll();

    Bank getByName(String name);

    void saveAll(List<Bank> banks);

}

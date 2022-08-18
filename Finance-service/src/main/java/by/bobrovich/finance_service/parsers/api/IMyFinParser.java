package by.bobrovich.finance_service.parsers.api;

import by.bobrovich.finance_service.dao.entity.Bank;

import java.util.List;

public interface IMyFinParser {

    List<Bank> getBanks();
}

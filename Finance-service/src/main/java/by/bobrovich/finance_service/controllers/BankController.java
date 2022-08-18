package by.bobrovich.finance_service.controllers;

import by.bobrovich.finance_service.controllers.api.IBankControllerApi;
import by.bobrovich.finance_service.dao.entity.Bank;
import by.bobrovich.finance_service.dto.ResponseBank;
import by.bobrovich.finance_service.services.api.IFinanceService;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BankController implements IBankControllerApi {

    private final ConversionService conversionService;
    private final IFinanceService service;

    public BankController(ConversionService conversionService,
                          IFinanceService service) {
        this.conversionService = conversionService;
        this.service = service;
    }

    @Override
    public List<ResponseBank> getAll() {
        List<Bank> banks = service.getAll();

        return banks.stream()
                .map(x -> conversionService.convert(x, ResponseBank.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseBank getByBankName(@PathVariable String name) {
        Bank bank = service.getByName(name);

        return conversionService.convert(bank, ResponseBank.class);
    }

}
package by.bobrovich.finance_service.converters;

import by.bobrovich.finance_service.dao.entity.Bank;
import by.bobrovich.finance_service.dto.ResponseBank;
import by.bobrovich.finance_service.dto.ResponseOffice;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BankToResponseBankConverter implements Converter<Bank, ResponseBank> {

    @Override
    public ResponseBank convert(Bank source) {
        List<ResponseOffice> responseOffices = source.getOffices()
                .stream()
                .map(x -> new ResponseOffice(
                        x.getAddress(),
                        x.getUsd().getBuy(),
                        x.getUsd().getSell(),
                        x.getEuro().getBuy(),
                        x.getEuro().getSell(),
                        x.getRub().getBuy(),
                        x.getRub().getSell()
                )
        ).collect(Collectors.toList());

        return new ResponseBank(source.getName(), responseOffices);
    }

}

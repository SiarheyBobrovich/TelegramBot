package by.bobrovich.finance_service.controllers;

import by.bobrovich.finance_service.controllers.api.IOfficeControllerApi;
import by.bobrovich.finance_service.dao.entity.AbstractCurrency;
import by.bobrovich.finance_service.dao.entity.Office;
import by.bobrovich.finance_service.dto.PageDtos;
import by.bobrovich.finance_service.dto.ResponseDto;
import by.bobrovich.finance_service.enums.Currency;
import by.bobrovich.finance_service.services.api.IReadService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class OfficeController implements IOfficeControllerApi {

    private final IReadService<Office> service;

    public OfficeController(IReadService<Office> service) {
        this.service = service;
    }

    @Override
    public PageDtos<ResponseDto> findAllOffices(Currency currency,
                                                Currency.Operation operation,
                                                Integer page,
                                                Integer size) {

        Sort.Order by = operation.getOrder(currency);

        PageRequest officePage = PageRequest.of(page, size,
                Sort.by(by)
        );

        Page<Office> all = service.getAll(officePage);



        return PageDtos.of(all, all.getContent()
                .stream()
                .map(x -> {
                    AbstractCurrency c;

                    switch (currency) {
                        case USD:
                            c = x.getUsd();
                            break;
                        case EURO:
                            c = x.getEuro();
                            break;
                        case RUB:
                            c = x.getRub();
                            break;

                        default: throw new NotYetImplementedException();
                    }

                    double exchangeRate = operation.equals(Currency.Operation.BUY) ? c.getBuy() : c.getSell();

                    return new ResponseDto(x.getAddress(), exchangeRate);
                })
                .collect(Collectors.toList())
        );
    }

    @Override
    public PageDtos<ResponseDto> findAllOfficesInCity(Currency currency,
                                                Currency.Operation operation,
                                                String city,
                                                Integer page,
                                                Integer size) {

        Sort.Order by = operation.getOrder(currency);

        PageRequest officePage = PageRequest.of(page, size,
                Sort.by(by)
        );

        Page<Office> all = service.getAllByCity(city, officePage);

        return PageDtos.of(all, all.getContent()
                .stream()
                .map(x -> {
                    AbstractCurrency c;

                    switch (currency) {
                        case USD:
                            c = x.getUsd();
                            break;
                        case EURO:
                            c = x.getEuro();
                            break;
                        case RUB:
                            c = x.getRub();
                            break;

                        default: throw new NotYetImplementedException();
                    }

                    double exchangeRate = operation.equals(Currency.Operation.BUY) ? c.getBuy() : c.getSell();

                    return new ResponseDto(x.getAddress(), exchangeRate);
                })
                .collect(Collectors.toList())
        );
    }
}

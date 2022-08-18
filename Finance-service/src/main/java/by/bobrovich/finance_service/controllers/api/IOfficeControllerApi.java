package by.bobrovich.finance_service.controllers.api;

import by.bobrovich.finance_service.dto.PageDtos;
import by.bobrovich.finance_service.dto.ResponseDto;
import by.bobrovich.finance_service.enums.Currency;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/api/office")
public interface IOfficeControllerApi {

    @GetMapping("/{currency}/{operation}")
    PageDtos<ResponseDto> findAllOffices(
            @PathVariable Currency currency,
            @PathVariable Currency.Operation operation,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size);

    @GetMapping("/{currency}/{operation}/{city}")
    PageDtos<ResponseDto> findAllOfficesInCity(
            @PathVariable Currency currency,
            @PathVariable Currency.Operation operation,
            @PathVariable String city,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size);
}

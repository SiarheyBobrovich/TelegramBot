package by.bobrovich.finance_service.controllers.api;

import by.bobrovich.finance_service.dto.ResponseBank;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/api/bank")
public interface IBankControllerApi {

    @GetMapping("/")
    List<ResponseBank> getAll();

    @GetMapping("/{name}")
    ResponseBank getByBankName(@PathVariable String name);

    }

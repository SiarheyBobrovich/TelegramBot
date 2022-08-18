package by.bobrovich.finance_service.services;

import by.bobrovich.finance_service.dao.api.IBankRepository;
import by.bobrovich.finance_service.dao.entity.Bank;
import by.bobrovich.finance_service.dao.entity.Office;
import by.bobrovich.finance_service.services.api.IFinanceService;
import by.bobrovich.finance_service.services.api.ISaveInterface;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class BankService implements IFinanceService {

    private final IBankRepository bankRepository;
    private final ISaveInterface<Office> officeService;

    public BankService(IBankRepository bankRepository, ISaveInterface<Office> officeService) {
        this.bankRepository = bankRepository;
        this.officeService = officeService;
    }

    @Override
    public List<Bank> getAll() {
        return bankRepository.findAll(Sort.by("name"));
    }

    @Override
    public Bank getByName(String name) {
        if (name == null || !bankRepository.existsByName(name)) {
            throw new EntityExistsException();
        }

        return bankRepository.findByName(name).orElseThrow(EntityExistsException::new);
    }

    @Override
    @Transactional
    public void saveAll(List<Bank> banks) {

        banks.forEach(bank -> {
            Optional<Bank> optionalBank = bankRepository.findByNameAndCity(bank.getName(), bank.getCity());

            if (optionalBank.isPresent()) {
                Bank currentBank = optionalBank.get();
                bank.setUuid(currentBank.getUuid());

            }else {
                bank.setUuid(UUID.randomUUID());
            }

            bank.getOffices().forEach(office -> {
                office.setUuid(UUID.randomUUID());
                office.setBankUuid(bank.getUuid());
            });

            officeService.saveAll(bank.getOffices());

            bankRepository.save(bank);
        });
    }
}

package by.bobrovich.finance_service.services;

import by.bobrovich.finance_service.dao.api.IOfficeRepository;
import by.bobrovich.finance_service.dao.entity.Euro;
import by.bobrovich.finance_service.dao.entity.Office;
import by.bobrovich.finance_service.dao.entity.Rub;
import by.bobrovich.finance_service.dao.entity.Usd;
import by.bobrovich.finance_service.services.api.IReadService;
import by.bobrovich.finance_service.services.api.ISaveInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class OfficeService implements ISaveInterface<Office>, IReadService<Office> {

    private final IOfficeRepository officeRepository;
    private final AbstractCurrencyService<Usd> usdService;
    private final AbstractCurrencyService<Euro> euroService;
    private final AbstractCurrencyService<Rub> rubService;

    public OfficeService(IOfficeRepository officeRepository,
                         AbstractCurrencyService<Usd> usdService,
                         AbstractCurrencyService<Euro> euroService,
                         AbstractCurrencyService<Rub> rubService) {
        this.officeRepository = officeRepository;
        this.usdService = usdService;
        this.euroService = euroService;
        this.rubService = rubService;
    }

    @Override
    @Transactional
    public void saveAll(Set<Office> entity) {
        entity.forEach(this::save);
    }

    @Override
    @Transactional
    public Office save(Office office) {
        Optional<Office> optionalOffice = officeRepository.findByAddressAndBankUuid(office.getAddress(), office.getBankUuid());
        if (optionalOffice.isPresent()) {
            Office currentOffice = optionalOffice.get();
            office.setUuid(currentOffice.getUuid());

        }else {
            office.setUuid(UUID.randomUUID());
        }

        office.getUsd().setOfficeUuid(office.getUuid());
        office.getEuro().setOfficeUuid(office.getUuid());
        office.getRub().setOfficeUuid(office.getUuid());

        office.setUsd(usdService.save(office.getUsd()));
        office.setEuro(euroService.save(office.getEuro()));
        office.setRub(rubService.save(office.getRub()));

        return officeRepository.save(office);
    }

    @Override
    public Page<Office> getAll(Pageable pageable) {

        Page<Office> offices = officeRepository.findAll(pageable);

        if (offices.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return offices;
    }

    @Override
    public Page<Office> getAllByCity(String city, Pageable pageable) {

        Page<Office> offices = officeRepository.findByCity(city, pageable);

        if (offices.isEmpty()) {
            throw new EntityNotFoundException("city: " + city + "pageNumber: " + pageable.getPageNumber() + "size: " +  pageable.getPageSize());
        }

        return offices;
    }
}

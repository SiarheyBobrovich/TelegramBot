package by.bobrovich.finance_service.services.api;

import by.bobrovich.finance_service.dao.entity.Office;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;

public interface IReadService<T> {

    Page<T> getAll(Pageable pageable) throws EntityNotFoundException;
    Page<Office> getAllByCity(String city, Pageable pageable) throws EntityNotFoundException;
}

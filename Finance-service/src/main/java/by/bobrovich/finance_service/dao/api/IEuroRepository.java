package by.bobrovich.finance_service.dao.api;

import by.bobrovich.finance_service.dao.entity.Euro;
import org.springframework.stereotype.Repository;

@Repository
public interface IEuroRepository extends ICurrencyRepository<Euro> {
}
package by.bobrovich.finance_service.dao.api;

import by.bobrovich.finance_service.dao.entity.Rub;
import org.springframework.stereotype.Repository;

@Repository
public interface IRubRepository extends ICurrencyRepository<Rub> {
}
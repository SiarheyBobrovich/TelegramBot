package by.bobrovich.finance_service.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;


@NoRepositoryBean
public interface ICurrencyRepository<T> extends JpaRepository<T, UUID> {

}
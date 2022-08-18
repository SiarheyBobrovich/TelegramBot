package by.bobrovich.finance_service.dao.api;

import by.bobrovich.finance_service.dao.entity.Office;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IOfficeRepository extends JpaRepository<Office, UUID> {

    Optional<Office> findByAddressAndBankUuid(String address, UUID uuid);

    Page<Office> findByCity(String city, Pageable pageable);

}

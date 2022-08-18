package by.bobrovich.finance_service.dao.api;

import by.bobrovich.finance_service.dao.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IBankRepository extends JpaRepository<Bank, UUID> {

    boolean existsByName(String name);

    @Query("select b from Bank b where b.name = ?1")
    Optional<Bank> findByName(String name);

    @Query("select b from Bank b where b.name = ?1 and b.city = ?2")
    Optional<Bank> findByNameAndCity(String name, String city);



}

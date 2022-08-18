package by.it_academy.user.dao.api;

import by.it_academy.user.dao.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleDao extends JpaRepository<Role, Long> {
    Optional<Role> findByAuthority(String authority);
}

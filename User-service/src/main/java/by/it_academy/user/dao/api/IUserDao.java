package by.it_academy.user.dao.api;

import by.it_academy.user.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserDao extends JpaRepository<User, UUID> {

    boolean existsByChatId(long chatId);

    Optional<User> findByChatId(long chatId);

    User findByUsername(String username);
}

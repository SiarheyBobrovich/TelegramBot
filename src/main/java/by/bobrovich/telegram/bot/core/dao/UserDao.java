package by.bobrovich.telegram.bot.core.dao;

import by.bobrovich.telegram.bot.core.entity.User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class UserDao {

    private Map<Long, User> users;

    public UserDao(Map<Long, User> users) {
        this.users = users;
    }

    void save(User user) {
        users.put(user.getId(), user);
    }
}

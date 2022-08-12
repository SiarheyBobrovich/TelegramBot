package by.bobrovich.telegram.bot.dao;

import by.bobrovich.telegram.bot.dao.entity.User;
import org.springframework.stereotype.Service;

import java.util.Map;

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

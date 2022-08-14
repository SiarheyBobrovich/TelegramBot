package by.bobrovich.telegram.bot.service;

import by.bobrovich.telegram.bot.dto.user.SaveUser;
import by.bobrovich.telegram.bot.dto.user.User;
import by.bobrovich.telegram.bot.exceptions.LoadUserException;
import by.bobrovich.telegram.bot.exceptions.SaveUserException;
import by.bobrovich.telegram.bot.service.api.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService implements IUserService {

    private final RestTemplate template;

    public UserService(RestTemplate template) {
        this.template = template;
    }

    @Override
    public void save(SaveUser user) throws SaveUserException {
        ResponseEntity<String> stringResponseEntity = template.postForEntity("", user, String.class);
        if (!stringResponseEntity.getStatusCode().is2xxSuccessful()) {
            throw new SaveUserException(stringResponseEntity.getStatusCode());
        }
    }

    @Override
    public User load(long chatId) throws LoadUserException {
        ResponseEntity<User> forEntity = template.getForEntity("http://localhost:81/api/v1/users/me/" + chatId, User.class);

        if (!forEntity.getStatusCode().is2xxSuccessful()) {
            throw new LoadUserException(forEntity.getStatusCode());
        }

        return forEntity.getBody();
    }
}

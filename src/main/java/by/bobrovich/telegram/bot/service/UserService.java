package by.bobrovich.telegram.bot.service;

import by.bobrovich.telegram.bot.dto.user.SaveUser;
import by.bobrovich.telegram.bot.dto.user.User;
import by.bobrovich.telegram.bot.dto.user.enums.Status;
import by.bobrovich.telegram.bot.exceptions.LoadUserException;
import by.bobrovich.telegram.bot.exceptions.SaveUserException;
import by.bobrovich.telegram.bot.service.api.IUserService;
import by.bobrovich.telegram.bot.utils.LocalDateTimeUtil;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneId;

@Service
public class UserService implements IUserService {

    private final RestTemplate template;
    private final ConversionService conversionService;

    public UserService(RestTemplate template, ConversionService conversionService) {
        this.template = template;
        this.conversionService = conversionService;
    }

    @Override
    public void save(SaveUser user) throws SaveUserException, LoadUserException {
        ResponseEntity<String> stringResponseEntity = template.postForEntity("http://localhost:81/api/v1/users/registration", user, String.class);

        if (!stringResponseEntity.getStatusCode().is2xxSuccessful()) {
            throw new SaveUserException(stringResponseEntity.getStatusCode());
        }
    }

    @Override
    public User load(long chatId) throws LoadUserException {
        ResponseEntity<User> forEntity;

        try {
            forEntity = template.getForEntity("http://localhost:81/api/v1/users/me/" + chatId, User.class);
        }catch (RestClientException e) {
            try {
                save(conversionService.convert(chatId, SaveUser.class));

                forEntity = template.getForEntity("http://localhost:81/api/v1/users/me/" + chatId, User.class);

            }catch (SaveUserException exception) {
                System.err.println(exception.getMessage());
                throw new LoadUserException("Сервис временно недоступен.");
            }
        }

        return forEntity.getBody();
    }

    @Override
    public void updateCity(User user, String city) throws SaveUserException {
        long dtUpdate = LocalDateTimeUtil.localDateTimeToLong(user.getDtUpdate());
        SaveUser saveUser = conversionService.convert(user, SaveUser.class);

        saveUser.setCity(city);

        template.put("http://localhost:81/api/v1/users/update/" + dtUpdate, saveUser);

    }

    @Override
    public void updateSize(User user, int size) throws SaveUserException {
        long dtUpdate = LocalDateTimeUtil.localDateTimeToLong(user.getDtUpdate());
        SaveUser saveUser = conversionService.convert(user, SaveUser.class);

        saveUser.setSize(size);

        template.put("http://localhost:81/api/v1/users/update/" + dtUpdate, saveUser);
    }
}

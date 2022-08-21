package by.bobrovich.telegram.bot.service;

import by.bobrovich.telegram.bot.dto.user.SaveUser;
import by.bobrovich.telegram.bot.dto.user.User;
import by.bobrovich.telegram.bot.exceptions.LoadUserException;
import by.bobrovich.telegram.bot.exceptions.SaveUserException;
import by.bobrovich.telegram.bot.service.api.IUserService;
import by.bobrovich.telegram.bot.utils.LocalDateTimeUtil;
import by.bobrovich.telegram.bot.utils.YamlPropertySourceFactory;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@ConfigurationProperties(prefix = "yaml")
@PropertySource(value = "classpath:application.yml", factory = YamlPropertySourceFactory.class)
public class UserService implements IUserService {

    private final RestTemplate template;
    private final ConversionService conversionService;

    private final String registration;
    private final String me;
    private final String update;

    public UserService(RestTemplate template,
                       ConversionService conversionService,
                       @Value("${user.service.registration}") String registration,
                       @Value("${user.service.about}")String aboutMe,
                       @Value("${user.service.update}")String update) {
        this.template = template;
        this.conversionService = conversionService;
        this.registration = registration;
        this.me = aboutMe;
        this.update = update;
    }

    @Override
    public void save(SaveUser user) throws SaveUserException, LoadUserException {
        ResponseEntity<String> stringResponseEntity = template.postForEntity(registration, user, String.class);

        if (!stringResponseEntity.getStatusCode().is2xxSuccessful()) {
            throw new SaveUserException(stringResponseEntity.getStatusCode());
        }
    }

    @Override
    public User load(long chatId) throws LoadUserException {
        ResponseEntity<User> forEntity;

        try {
            forEntity = template.getForEntity(me + chatId, User.class);
        }catch (RestClientException e) {
            try {
                save(conversionService.convert(chatId, SaveUser.class));

                forEntity = template.getForEntity(me + chatId, User.class);

            }catch (SaveUserException | RestClientException exception) {
                LogFactory.getLog(this.getClass()).error(exception.getMessage());

                throw new LoadUserException("Сервис временно недоступен");
            }
        }

        final User user = forEntity.getBody();
        user.setJwtToken(forEntity.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0));

        return forEntity.getBody();
    }

    @Override
    public void updateCity(User user, String city) throws SaveUserException {
        long dtUpdate = LocalDateTimeUtil.localDateTimeToLong(user.getDtUpdate());
        SaveUser saveUser = conversionService.convert(user, SaveUser.class);

        saveUser.setCity(city);

        template.put(update + dtUpdate, saveUser);

    }

    @Override
    public void updateSize(User user, int size) throws SaveUserException {
        long dtUpdate = LocalDateTimeUtil.localDateTimeToLong(user.getDtUpdate());
        SaveUser saveUser = conversionService.convert(user, SaveUser.class);

        saveUser.setSize(size);

        template.put(update + dtUpdate, saveUser);
    }
}

package by.bobrovich.telegram.bot.service;

import by.bobrovich.telegram.bot.dto.user.Role;
import by.bobrovich.telegram.bot.dto.user.User;
import by.bobrovich.telegram.bot.exceptions.ForbiddenException;
import by.bobrovich.telegram.bot.exceptions.LoadUserException;
import by.bobrovich.telegram.bot.service.api.IAdministrationService;
import by.bobrovich.telegram.bot.service.api.IUserService;
import by.bobrovich.telegram.bot.utils.YamlPropertySourceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@Service
@PropertySource(value = "classpath:application.yml", factory = YamlPropertySourceFactory.class)
public class AdministrationService implements IAdministrationService {
    private final IUserService userService;
    private final String countUri;
    private final RestTemplate template;
    private final ObjectMapper mapper;

    public AdministrationService(IUserService userService,
                                 @Value("${admin.user.count}") String countUri,
                                 RestTemplate template, ObjectMapper mapper) {
        this.userService = userService;
        this.countUri = countUri;
        this.template = template;
        this.mapper = mapper;
    }

    @Override
    public void sendMsg(long chatId, AbsSender absSender, String... arg) {
        final SendMessage.SendMessageBuilder messageBuilder = SendMessage.builder()
                .chatId(chatId)
                .parseMode(ParseMode.MARKDOWNV2);

        final User user;
        try {
            user = userService.load(chatId);

        }catch (LoadUserException e) {
            messageBuilder.text(e.getMessage());
            execute(absSender, messageBuilder.build());
            return;
        }

        if (!user.getAuthorities().contains(new Role("ROLE_ADMIN"))) {
            messageBuilder.text("Нет прав");
            execute(absSender, messageBuilder.build());
            return;
        }

        try {
            int countUsers = getCountUsers(user.getJwtToken());
            messageBuilder.text("Текущее колличество пользователей: " + countUsers);

        }catch (IOException | ForbiddenException exception) {
            LogFactory.getLog(this.getClass()).error(List.of(exception, user));
        }

        execute(absSender, messageBuilder.build());
    }

    private int getCountUsers(String jwtToken) throws IOException, ForbiddenException {
        final Integer countUsers;
        final ClientHttpRequest request = template.getRequestFactory()
                .createRequest(URI.create(countUri), HttpMethod.GET);
        request.getHeaders().put(HttpHeaders.AUTHORIZATION, List.of("Bearer " + jwtToken));

        try(ClientHttpResponse execute = request.execute()) {
            if (!execute.getStatusCode().is2xxSuccessful()) {
                throw new ForbiddenException();
            }

            countUsers = mapper.readValue(execute.getBody(), Integer.class);
        }

        return countUsers;
    }
}

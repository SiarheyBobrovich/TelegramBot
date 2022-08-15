package by.bobrovich.telegram.bot.service;


import by.bobrovich.telegram.bot.dto.user.User;
import by.bobrovich.telegram.bot.exceptions.LoadUserException;
import by.bobrovich.telegram.bot.service.api.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

class UserServiceTest {

    private static final IUserService service = new UserService(new RestTemplate(), conversionService);


    @ParameterizedTest
    @ValueSource(longs = {23132L})
    void load(long chatId) throws LoadUserException {
        User user;
        Assertions.assertDoesNotThrow(() -> service.load(chatId));
        user = service.load(chatId);

        assert user != null;
    }

    @ParameterizedTest
    @ValueSource(longs = {-1L})
    void loadFail(long chatId) throws LoadUserException {
        Assertions.assertThrows(HttpClientErrorException.class, () -> service.load(chatId));
    }
}
package by.bobrovich.telegram.bot.service;


import by.bobrovich.telegram.bot.service.api.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.web.client.RestTemplate;

class UserServiceTest {

    @Value("${user.service.registration}")
    private static String registration;
    @Value("${user.service.about}")
    private static String me;
    @Value("${user.service.update}")
    private static String update;

    private static final IUserService service = new UserService(new RestTemplate(), new ConversionServiceFactoryBean().getObject(), registration, me, update);


//    @ParameterizedTest
//    @ValueSource(longs = {23132L})
//    void load(long chatId) throws LoadUserException {
//        User user;
//        Assertions.assertDoesNotThrow(() -> service.load(chatId));
//        user = service.load(chatId);
//
//        assert user != null;
//    }
//
//    @ParameterizedTest
//    @ValueSource(longs = {-1L})
//    void loadFail(long chatId) throws LoadUserException {
//        Assertions.assertThrows(HttpClientErrorException.class, () -> service.load(chatId));
//    }
}
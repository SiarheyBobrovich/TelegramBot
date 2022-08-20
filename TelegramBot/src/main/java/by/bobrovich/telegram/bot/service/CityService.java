package by.bobrovich.telegram.bot.service;

import by.bobrovich.telegram.bot.dto.user.User;
import by.bobrovich.telegram.bot.exceptions.LoadUserException;
import by.bobrovich.telegram.bot.exceptions.SaveUserException;
import by.bobrovich.telegram.bot.service.api.ICityService;
import by.bobrovich.telegram.bot.service.api.IUserService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Service
public class CityService implements ICityService {
    private final IUserService userService;

    public CityService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void sendMsg(final long chatId,
                        final AbsSender absSender,
                        final String... arg) {
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

        if (arg.length == 2) {
            try {
                userService.updateCity(user, arg[1]);
                messageBuilder.text("Сохранено");

            } catch (SaveUserException e) {
                messageBuilder.text("Сервис временно недоступен");
            }

        }else {
            messageBuilder.text("Укажите город");
        }

        execute(absSender, messageBuilder.build());
    }
}

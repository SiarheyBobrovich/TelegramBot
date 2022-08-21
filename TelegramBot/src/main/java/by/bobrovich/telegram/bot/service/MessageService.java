package by.bobrovich.telegram.bot.service;

import by.bobrovich.telegram.bot.exceptions.LoadUserException;
import by.bobrovich.telegram.bot.keyboard.KeyboardsContainer;
import by.bobrovich.telegram.bot.service.api.IMessageService;
import by.bobrovich.telegram.bot.service.api.IUserService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Service
public class MessageService implements IMessageService {
    private final IUserService userService;
    private final KeyboardsContainer keyboardsContainer;

    public MessageService(IUserService userService, KeyboardsContainer keyboardsContainer) {
        this.userService = userService;
        this.keyboardsContainer = keyboardsContainer;
    }

    @Override
    public void sendMsg(final long chatId,
                        final AbsSender absSender,
                        final String... arg) {
        SendMessage.SendMessageBuilder messageBuilder = SendMessage.builder()
                .chatId(chatId)
                .parseMode(ParseMode.MARKDOWNV2);

        final String textFromUser = arg[0];

        try {
            userService.load(chatId);
            messageBuilder.text(keyboardsContainer.getMessage(textFromUser));
            messageBuilder.replyMarkup(keyboardsContainer.getReplyKeyboardMarkup(arg[0]));


        }catch (LoadUserException e) {
            messageBuilder.text(e.getMessage());
        }

        execute(absSender, messageBuilder.build());
    }
}

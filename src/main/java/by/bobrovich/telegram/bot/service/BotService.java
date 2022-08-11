package by.bobrovich.telegram.bot.service;

import by.bobrovich.telegram.bot.utils.KeyboardsNameUtil;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class BotService {

    private final ButtonService buttonService;


    public BotService(ButtonService buttonService) {
        this.buttonService = buttonService;
    }

    public SendMessage sendMsg(Message message) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(message.getChatId())
                .replyToMessageId(message.getMessageId())
                .text("abra")
                .build();

        buttonService.addKeyBoard(KeyboardsNameUtil.getKeyboardsName(message.getText()), sendMessage);

        sendMessage.enableMarkdown(true);

        return sendMessage;
    }
}

package by.bobrovich.telegram.bot.adapter;

import by.bobrovich.telegram.bot.adapter.api.IBotMessagesAdapter;
import by.bobrovich.telegram.bot.service.api.*;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class MessageAdapter implements IBotMessagesAdapter {

    private final IMessageService messageService;
    private final ICityService cityService;
    private final ISizeService sizeService;
    private final ICurrencyService currencyService;
    private final IAdministrationService administrationService;


    public MessageAdapter(IMessageService messageService,
                          ICityService cityService,
                          ISizeService sizeService,
                          ICurrencyService currencyService, IAdministrationService administrationService) {
        this.messageService = messageService;
        this.cityService = cityService;
        this.sizeService = sizeService;
        this.currencyService = currencyService;
        this.administrationService = administrationService;
    }

    @Override
    public void sendMsg(Message message, AbsSender absSender) {
        if (message.getText().startsWith("ADMIN")) {
            administrationService.sendMsg(message.getChatId(), absSender, message.getText());
        }else {
            messageService.sendMsg(message.getChatId(), absSender, message.getText());
        }
    }

    @Override
    public void sendMsg(CallbackQuery query, AbsSender absSender) {
        Long chatId = query.getMessage().getChatId();
        String[] arg = query.getData().split(" ");

        switch (arg[0]) {
            case "CITY":
                cityService.sendMsg(query.getMessage().getChatId(), absSender, arg);
                break;

            case "SIZE":
                sizeService.sendMsg(chatId, absSender, arg);
                break;

            case "CURRENCY":
                currencyService.sendMsg(chatId, absSender, arg);
                break;

            default:
                messageService.sendMsg(chatId, absSender, "Меню");
        }
    }
}

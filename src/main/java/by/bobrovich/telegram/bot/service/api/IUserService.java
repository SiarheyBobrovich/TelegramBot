package by.bobrovich.telegram.bot.service.api;

import by.bobrovich.telegram.bot.dto.user.SaveUser;
import by.bobrovich.telegram.bot.dto.user.User;
import by.bobrovich.telegram.bot.exceptions.LoadUserException;
import by.bobrovich.telegram.bot.exceptions.SaveUserException;

public interface IUserService {
    void save(SaveUser user) throws SaveUserException;
    User load(long chatId) throws LoadUserException;
}

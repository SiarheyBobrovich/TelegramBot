package by.it_academy.user.services.api;

import by.it_academy.user.dto.request.UserRegistrationDto;
import by.it_academy.user.dto.request.UserUpdateDto;
import by.it_academy.user.dto.response.ResponseUser;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Validated
public interface IUserPersonalService {

    /**
     * Method to validate a new user and save in database
     * @param newUser User to save
     */
    void save(@Valid UserRegistrationDto newUser);

    /**
     * Method to validate a new user and update in database
     * @param updatedUser User to update
     */
    void update(@Valid UserUpdateDto updatedUser, LocalDateTime dtUpdate);

    /**
     * Method to find user by mail
     * @param chatId User's chatId
     * @return Information about
     */
    ResponseUser getByChatId(@Valid long chatId);


}
package by.it_academy.user.controllers;

import by.it_academy.user.dto.request.UserRegistrationDto;
import by.it_academy.user.dto.request.UserUpdateDto;
import by.it_academy.user.dto.response.ResponseUser;
import by.it_academy.user.services.api.IUserPersonalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


@RestController
@RequestMapping("/api/v1/users")
public class PersonalController {

    private final IUserPersonalService service;

    public PersonalController(IUserPersonalService service) {
        this.service = service;
    }

    @PostMapping("/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void registration(@RequestBody UserRegistrationDto newUser) {
        service.save(newUser);
    }

    @GetMapping("/me/{chat_id}")
    public ResponseUser getInformationAbout(@PathVariable(name = "chat_id") Long chatId) {
        return service.getByChatId(chatId);
    }

    @PutMapping("/update/{dt_update}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void update(@RequestBody UserUpdateDto updatedUser,
                       @PathVariable(name = "dt_update") Long dtUpdate) {

        LocalDateTime updateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.of("UTC"));
        service.update(updatedUser, updateTime);
    }
}

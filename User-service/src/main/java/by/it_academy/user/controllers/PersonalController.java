package by.it_academy.user.controllers;

import by.it_academy.user.dao.entity.User;
import by.it_academy.user.dto.request.UserRegistrationDto;
import by.it_academy.user.dto.request.UserUpdateDto;
import by.it_academy.user.dto.response.ResponseUser;
import by.it_academy.user.services.api.IUserPersonalService;
import by.it_academy.user.utils.JwtTokenUtil;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
public class PersonalController {

    private final IUserPersonalService service;

    private final ConversionService conversionService;

    public PersonalController(IUserPersonalService service, ConversionService conversionService) {
        this.service = service;
        this.conversionService = conversionService;
    }

    @PostMapping("/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void registration(@RequestBody UserRegistrationDto newUser) {
        service.save(newUser);
    }

    @GetMapping("/me/{chat_id}")
    public ResponseEntity<ResponseUser> getInformationAbout(@PathVariable(name = "chat_id") Long chatId) {
        User user = service.getByChatId(chatId);
        HttpHeaders headers = new HttpHeaders();

        headers.put(HttpHeaders.AUTHORIZATION, List.of(JwtTokenUtil.generateAccessToken(user)));

        return ResponseEntity.ok()
                .headers(headers)
                .body(conversionService.convert(user, ResponseUser.class));
    }

    @PutMapping("/update/{dt_update}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void update(@RequestBody UserUpdateDto updatedUser,
                       @PathVariable(name = "dt_update") Long dtUpdate) {

        LocalDateTime updateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.of("UTC"));
        service.update(updatedUser, updateTime);
    }
}

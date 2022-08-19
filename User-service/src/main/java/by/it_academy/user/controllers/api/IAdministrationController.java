package by.it_academy.user.controllers.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/administration")
public interface IAdministrationController {

    @GetMapping("/user/count")
    ResponseEntity<Long> getCountUsers();
}
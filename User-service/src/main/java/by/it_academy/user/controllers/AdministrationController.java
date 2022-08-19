package by.it_academy.user.controllers;

import by.it_academy.user.controllers.api.IAdministrationController;
import by.it_academy.user.services.AdministrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdministrationController implements IAdministrationController {

    private final AdministrationService administrationService;

    public AdministrationController(AdministrationService administrationService) {
        this.administrationService = administrationService;
    }

    @Override
    public ResponseEntity<Long> getCountUsers() {
        return ResponseEntity.ok(administrationService.getCountUsers());
    }
}

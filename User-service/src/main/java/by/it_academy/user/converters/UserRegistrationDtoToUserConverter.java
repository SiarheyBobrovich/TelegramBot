package by.it_academy.user.converters;

import by.it_academy.user.converters.api.AbstractConverterWithEncoder;
import by.it_academy.user.dao.entity.Role;
import by.it_academy.user.dao.entity.User;
import by.it_academy.user.dao.enums.Roles;
import by.it_academy.user.dto.request.UserRegistrationDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.UUID;

@Component
public class UserRegistrationDtoToUserConverter extends AbstractConverterWithEncoder<UserRegistrationDto, User> {

    public UserRegistrationDtoToUserConverter(PasswordEncoder encoder) {
        super(encoder);
    }

    @Override
    public User convert(UserRegistrationDto source) {
        LocalDateTime now = LocalDateTime.now();

        return User.builder()
                .setUuid(UUID.randomUUID())
                .setUsername(source.getUsername())
                .setPassword(encoder.encode(source.getPassword()))
                .setChatId(source.getChatId())
                .setCity(source.getCity())
                .setAuthorities(Set.of(Role.of(Roles.ROLE_USER)))
                .setSize(source.getSize())
                .setStatus(source.getStatus())
                .setDtCreate(now)
                .setDtUpdate(now.truncatedTo(ChronoUnit.MILLIS))
                .build();
    }
}

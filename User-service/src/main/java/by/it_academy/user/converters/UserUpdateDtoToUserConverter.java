package by.it_academy.user.converters;

import by.it_academy.user.converters.api.AbstractConverterWithEncoder;
import by.it_academy.user.dao.entity.User;
import by.it_academy.user.dto.request.UserUpdateDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateDtoToUserConverter extends AbstractConverterWithEncoder<UserUpdateDto, User> {

    public UserUpdateDtoToUserConverter(PasswordEncoder encoder) {
        super(encoder);
    }

    @Override
    public User convert(UserUpdateDto source) {
        return User.builder()
                .setUsername(source.getUsername())
                .setPassword(encoder.encode(source.getPassword()))
                .setChatId(source.getChatId())
                .setCity(source.getCity())
                .setSize(source.getSize())
                .setStatus(source.getStatus())
                .build();
    }
}

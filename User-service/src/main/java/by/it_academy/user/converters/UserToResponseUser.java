package by.it_academy.user.converters;

import by.it_academy.user.dao.entity.User;
import by.it_academy.user.dto.response.ResponseRole;
import by.it_academy.user.dto.response.ResponseUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserToResponseUser implements Converter<User, ResponseUser> {

    @Override
    public ResponseUser convert(User source) {
        return ResponseUser.create()
                .setUsername(source.getUsername())
                .setChatId(source.getChatId())
                .setCity(source.getCity())
                .setStatus(source.getStatus())
                .setAuthorities(source.getAuthorities()
                        .stream()
                        .map(role -> new ResponseRole(role.getAuthority()))
                        .collect(Collectors.toSet())
                ).setDtUpdate(source.getDtUpdate())
                .setSize(source.getSize())
                .build();
    }
}

package by.it_academy.user.converters.api;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class AbstractConverterWithEncoder<S, T> implements Converter<S, T>{

    protected final PasswordEncoder encoder;

    protected AbstractConverterWithEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

}

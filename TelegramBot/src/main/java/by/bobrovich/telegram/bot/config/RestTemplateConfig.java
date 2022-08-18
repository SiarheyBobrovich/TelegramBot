package by.bobrovich.telegram.bot.config;

import by.bobrovich.telegram.bot.utils.LocalDateTimeDeserializer;
import by.bobrovich.telegram.bot.utils.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(MappingJackson2HttpMessageConverter converter) {
        return new RestTemplateBuilder()
                .messageConverters(converter)
                .build();
    }

    @Bean
    public MappingJackson2HttpMessageConverter createMappingJacksonHttpMessageConverter(Jackson2ObjectMapperFactoryBean factoryBean) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON));
        converter.setObjectMapper(factoryBean.getObject());
        return converter;
    }

    @Bean
    @Primary
    public Jackson2ObjectMapperFactoryBean jackson2ObjectMapperFactoryBean() {
        Jackson2ObjectMapperFactoryBean factoryBean = new Jackson2ObjectMapperFactoryBean();

        factoryBean.setObjectMapper(new ObjectMapper());
        factoryBean.setPropertyNamingStrategy(SNAKE_CASE);
        factoryBean.setModulesToInstall(JavaTimeModule.class);
        factoryBean.setDeserializersByType(Map.of(LocalDateTime.class , new LocalDateTimeDeserializer()));
        factoryBean.setSerializersByType(Map.of(LocalDateTime.class , new LocalDateTimeSerializer()));

        return factoryBean;
    }
}

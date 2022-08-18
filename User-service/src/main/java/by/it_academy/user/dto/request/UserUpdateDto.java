package by.it_academy.user.dto.request;

import by.it_academy.user.dao.enums.Status;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@JsonDeserialize(builder = UserUpdateDto.Builder.class)
public class UserUpdateDto implements Serializable {

    private final String username;
    private final String password;
    private final Long chatId;
    private final String city;
    private final Integer size;
    private final Status status;
    public UserUpdateDto(String username, String password, Long chatId, String city, Integer size, Status status) {
        this.username = username;
        this.password = password;
        this.chatId = chatId;
        this.city = city;
        this.size = size;
        this.status = status;
    }

    @NotBlank
    public String getUsername() {
        return username;
    }
    @NotBlank
    public String getPassword() {
        return password;
    }
    @Min(0)
    @NotNull
    public Long getChatId() {
        return chatId;
    }

    @NotNull
    @Min(1)
    public Integer getSize() {
        return size;
    }

    @NotNull
    @Pattern(regexp = "\\p{Alpha}{2,}", message = "не верный формат")
    public String getCity() {
        return city;
    }

    @NotNull
    public Status getStatus() {
        return status;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private String username;
        private String password;
        private Long chatId;
        private String city;
        private Integer size;
        private Status status;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setChatId(Long chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setSize(Integer size) {
            this.size = size;
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public UserUpdateDto build() {
            return new UserUpdateDto(
                    this.username,
                    this.password,
                    this.chatId,
                    this.city,
                    this.size,
                    this.status
            );
        }
    }
}

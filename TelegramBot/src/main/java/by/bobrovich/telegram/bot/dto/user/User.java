package by.bobrovich.telegram.bot.dto.user;

import by.bobrovich.telegram.bot.dto.user.enums.Status;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDateTime;
import java.util.Set;

@JsonDeserialize(builder = User.Builder.class)
public class User {
    private final String username;
    private final long chatId;
    private final String city;
    private final int size;
    private final Set<Role> authorities;
    private final Status status;
    private final LocalDateTime dtUpdate;

    private String jwtToken;

    public User(String username,
                long chatId,
                String city,
                int size,
                Set<Role> authorities,
                Status status,
                LocalDateTime dtUpdate) {
        this.username = username;
        this.chatId = chatId;
        this.city = city;
        this.size = size;
        this.authorities = authorities;
        this.status = status;
        this.dtUpdate = dtUpdate;
    }

    public String getUsername() {
        return username;
    }

    public long getChatId() {
        return chatId;
    }

    public String getCity() {
        return city;
    }

    public int getSize() {
        return size;
    }

    public Set<Role> getAuthorities() {
        return authorities;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {

        private Builder() {}

        private String username;
        private long chatId;
        private String city;
        private int size;
        private Set<Role> authorities;
        private Status status;

        private LocalDateTime dtUpdate;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setChatId(long chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public Builder setAuthorities(Set<Role> authorities) {
            this.authorities = authorities;
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder setDtUpdate(LocalDateTime dtUpdate) {
            this.dtUpdate = dtUpdate;
            return this;
        }

        public User build() {
            return new User(
                    username,
                    chatId,
                    city,
                    size,
                    authorities,
                    status,
                    dtUpdate
            );
        }
    }
}

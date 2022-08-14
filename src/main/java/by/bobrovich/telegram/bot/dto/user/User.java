package by.bobrovich.telegram.bot.dto.user;

import by.bobrovich.telegram.bot.dto.user.enums.Roles;
import by.bobrovich.telegram.bot.dto.user.enums.Status;

import java.util.Set;

public class User {

    private final String username;
    private final long chatId;
    private final String city;
    private final Set<Roles> authorities;
    private final Status status;

    public User(String username, long chatId, String city, Set<Roles> authorities, Status status) {
        this.username = username;
        this.chatId = chatId;
        this.city = city;
        this.authorities = authorities;
        this.status = status;
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

    public Set<Roles> getAuthorities() {
        return authorities;
    }

    public Status getStatus() {
        return status;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;
        private long chatId;
        private String city;
        private Set<Roles> authorities;
        private Status status;

        public Builder setUsername(String username) {
            this.username = username;
            return  this;
        }

        public Builder setChatId(long chatId) {
            this.chatId = chatId;
            return  this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return  this;
        }

        public Builder setAuthorities(Set<Roles> authorities) {
            this.authorities = authorities;
            return  this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return  this;
        }

        public User build() {
            return new User(
                    username,
                    chatId,
                    city,
                    authorities,
                    status);
        }
    }
}

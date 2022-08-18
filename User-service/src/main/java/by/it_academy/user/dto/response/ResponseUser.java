package by.it_academy.user.dto.response;

import by.it_academy.user.dao.enums.Status;

import java.time.LocalDateTime;
import java.util.Set;

public class ResponseUser {
    private final String username;
    private final long chatId;
    private final String city;
    private final int size;
    private final Set<ResponseRole> authorities;
    private final Status status;
    private final LocalDateTime dtUpdate;

    public ResponseUser(String username, long chatId, String city, int size, Set<ResponseRole> authorities, Status status, LocalDateTime dtUpdate) {
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

    public Set<ResponseRole> getAuthorities() {
        return authorities;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public static Builder create() {
        return new Builder();
    }
    public static class Builder {

        private Builder(){}
        private String username;
        private long chatId;
        private String city;
        private Set<ResponseRole> authorities;
        private Status status;
        private int size;
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

        public Builder setAuthorities(Set<ResponseRole> authorities) {
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

        public ResponseUser build() {
            return new ResponseUser(
                    this.username,
                    this.chatId,
                    this.city,
                    this.size,
                    this.authorities,
                    this.status,
                    this.dtUpdate);
        }
    }
}

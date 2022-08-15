package by.bobrovich.telegram.bot.dto.user;

import by.bobrovich.telegram.bot.dto.user.enums.Status;

import java.io.Serializable;

public class SaveUser implements Serializable {

    private String username;
    private String password;
    private final long chatId;
    private String city;
    private int size;
    private Status status;

    public SaveUser(String username, String password, long chatId, String city, int size, Status status) {
        this.username = username;
        this.password = password;
        this.chatId = chatId;
        this.city = city;
        this.size = size;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
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
    public Status getStatus() {
        return status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;
        private String password;
        private long chatId;
        private String city;
        private int size;
        private Status status;

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setChatId(long chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public SaveUser build() {
            return new SaveUser(
                    username,
                    password,
                    chatId,
                    city,
                    size,
                    status
            );
        }
    }
}

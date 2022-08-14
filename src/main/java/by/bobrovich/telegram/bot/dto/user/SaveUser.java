package by.bobrovich.telegram.bot.dto.user;

import java.io.Serializable;

public class SaveUser implements Serializable {

    private final String username;
    private final String password;
    private final Long chatId;
    private final String city;

    public SaveUser(String username, String password, Long chatId, String city) {
        this.username = username;
        this.password = password;
        this.chatId = chatId;
        this.city = city;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public Long getChatId() {
        return chatId;
    }

    public String getCity() {
        return city;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;
        private String password;
        private Long chatId;
        private String city;

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

        public Builder setChatId(Long chatId) {
            this.chatId = chatId;
            return this;
        }

        public SaveUser build() {
            return new SaveUser(
                    username,
                    password,
                    chatId,
                    city
            );
        }
    }
}

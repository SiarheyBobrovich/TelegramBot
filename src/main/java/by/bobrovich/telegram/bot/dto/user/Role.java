package by.bobrovich.telegram.bot.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Role {
    private final String role;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Role(@JsonProperty("role") String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

package by.it_academy.user.dao.entity;

import by.it_academy.user.dao.enums.Status;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(schema = "security", name = "users")
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;
    private UUID uuid;
    private String username;
    private long chatId;
    private String password;
    private String city;
    private int size;
    private Set<Role> authorities;
    private Status status;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;

    @Id
    @Column(name = "uuid", nullable = false, updatable = false, unique = true)
    public UUID getUuid() {
        return uuid;
    }

    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    @Column(name = "chat_Id", nullable = false, unique = true)
    public long getChatId() {
        return chatId;
    }

    @Override
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    @Column(name = "city", nullable = false)
    public String getCity() {
        return city;
    }

    @Column(name = "size", nullable = false)
    public int getSize() {
        return size;
    }

    @Override
    @OneToMany(fetch = FetchType.EAGER)
    public Set<Role> getAuthorities() {
        return authorities;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    public Status getStatus() {
        return status;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return !status.equals(Status.WAITING_ACTIVATION);
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return !status.equals(Status.DEACTIVATED);
    }

    @Column(name = "dt_create", nullable = false, updatable = false)
    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    @Version
    @Column(name = "dt_update", nullable = false)
    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setAuthorities(Set<Role> roles) {
        this.authorities = roles;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(uuid, user.uuid) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(authorities, user.authorities) &&
                status == user.status &&
                Objects.equals(dtCreate, user.dtCreate) &&
                Objects.equals(dtUpdate, user.dtUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid,
                username,
                password,
                authorities,
                status,
                dtCreate,
                dtUpdate
        );
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Builder() {}
        private UUID uuid;
        private String username;
        private long chatId;
        private String password;
        private String city;
        private Set<Role> authorities;
        private Status status;
        private int size;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setChatId(long chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
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

        public Builder setDtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public Builder setDtUpdate(LocalDateTime dtUpdate) {
            this.dtUpdate = dtUpdate;
            return this;
        }

        public User build() {
            User user = new User();

            user.setUuid(this.uuid);
            user.setUsername(this.username);
            user.setPassword(this.password);
            user.setChatId(this.chatId);
            user.setCity(city);
            user.setAuthorities(this.authorities);
            user.setStatus(this.status);
            user.setDtCreate(this.dtCreate);
            user.setDtUpdate(this.dtUpdate);
            user.setSize(size);

            return user;
        }
    }
}
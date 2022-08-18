package by.it_academy.user.dao.entity;

import by.it_academy.user.dao.enums.Roles;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

/**
 * User's roles
 */
@Entity
@Table(schema = "security", name = "roles")
public class Role implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String authority;

    public Role() {
    }

    private Role(String authority) {
        this.authority = authority;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Override
    @Column(name = "role", nullable = false)
    public String getAuthority() {
        return authority;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role1 = (Role) o;
        return Objects.equals(id, role1.id) && Objects.equals(authority, role1.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                '}';
    }

    public static Role of(Roles role) {
        return new Role(role.name());
    }
}

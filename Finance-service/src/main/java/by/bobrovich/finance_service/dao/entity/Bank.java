package by.bobrovich.finance_service.dao.entity;

import by.bobrovich.finance_service.dao.entity.api.IBankEntity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(schema = "finance", name = "banks")
public class Bank implements IBankEntity {

    public static final long serialVersionUID = 1L;
    private UUID uuid;
    private String city;
    private String name;
    private Set<Office> offices;

    @Id
    @Column(name = "uuid", nullable = false, unique = true)
    public UUID getUuid() {
        return uuid;
    }

    @Column(name = "city", nullable = false)
    public String getCity() {
        return city;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Office.class)
    public Set<Office> getOffices() {
        return offices;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOffices(Set<Office> offices) {
        this.offices = offices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bank)) return false;
        Bank bank = (Bank) o;
        return Objects.equals(name, bank.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

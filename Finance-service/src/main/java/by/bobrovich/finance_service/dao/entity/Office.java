package by.bobrovich.finance_service.dao.entity;


import by.bobrovich.finance_service.dao.entity.api.IBankEntity;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "finance", name = "offices")
public class Office implements IBankEntity {

    public static final long serialVersionUID = 1L;

    private UUID uuid;
    private UUID bankUuid;
    private String city;
    private String address;
    private Usd usd;
    private Euro euro;
    private Rub rub;

    @Id
    @Column(name = "uuid", nullable = false, unique = true)
    public UUID getUuid() {
        return uuid;
    }

    @Column(name = "bank_uuid", nullable = false)
    public UUID getBankUuid() {
        return bankUuid;
    }

    @Column(name = "city", nullable = false)
    public String getCity() {
        return city;
    }

    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }

    @OneToOne(fetch = FetchType.EAGER)
    public Usd getUsd() {
        return usd;
    }

    @OneToOne(fetch = FetchType.EAGER)
    public Euro getEuro() {
        return euro;
    }

    @OneToOne(fetch = FetchType.EAGER)
    public Rub getRub() {
        return rub;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setBankUuid(UUID bankUuid) {
        this.bankUuid = bankUuid;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUsd(Usd usd) {
        this.usd = usd;
    }

    public void setEuro(Euro euro) {
        this.euro = euro;
    }

    public void setRub(Rub rub) {
        this.rub = rub;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Office)) return false;
        Office office = (Office) o;
        return Objects.equals(address, office.address) && Objects.equals(bankUuid, office.bankUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID bankUuid;

        private String city;
        private String address;
        private Usd usd;
        private Euro euro;
        private Rub rub;

        private Builder() {}

        public Builder setBankUuid(UUID bankUuid) {
            this.bankUuid = bankUuid;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setUsd(Usd usd) {
            this.usd = usd;
            return this;
        }

        public Builder setEuro(Euro euro) {
            this.euro = euro;
            return this;
        }

        public Builder setRub(Rub rub) {
            this.rub = rub;
            return this;
        }

        public Office build() {
            Office office = new Office();

            office.setBankUuid(bankUuid);
            office.setCity(city);
            office.setAddress(address);
            office.setUsd(usd);
            office.setEuro(euro);
            office.setRub(rub);

            return office;
        }
    }
}

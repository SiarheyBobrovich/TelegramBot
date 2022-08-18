package by.bobrovich.finance_service.dao.entity;

import by.bobrovich.finance_service.dao.entity.api.IBankEntity;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractCurrency implements IBankEntity {

    private UUID officeUuid;
    private double buy;
    private double sell;

    @Id
    @Column(name = "office_uuid")
    public UUID getOfficeUuid() {
        return officeUuid;
    }

    public void setOfficeUuid(UUID officeUuid) {
        this.officeUuid = officeUuid;
    }

    @Column(name = "buy", nullable = false)
    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    @Column(name = "sell", nullable = false)
    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }
}

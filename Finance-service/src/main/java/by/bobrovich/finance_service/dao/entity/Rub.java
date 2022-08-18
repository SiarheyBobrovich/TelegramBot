package by.bobrovich.finance_service.dao.entity;

import javax.persistence.*;

@Entity
@Table(schema = "finance", name = "rubs")
public class Rub extends AbstractCurrency {

    public static final long serialVersionUID = 1L;
}

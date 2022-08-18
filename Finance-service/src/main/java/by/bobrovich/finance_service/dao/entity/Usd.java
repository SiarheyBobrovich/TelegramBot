package by.bobrovich.finance_service.dao.entity;

import javax.persistence.*;

@Entity
@Table(schema = "finance", name = "usds")
public class Usd extends AbstractCurrency {

    public static final long serialVersionUID = 1L;
}

package by.bobrovich.finance_service.dao.entity;

import javax.persistence.*;

@Entity
@Table(schema = "finance", name = "euros")
public class Euro extends AbstractCurrency {

    public static final long serialVersionUID = 1L;
}

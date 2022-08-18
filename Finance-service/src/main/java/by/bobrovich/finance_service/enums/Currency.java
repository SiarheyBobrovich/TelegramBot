package by.bobrovich.finance_service.enums;

import org.springframework.data.domain.Sort;

public enum Currency {
    USD,
    EURO,
    RUB;

    public enum Operation {
        BUY,
        SELL;

        @Override
        public String toString() {
            return name().toLowerCase();
        }

        public Sort.Order getOrder(Currency currency) {
            return !this.equals(Operation.BUY) ?
                    Sort.Order.asc(currency.toString() + "." + this) :
                    Sort.Order.desc(currency.toString() + "." + this);
        }
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

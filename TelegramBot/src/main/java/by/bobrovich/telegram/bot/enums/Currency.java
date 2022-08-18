package by.bobrovich.telegram.bot.enums;

public enum Currency {
    USD,
    EURO,
    RUB;

    public enum Operation {
        BUY("Продажа"),
        SELL("Покупка");

        private final String condition;

        Operation(String condition) {
            this.condition = condition;
        }

        public String getCondition() {
            return condition;
        }

        @Override
        public String toString() {
            return name().toLowerCase();
        }

    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

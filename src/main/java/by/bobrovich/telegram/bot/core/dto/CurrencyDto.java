package by.bobrovich.telegram.bot.core.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CurrencyDto.Builder.class)
public class CurrencyDto {

    private final String address;
    private final double exchangeRate;

    public CurrencyDto(String address, double exchangeRate) {
        this.address = address;
        this.exchangeRate = exchangeRate;
    }

    public String getAddress() {
        return address;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private String address;
        private double exchangeRate;

        private Builder() {
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setExchangeRate(double exchangeRate) {
            this.exchangeRate = exchangeRate;
            return this;
        }

        public CurrencyDto build() {
            return new CurrencyDto(address, exchangeRate);
        }
    }
}

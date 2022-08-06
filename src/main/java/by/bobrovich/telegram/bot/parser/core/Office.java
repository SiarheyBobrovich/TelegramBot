package by.bobrovich.telegram.bot.parser.core;

import java.util.List;

public class Office {

    private String address;
    private List<Double> currencies;

    public Office(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Double> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Double> currencies) {
        this.currencies = currencies;
    }
}

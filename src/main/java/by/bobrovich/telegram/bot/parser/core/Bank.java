package by.bobrovich.telegram.bot.parser.core;

import java.util.List;

public class Bank {

    private String name;
    private List<Office> offices;

    public Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }
}

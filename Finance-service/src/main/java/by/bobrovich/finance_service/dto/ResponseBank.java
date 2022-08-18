package by.bobrovich.finance_service.dto;

import java.util.List;

public class ResponseBank {

    private final String name;
    private final List<ResponseOffice> offices;

    public ResponseBank(String name, List<ResponseOffice> offices) {
        this.name = name;
        this.offices = offices;
    }

    public String getName() {
        return name;
    }

    public List<ResponseOffice> getOffices() {
        return offices;
    }
}

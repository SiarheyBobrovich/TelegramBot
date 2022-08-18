package by.bobrovich.finance_service.dto;

public class ResponseDto {

    private final String address;
    private final double exchangeRate;

    public ResponseDto(String address, double exchangeRate) {
        this.address = address;
        this.exchangeRate = exchangeRate;
    }

    public String getAddress() {
        return address;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }
}

package by.bobrovich.finance_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
public class ResponseOffice {
    private final String address;
    private final double usdBuy;
    private final double usdSell;
    private final double euroBuy;
    private final double euroSell;
    private final double rubBuy;
    private final double rubSell;

    public ResponseOffice(String address, double usdBuy, double usdSell, double euroBuy, double euroSell, double rubBuy, double rubSell) {
        this.address = address;
        this.usdBuy = usdBuy;
        this.usdSell = usdSell;
        this.euroBuy = euroBuy;
        this.euroSell = euroSell;
        this.rubBuy = rubBuy;
        this.rubSell = rubSell;
    }

    public String getAddress() {
        return address;
    }

    public double getUsdBuy() {
        return usdBuy;
    }

    public double getUsdSell() {
        return usdSell;
    }

    public double getEuroBuy() {
        return euroBuy;
    }

    public double getEuroSell() {
        return euroSell;
    }

    public double getRubBuy() {
        return rubBuy;
    }

    public double getRubSell() {
        return rubSell;
    }
}

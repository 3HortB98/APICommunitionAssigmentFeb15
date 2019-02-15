package com.example.apicommunication;

public class SymbolProfile {
    private String symbol;
    private String companyName;
    private String sector;
    private String primaryExchange;
    private double open;
    private double close;

    public SymbolProfile(String symbol, String companyName, String sector, String primaryExchange, double open, double close) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.sector = sector;
        this.primaryExchange = primaryExchange;
        this.open = open;
        this.close = close;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getPrimaryExchange() {
        return primaryExchange;
    }

    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }
}

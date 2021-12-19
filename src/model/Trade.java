package model;

import java.time.*;

public class Trade {
    private int id;
    private Sale sale;
    private Bid bid;
    private double finalPrice;
    private double purchaseTax;
    private double saleTax;
    private Instant ti;

    public Trade(int id, Sale sale, Bid bid, double purchaseTax, double saleTax, Instant ti) {
        this.id = id;
        this.sale = sale;
        this.bid = bid;
        this.finalPrice = bid.getPrice();
        this.purchaseTax = purchaseTax;
        this.saleTax = saleTax;
        this.ti = ti;
    }

    public Instant getTi() {
        return ti;
    }

    public void setTi(Instant ti) {
        this.ti = ti;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public double getPurchaseTax() {
        return purchaseTax;
    }

    public void setPurchaseTax(double purchaseTax) {
        this.purchaseTax = purchaseTax;
    }

    public double getSaleTax() {
        return saleTax;
    }

    public void setSaleTax(double saleTax) {
        this.saleTax = saleTax;
    }
}

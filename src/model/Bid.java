package model;

import java.time.*;

public class Bid {

    private int id;
    private Sale sale;
    private Member buyer;
    private double price;
    private Instant ti;

    public Bid(int id, Sale sale, Member member, double price, Instant ti) {
        this.id = id;
        this.sale = sale;
        this.buyer = member;
        this.price = Server.balanceFormat(price);
        this.ti = ti;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Member getBuyer() {
        return buyer;
    }

    public void setBuyer(Member buyer) {
        this.buyer = buyer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = Server.balanceFormat(price);
    }

    public Instant getTi() {
        return ti;
    }

    public void setTi(Instant ti) {
        this.ti = ti;
    }
}

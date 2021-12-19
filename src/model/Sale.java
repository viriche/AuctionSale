package model;

import java.time.*;

public class Sale {

    private int id;
    private Member seller;
    private Member finalBuyer;
    private String desc;
    private double askPrice;
    private int duration;
    private Bid bestBid;
    private boolean running;
    private Instant ti;
    public Instant to;

    public Sale(int id, Member seller, String desc, double askPrice, int duration, Instant ti) {
        this.id = id;
        this.seller = seller;
        this.finalBuyer = null;
        this.desc = desc;
        this.askPrice = Server.balanceFormat(askPrice);
        this.duration = duration;
        this.bestBid = null;
        this.running = true;
        this.ti = ti;
        this.to = null;
    }

    public double currentPrice(){
        if(this.bestBid==null) return this.askPrice;
        return this.bestBid.getPrice();
    }

    public Instant getTo() {
        return to;
    }

    public void setTo(Instant to) {
        this.to = to;
    }

    public Bid getBestBid() {
        return bestBid;
    }

    public void setBestBid(Bid bestBid) {
        this.bestBid = bestBid;
    }

    public Member getFinalBuyer() {
        return finalBuyer;
    }

    public void setFinalBuyer(Member finalBuyer) {
        this.finalBuyer = finalBuyer;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getSeller() {
        return seller;
    }

    public void setSeller(Member seller) {
        this.seller = seller;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(double askPrice) {
        this.askPrice = Server.balanceFormat(askPrice);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Instant getTi() {
        return ti;
    }

    public void setTi(Instant ti) {
        this.ti = ti;
    }
}

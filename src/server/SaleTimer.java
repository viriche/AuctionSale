package server;

import model.*;

import java.time.Instant;

public final class SaleTimer extends Thread{
    private final Sale sale;
    public SaleTimer(Sale sale) {
        this.sale = sale;
    }
    public void run(){
        try {
            sleep(this.sale.getDuration()*(long)60000);
        }catch (Exception ignored){}
        Trade trade = Server.closeSale(this.sale, Instant.now());
        if(trade!=null){
            Auction.sendMsg(trade.getBid().getBuyer().getContact(), "\r"+Server.alertPurchaseProceeded(trade));
            Auction.sendMsg(trade.getBid().getBuyer().getContact(), trade.getBid().getBuyer().getWelcomeMsg());
            Auction.sendMsg(trade.getSale().getSeller().getContact(), "\r"+Server.alertSaleProceeded(trade));
        } else Auction.sendMsg(this.sale.getSeller().getContact(), "\r"+Server.alertSaleNotProceeded(this.sale));
        Auction.sendMsg(this.sale.getSeller().getContact(), this.sale.getSeller().getWelcomeMsg());
    }
}

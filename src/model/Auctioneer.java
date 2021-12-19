package model;

import java.time.*;

public abstract class Auctioneer {
    private static double ratioSeller;
    private static double ratioBuyer;
    private static Instant i0;
    private static String pinCode;
    private static PiggyBank piggyBank;
    private static boolean logged;


    public static void init() {
        Auctioneer.setRatioSeller(0.10);
        Auctioneer.setRatioBuyer(0.15);
        Auctioneer.setI0(Instant.now());
        Auctioneer.setPinCode("0000");
        Auctioneer.setPiggyBank(Server.newPiggyBank(0));
        Auctioneer.setLogged(false);
    }

    public static boolean checkPinCode(String pinCode){
        return pinCode.equals(Auctioneer.getPinCode());
    }

    public static Instant getI0() {
        return i0;
    }

    public static void setI0(Instant i0) {
        Auctioneer.i0 = i0;
    }

    public static double taxSeller(int nbDigits){
        return Server.balanceFormat(Auctioneer.ratioSeller/Math.exp(0.1*(nbDigits-1)));
    }

    public static double taxBuyer(int nbDigits){
        return Server.balanceFormat(Auctioneer.ratioBuyer/Math.exp(0.1*(nbDigits-1)));
    }

    public static void connectAuctioneer(){
        Auctioneer.setLogged(true);
    }

    public static void disconnectAuctioneer(){
        Auctioneer.setLogged(false);
    }

    public static String getPinCode() {
        return pinCode;
    }

    public static void setPinCode(String pinCode) {
        Auctioneer.pinCode = pinCode;
    }

    public static boolean isLogged() {
        return logged;
    }

    public static void setLogged(boolean logged) {
        Auctioneer.logged = logged;
    }

    public static PiggyBank getPiggyBank() {
        return Auctioneer.piggyBank;
    }

    public static void setPiggyBank(PiggyBank piggyBank) {
        Auctioneer.piggyBank = piggyBank;
    }

    public static double getRatioSeller() {
        return ratioSeller;
    }

    public static void setRatioSeller(double ratioSeller) {
        Auctioneer.ratioSeller = ratioSeller;
    }

    public static double getRatioBuyer() {
        return ratioBuyer;
    }

    public static void setRatioBuyer(double ratioBuyer) {
        Auctioneer.ratioBuyer = ratioBuyer;
    }
}

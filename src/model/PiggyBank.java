package model;

public class PiggyBank {

    private int id;
    private double balance;
    private double potentialB;

    public PiggyBank(int id, double balance) {
        this.id = id;
        this.balance = Server.balanceFormat(balance);
        this.potentialB = 0;
    }

    public double getPotentialB() {
        return potentialB;
    }

    public void setPotentialB(double potentialB) {
        this.potentialB = Server.balanceFormat(potentialB);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = Server.balanceFormat(balance);
    }
}

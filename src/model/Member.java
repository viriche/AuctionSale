package model;

import java.io.*;
import java.time.*;

public class Member {

    private int id;
    private String welcomeMsg;
    private PrintWriter contact;
    private String name;
    private PiggyBank piggyBank;
    private int tag;
    private boolean logged;
    private Instant timeIn;
    private Instant timeOut;

    public Member(int id, String name, PiggyBank piggyBank, int tag, PrintWriter contact, Instant timeIn) {
        this.id = id;
        this.name = name;
        this.piggyBank = piggyBank;
        this.tag = tag;
        this.welcomeMsg = name+"("+id+")>";
        this.contact = contact;
        this.logged = true;
        this.timeIn = timeIn;
        this.timeOut = null;
        if(this.getTag() == Server.TAG_BUYER) this.getPiggyBank().setPotentialB(this.getPiggyBank().getBalance());
    }

    public Instant getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Instant timeOut) {
        this.timeOut = timeOut;
    }

    public Instant getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Instant timeIn) {
        this.timeIn = timeIn;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PiggyBank getPiggyBank() {
        return piggyBank;
    }

    public void setPiggyBank(PiggyBank piggyBank) {
        this.piggyBank = piggyBank;
    }

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public void setWelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }

    public PrintWriter getContact() {
        return contact;
    }

    public void setContact(PrintWriter contact) {
        this.contact = contact;
    }
}

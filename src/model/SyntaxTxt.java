package model;

import java.io.*;
import java.util.*;

public abstract class SyntaxTxt {
    public static final String PATH =System.getProperty("java.class.path")+"\\";
    public static final int CODE_L=1;
    public static final int CODE_B=2;
    public static final int CODE_S=3;
    public static final int CODE_A=4;

    public static String commands(int code){
        File myObj;
        switch (code){
            case CODE_L -> myObj = new File(PATH +"model\\commands\\1CommandLogin.txt");
            case CODE_B -> myObj = new File(PATH +"model\\commands\\1CommandBuyer.txt");
            case CODE_S -> myObj = new File(PATH +"model\\commands\\1CommandSeller.txt");
            case CODE_A -> myObj = new File(PATH +"model\\commands\\1CommandAdmin.txt");
            default -> throw new IllegalStateException("Unexpected value: " + code);
        }
        try {
            Scanner myReader = new Scanner(myObj);
            StringBuilder data= new StringBuilder();
            while (myReader.hasNextLine()) data.append(myReader.nextLine()).append("\n");
            myReader.close();
            return data.toString();
        } catch (Exception e) {
            return txtCommands(code);
        }
    }


    public static String help(int code){
        File myObj;
        switch (code) {
            case CODE_L -> myObj = new File(PATH + "..\\..\\..\\Commands\\1h1_HelpLogin.txt");
            case CODE_B -> myObj = new File(PATH + "..\\..\\..\\Commands\\1h2_HelpBuyer.txt");
            case CODE_S -> myObj = new File(PATH + "..\\..\\..\\Commands\\1h3_HelpSeller.txt");
            case CODE_A -> myObj = new File(PATH + "..\\..\\..\\Commands\\1h4_HelpAdmin.txt");
            default -> throw new IllegalStateException("Unexpected value: " + code);
        }
        try {
            Scanner myReader = new Scanner(myObj);
            StringBuilder data = new StringBuilder();
            while (myReader.hasNextLine()) data.append(myReader.nextLine()).append("\n");
            myReader.close();
            return data.toString();
        } catch (Exception e) {
            return txtHelp(code);
        }
    }


    private static String txtCommands(int code) {
        String data;
        switch (code) {
            case CODE_L -> data = txtCommandL();
            case CODE_B -> data = txtCommandB();
            case CODE_S -> data = txtCommandS();
            case CODE_A -> data = txtCommandA();
            default -> throw new IllegalStateException("Unexpected value: " + code);
        }
        return data;
    }

    private static String txtHelp(int code) {
        String data;
        switch (code) {
            case CODE_L -> data = txtHelpL();
            case CODE_B -> data = txtHelpB();
            case CODE_S -> data = txtHelpS();
            case CODE_A -> data = txtHelpA();
            default -> throw new IllegalStateException("Unexpected value: " + code);
        }
        return data;
    }

    private static String txtCommandA() {
        return "HELP\n" +
                "HELP\n" +
                "Show all available commands for Login\n" +
                "HELPc\n" +
                "HELPc command\n" +
                "Show use and useCase of command\n" +
                "CLEAR\n" +
                "CLEAR\n" +
                "Clear the terminal\n" +
                "AUCTION\n" +
                "AUCTION\n" +
                "Show auction information\n" +
                "RATIOb\n" +
                "RATIOb pinCode##ratioB\n" +
                "Set the ratio tax for purchases to ratioB(floating number between 0 and 1 (both exclusive))\n" +
                "RATIOs\n" +
                "RATIOs pinCode##ratioS\n" +
                "Set the ratio tax for sellings to ratioS(floating number between 0 and 1 (both exclusive))\n" +
                "PIN\n" +
                "PIN old##new\n" +
                "Set the pin code from old to new (4 digits natural number)\n" +
                "BID\n" +
                "BID idBid\n" +
                "Show bid with id idBid\n" +
                "BIDSs\n" +
                "BIDSs idSale\n" +
                "Show all bids on sale with id idSale\n" +
                "BIDSs p\n" +
                "BIDSs idSale##nbr##[P or T]##[R or N]\n" +
                "Show all bids on sale with id idSale sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDS**\n" +
                "BIDS**\n" +
                "Show all bids\n" +
                "BIDS** p\n" +
                "BIDS** nbr##[P or T]##[R or N]\n" +
                "Show all bids sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDS*r\n" +
                "BIDS*r\n" +
                "Show all bids on running sales\n" +
                "BIDS*r p\n" +
                "BIDS*r nbr##[P or T]##[R or N]\n" +
                "Show all bids on running sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDS*c\n" +
                "BIDS*c\n" +
                "Show all bids on closed sales\n" +
                "BIDS*c p\n" +
                "BIDS*c nbr##[P or T]##[R or N]\n" +
                "Show all bids on closed sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDS*w\n" +
                "BIDS*w\n" +
                "Show all the bids that won their sale\n" +
                "BIDS*w p\n" +
                "BIDS*w nbr##[P or T]##[R or N]\n" +
                "Show nbr of the bids that won their sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDS*l\n" +
                "BIDS*l\n" +
                "Show all the bids that lost their sale\n" +
                "BIDS*l p\n" +
                "BIDS*l nbr##[P or T]##[R or N]\n" +
                "Show nbr of the bids that lost their sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSm*\n" +
                "BIDSm* idMember\n" +
                "Show all bids relative to member with id idMember\n" +
                "BIDSm* p\n" +
                "BIDSm* idMember##nbr##[P or T]##[R or N]\n" +
                "Show all bids relative to member with id idMember sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSmr\n" +
                "BIDSmr idMember\n" +
                "Show all bids on running sales relative to member with id idMember\n" +
                "BIDSmr p\n" +
                "BIDSmr idMember##nbr##[P or T]##[R or N]\n" +
                "Show all bids on running relative to member with id idMember sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSmc\n" +
                "BIDSmc idMember\n" +
                "Show all bids on closed sales relative to member with id idMember\n" +
                "BIDSmc p\n" +
                "BIDSmc idMember##nbr##[P or T]##[R or N]\n" +
                "Show all bids on closed relative to member with id idMember sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSmw\n" +
                "BIDSmw idMember\n" +
                "Show all the bids that won their sales relative to member with id idMember\n" +
                "BIDSmw p\n" +
                "BIDSmw idMember##nbr##[P or T]##[R or N]\n" +
                "Show nbr of the bids that won their sales relative to member with id idMember sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSml\n" +
                "BIDSml idMember\n" +
                "Show all the bids that lost their sales relative to member with id idMember\n" +
                "BIDSml p\n" +
                "BIDSml idMember##nbr##[P or T]##[R or N]\n" +
                "Show nbr of the bids that lost their sales relative to member with id idMember sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "SAL\n" +
                "SAL idSale\n" +
                "Show sale with id idSale\n" +
                "SALS**\n" +
                "SALS**\n" +
                "Show all sales\n" +
                "SALS** p\n" +
                "SALS** nbr##[P, BB, D, T or RT]##[R or N]\n" +
                "Show all sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALS*r\n" +
                "SALS*r\n" +
                "Show all running sales\n" +
                "SALS*r p\n" +
                "SALS*r nbr##[P, BB, D, T or RT]##[R or N]\n" +
                "Show all running sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALS*c\n" +
                "SALS*c\n" +
                "Show all closed sales\n" +
                "SALS*c p\n" +
                "SALS*c nbr##[P, BB, D, T or RT]##[R or N]\n" +
                "Show all closed sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALS*w\n" +
                "SALS*w\n" +
                "Show all purchased sales\n" +
                "SALS*w p\n" +
                "SALS*w nbr##[P, BB, D, T or RT]##[R or N]\n" +
                "Show all purchased sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALS*l\n" +
                "SALS*l\n" +
                "Show all not purchased sales\n" +
                "SALS*l p\n" +
                "SALS*l nbr##[P, BB, D, T or RT]##[R or N]\n" +
                "Show all not purchased sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSma\n" +
                "SALSma idBuyer\n" +
                "Show all sales buyer with id idBuyer can bid on\n" +
                "SALSma p\n" +
                "SALSma idBuyer##nbr##[P, BB, D, T or RT]##[R or N]\n" +
                "Show all sales buyer with id idBuyer can bid on sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSm*\n" +
                "SALSm* idSeller\n" +
                "Show all sales of seller with id idSeller\n" +
                "SALSm* p\n" +
                "SALSm* idSeller##nbr##[P, BB, D, T or RT]##[R or N]\n" +
                "Show all sales of seller with id idSeller sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSmr\n" +
                "SALSmr idSeller\n" +
                "Show all running sales of seller with id idSeller\n" +
                "SALSmr p\n" +
                "SALSmr idSeller##nbr##[P, BB, D, T or RT]##[R or N]\n" +
                "Show all running sales of seller with id idSeller sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSmc\n" +
                "SALSmc idSeller\n" +
                "Show all closed sales of seller with id idSeller\n" +
                "SALSmc p\n" +
                "SALSmc idSeller##nbr##[P, BB, D, T or RT]##[R or N]\n" +
                "Show all closed sales of seller with id idSeller sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSmw\n" +
                "SALSmw idSeller\n" +
                "Show all purchased sales of seller with id idSeller\n" +
                "SALSmw p\n" +
                "SALSmw idSeller##nbr##[P, BB, D, T or RT]##[R or N]\n" +
                "Show all purchased sales of seller with id idSeller sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSml\n" +
                "SALSml idSeller\n" +
                "Show all not purchased sales of seller with id idSeller\n" +
                "SALSml p\n" +
                "SALSml idSeller##nbr##[P, BB, D, T or RT]##[R or N]\n" +
                "Show all not purchased sales of seller with id idSeller sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "TRAD\n" +
                "TRAD idSale \n" +
                "Show trade with id idTrade\n" +
                "TRADS*\n" +
                "TRADS*\n" +
                "Show all trades\n" +
                "TRADS* p\n" +
                "TRADS* nbr##[P or T]##[R or N]\n" +
                "Show all trades sorted by final price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "TRADSm\n" +
                "TRADSm idMember\n" +
                "Show all trades relative to member with id idMember\n" +
                "TRADSm p\n" +
                "TRADSm idMember##nbr##[P or T]##[R or N]\n" +
                "Show all trades relative to member with id idMember sorted by final price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "MEM\n" +
                "MEM idMember\n" +
                "Show member with id idMember\n" +
                "MEMS**\n" +
                "MEMS**\n" +
                "Show all members\n" +
                "MEMS** p\n" +
                "MEMS** nbr##[B, N, PB, or T]##[R or N]\n" +
                "Show all members sorted by name(N), balance(B), useful balance(PB) or time logged out(T) in reverse(R) or natural(N) order\n" +
                "MEMSb*\n" +
                "MEMSb*\n" +
                "Show all buyers\n" +
                "MEMSb* p\n" +
                "MEMSb* nbr##[B, N, PB, or T]##[R or N]\n" +
                "Show all buyers sorted by name(N), balance(B), useful balance(PB) or time logged out(T) in reverse(R) or natural(N) order\n" +
                "MEMSs*\n" +
                "MEMSs*\n" +
                "Show all sellers\n" +
                "MEMSs* p\n" +
                "MEMSs* nbr##[B, N, PB, or T]##[R or N]\n" +
                "Show all sellers sorted by name(N), balance(B), useful balance(PB) or time logged out(T) in reverse(R) or natural(N) order\n" +
                "MEMS*i\n" +
                "MEMS*i\n" +
                "Show all members logged in\n" +
                "MEMS*i p\n" +
                "MEMS*i nbr##[B, N, PB, or T]##[R or N]\n" +
                "Show all members logged in sorted by name(N), balance(B), useful balance(PB) or time logged out(T) in reverse(R) or natural(N) order\n" +
                "MEMS*o\n" +
                "MEMS*o\n" +
                "Show all members logged out\n" +
                "MEMS*o p\n" +
                "MEMS*o nbr##[B, N, PB, or T]##[R or N]\n" +
                "Show all members logged out sorted by name(N), balance(B), useful balance(PB) or time logged out(T) in reverse(R) or natural(N) order\n" +
                "MEMSbi\n" +
                "MEMSbi\n" +
                "Show all buyers logged in\n" +
                "MEMSbi p\n" +
                "MEMSbi nbr##[B, N, PB, or T]##[R or N]\n" +
                "Show all buyers logged in sorted by name(N), balance(B), useful balance(PB) or time logged out(T) in reverse(R) or natural(N) order\n" +
                "MEMSbo\n" +
                "MEMSbo\n" +
                "Show all buyers logged out\n" +
                "MEMSbo p\n" +
                "MEMSbo nbr##[B, N, PB, or T]##[R or N]\n" +
                "Show all buyers logged out sorted by name(N), balance(B), useful balance(PB) or time logged out(T) in reverse(R) or natural(N) order\n" +
                "MEMSsi\n" +
                "MEMSsi\n" +
                "Show all sellers logged in\n" +
                "MEMSsi p\n" +
                "MEMSsi nbr##[B, N, PB, or T]##[R or N]\n" +
                "Show all sellers logged in sorted by name(N), balance(B), useful balance(PB) or time logged out(T) in reverse(R) or natural(N) order\n" +
                "MEMSso\n" +
                "MEMSso\n" +
                "Show all sellers logged out\n" +
                "MEMSso p\n" +
                "MEMSso nbr##[B, N, PB, or T]##[R or N]\n" +
                "Show all sellers logged out sorted by name(N), balance(B), useful balance(PB) or time logged out(T) in reverse(R) or natural(N) order\n";
    }

    private static String txtCommandS() {
        return "HELP\n" +
                "HELP\n" +
                "Show all available commands for a Buyer\n" +
                "HELPc\n" +
                "HELPc command\n" +
                "Show use and useCase of command\n" +
                "CLEAR\n" +
                "CLEAR\n" +
                "Clear the terminal\n" +
                "SELF\n" +
                "SELF\n" +
                "Show self information\n" +
                "ADDs\n" +
                "ADDs desc##price##time\n" +
                "Start sale of desc with askedPrice price for duration time(number of minutes)\n" +
                "BID\n" +
                "BID idBid\n" +
                "Show bid with id idBid\n" +
                "BIDSs\n" +
                "BIDSs idSale\n" +
                "Show all bids on sale with id idSale (if yours)\n" +
                "BIDSs p\n" +
                "BIDSs idSale##nbr##[P or T]##[R or N]\n" +
                "Show all bids on sale with id idSale sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDS*\n" +
                "BIDS*\n" +
                "Show all bids on your sales\n" +
                "BIDS* p\n" +
                "BIDS* nbr##[P or T]##[R or N]\n" +
                "Show all bids on your sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSr\n" +
                "BIDSr\n" +
                "Show all bids on your running sales\n" +
                "BIDSr p\n" +
                "BIDSr nbr##[P or T]##[R or N]\n" +
                "Show all bids on your running sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSc\n" +
                "BIDSc\n" +
                "Show all bids on your closed sales\n" +
                "BIDSc p\n" +
                "BIDSc nbr##[P or T]##[R or N]\n" +
                "Show all bids on your closed sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSw\n" +
                "BIDSw\n" +
                "Show all the bids that won your sales\n" +
                "BIDSw p\n" +
                "BIDSw nbr##[P or T]##[R or N]\n" +
                "Show nbr of the bids that won your sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSl\n" +
                "BIDSl\n" +
                "Show all the bids that lost your sales\n" +
                "BIDSl p\n" +
                "BIDSl nbr##[P or T]##[R or N]\n" +
                "Show nbr of the bids that lost your sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "SAL\n" +
                "SAL idSale\n" +
                "Show sale with id idSale\n" +
                "SALS*\n" +
                "SALS*\n" +
                "Show all your sales\n" +
                "SALS* p\n" +
                "SALS* nbr##[P, BB, D, T or RT]##[R or N]\n" +
                "Show all your sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSr\n" +
                "SALSr\n" +
                "Show all your running sales\n" +
                "SALSr p\n" +
                "SALSr nbr##[P, BB, D, T or RT]##[R or N]\n" +
                "Show all your running sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSc\n" +
                "SALSc\n" +
                "Show all your closed sales\n" +
                "SALSc p\n" +
                "SALSc nbr##[P, BB, D, T or RT]##[R or N]\n" +
                "Show all your closed sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSw\n" +
                "SALSw\n" +
                "Show all your purchased sales\n" +
                "SALSw p\n" +
                "SALSw nbr##[P, BB, D, T or RT]##[R or N]\n" +
                "Show all your purchased sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSl\n" +
                "SALSl\n" +
                "Show all your not purchased sales\n" +
                "SALSl p\n" +
                "SALSl nbr##[P, BB, D, T or RT]##[R or N]\n" +
                "Show all your not purchased sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "TRAD\n" +
                "TRAD idTrade\n" +
                "Show trade with id idTrade\n" +
                "TRADS\n" +
                "TRADS\n" +
                "Show all your trades\n" +
                "TRADS p\n" +
                "TRADS nbr##[P or T]##[R or N]\n" +
                "Show nbr of your trades sorted by price(P) or time(T) in reverse(R) or natural(N) order\n";
    }

    private static String txtCommandB() {
        return "HELP\n" +
                "HELP\n" +
                "Show all available commands for a Buyer\n" +
                "HELPc\n" +
                "HELPc command\n" +
                "Show use and useCase of command\n" +
                "CLEAR\n" +
                "CLEAR\n" +
                "Clear the terminal\n" +
                "SELF\n" +
                "SELF\n" +
                "Show self information\n" +
                "ADDb\n" +
                "ADDb idSale##price\n" +
                "Put bid of amount price on sale with id idSale\n" +
                "BID\n" +
                "BID idBid\n" +
                "Show bid with id idBid\n" +
                "BIDSs\n" +
                "BIDSs idSale\n" +
                "Show all bids on sale with id idSale (if you are rich enough to bid on it)\n" +
                "BIDSs p\n" +
                "BIDSs idSale##nbr##[P or T]##[R or N]\n" +
                "Show nbr of the bids on sale with id idSale sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDS*\n" +
                "BIDS*\n" +
                "Show all your bids\n" +
                "BIDS* p\n" +
                "BIDS* nbr##[P or T]##[R or N]\n" +
                "Show nbr of your bids sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSr\n" +
                "BIDSr\n" +
                "Show all your bids on running sales\n" +
                "BIDSr p\n" +
                "BIDSr nbr##[P or T]##[R or N]\n" +
                "Show nbr of your bids on running sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSc\n" +
                "BIDSc\n" +
                "Show all your bids on closed sales\n" +
                "BIDSc p\n" +
                "BIDSc nbr##[P or T]##[R or N]\n" +
                "Show nbr of your bids on closed sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSw\n" +
                "BIDSw\n" +
                "Show all your bids that won their sale\n" +
                "BIDSw p\n" +
                "BIDSw nbr##[P or T]##[R or N]\n" +
                "Show nbr of your bids that won their sale sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSl\n" +
                "BIDSl\n" +
                "Show all your bids that lost their sale\n" +
                "BIDSl p\n" +
                "BIDSl nbr##[P or T]##[R or N]\n" +
                "Show nbr of your bids that lost their sale sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "SAL\n" +
                "SAL idSale\n" +
                "Show sale with id idSale\n" +
                "SALSr\n" +
                "SALSr\n" +
                "Show all sales\n" +
                "SALSr p\n" +
                "SALSr nbr##[P, BB, D, T or RT]##[R or N]\n" +
                "Show all running sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSa\n" +
                "SALSa\n" +
                "Show all sales you can bid on\n" +
                "SALSa p\n" +
                "SALSa nbr##[P, BB, D, T or RT]##[R or N]\n" +
                "Show all sales you can bid on sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "TRAD\n" +
                "TRAD idTrade\n" +
                "Show trade with id idTrade\n" +
                "TRADS\n" +
                "TRADS\n" +
                "Show all your trades\n" +
                "TRADS p\n" +
                "TRADS nbr##[P or T]##[R or N]\n" +
                "Show nbr of your trades sorted by price(P) or time(T) in reverse(R) or natural(N) order\n";
    }

    private static String txtCommandL() {
        return "HELP\n" +
                "HELP\n" +
                "Show all available commands for Login\n" +
                "HELPc\n" +
                "HELPc command\n" +
                "Show use and useCase of command\n" +
                "CLEAR\n" +
                "CLEAR\n" +
                "Clear the terminal\n" +
                "BUYER>\n" +
                "BUYER>name balance\n" +
                "Connect as a buyer called name(first character has to be a letter) and owning balance(has to be a floating positive number)\n" +
                "SELLER>\n" +
                "SELLER>name\n" +
                "Connect as a seller called name(first character has to be a letter)\n" +
                "AUCTIONEER>\n" +
                "AUCTIONEER>pinCode\n" +
                "Connect as the auctioneer with the pinCode(a 4 digits natural number)\n";
    }

    private static String txtHelpA() {
        return "#Commands available for the Auctioneer\n" +
                "\n" +
                "HELP -->Show all available commands for Login\n" +
                "HELPc command -->Show use and useCase of command\n" +
                "CLEAR -->Clear the terminal\n" +
                "AUCTION -->Show auction information\n" +
                "\n" +
                "RATIOb pinCode##ratioB -->Set the ratio tax for purchases to ratioB(floating number between 0 and 1 (both exclusive))\n" +
                "RATIOs pinCode##ratioS -->Set the ratio tax for sellings to ratioS(floating number between 0 and 1 (both exclusive))\n" +
                "PIN old##new -->Set the pin code from old to new (4 digits natural number)\n" +
                "\n" +
                "BID idBid -->Show bid with id idBid\n" +
                "BIDSs idSale -->Show all bids on sale with id idSale\n" +
                "BIDSs idSale##nbr##[P or T]##[R or N] -->Show all bids on sale with id idSale sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "\n" +
                "BIDS** -->Show all bids\n" +
                "BIDS** nbr##[P or T]##[R or N] -->Show all bids sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDS*r -->Show all bids on running sales\n" +
                "BIDS*r nbr##[P or T]##[R or N] -->Show all bids on running sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDS*c -->Show all bids on closed sales\n" +
                "BIDS*c nbr##[P or T]##[R or N] -->Show all bids on closed sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDS*w -->Show all the bids that won their sale\n" +
                "BIDS*w nbr##[P or T]##[R or N] -->Show nbr of the bids that won their sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDS*l -->Show all the bids that lost their sale\n" +
                "BIDS*l nbr##[P or T]##[R or N] -->Show nbr of the bids that lost their sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "\n" +
                "BIDSm* idMember -->Show all bids relative to member with id idMember\n" +
                "BIDSm* idMember##nbr##[P or T]##[R or N] -->Show all bids relative to member with id idMember sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSmr idMember -->Show all bids on running sales relative to member with id idMember\n" +
                "BIDSmr idMember##nbr##[P or T]##[R or N] -->Show all bids on running relative to member with id idMember sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSmc idMember -->Show all bids on closed sales relative to member with id idMember\n" +
                "BIDSmc idMember##nbr##[P or T]##[R or N] -->Show all bids on closed relative to member with id idMember sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSmw idMember -->Show all the bids that won their sales relative to member with id idMember\n" +
                "BIDSmw idMember##nbr##[P or T]##[R or N] -->Show nbr of the bids that won their sales relative to member with id idMember sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSml idMember -->Show all the bids that lost their sales relative to member with id idMember\n" +
                "BIDSml idMember##nbr##[P or T]##[R or N] -->Show nbr of the bids that lost their sales relative to member with id idMember sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "\n" +
                "SAL idSale -->Show sale with id idSale\n" +
                "SALS** -->Show all sales\n" +
                "SALS** nbr##[P, BB, D, T or RT]##[R or N] -->Show all sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALS*r -->Show all running sales\n" +
                "SALS*r nbr##[P, BB, D, T or RT]##[R or N] -->Show all running sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALS*c -->Show all closed sales\n" +
                "SALS*c nbr##[P, BB, D, T or RT]##[R or N] -->Show all closed sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALS*w -->Show all purchased sales\n" +
                "SALS*w nbr##[P, BB, D, T or RT]##[R or N] -->Show all purchased sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALS*l -->Show all not purchased sales\n" +
                "SALS*l nbr##[P, BB, D, T or RT]##[R or N] -->Show all not purchased sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "\n" +
                "SALSma idBuyer -->Show all sales buyer with id idBuyer can bid on\n" +
                "SALSma idBuyer##nbr##[P, BB, D, T or RT]##[R or N] -->Show all sales buyer with id idBuyer can bid on sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSm* idSeller -->Show all sales of seller with id idSeller\n" +
                "SALSm* idSeller##nbr##[P, BB, D, T or RT]##[R or N] -->Show all sales of seller with id idSeller sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSmr idSeller -->Show all running sales of seller with id idSeller\n" +
                "SALSmr idSeller##nbr##[P, BB, D, T or RT]##[R or N] -->Show all running sales of seller with id idSeller sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSmc idSeller -->Show all closed sales of seller with id idSeller\n" +
                "SALSmc idSeller##nbr##[P, BB, D, T or RT]##[R or N] -->Show all closed sales of seller with id idSeller sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSmw idSeller -->Show all purchased sales of seller with id idSeller\n" +
                "SALSmw idSeller##nbr##[P, BB, D, T or RT]##[R or N] -->Show all purchased sales of seller with id idSeller sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSml idSeller -->Show all not purchased sales of seller with id idSeller\n" +
                "SALSml idSeller##nbr##[P, BB, D, T or RT]##[R or N] -->Show all not purchased sales of seller with id idSeller sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "\n" +
                "TRAD idSale  -->Show trade with id idTrade\n" +
                "TRADS* -->Show all trades\n" +
                "TRADS* nbr##[P or T]##[R or N] -->Show all trades sorted by final price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "TRADSm idMember -->Show all trades relative to member with id idMember\n" +
                "TRADSm idMember##nbr##[P or T]##[R or N] -->Show all trades relative to member with id idMember sorted by final price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "\n" +
                "MEM idMember -->Show member with id idMember\n" +
                "MEMS** -->Show all members\n" +
                "MEMS** nbr##[B, N, PB, or T]##[R or N] -->Show all members sorted by name(N), balance(B), useful balance(PB) or time logged out(T) in reverse(R) or natural(N) order\n" +
                "MEMSb* -->Show all buyers\n" +
                "MEMSb* nbr##[B, N, PB, or T]##[R or N] -->Show all buyers sorted by name(N), balance(B), useful balance(PB) or time logged out(T) in reverse(R) or natural(N) order\n" +
                "MEMSs* -->Show all sellers\n" +
                "MEMSs* nbr##[B, N, PB, or T]##[R or N] -->Show all sellers sorted by name(N), balance(B), useful balance(PB) or time logged out(T) in reverse(R) or natural(N) order\n" +
                "MEMS*i -->Show all members logged in\n" +
                "MEMS*i nbr##[B, N, PB, or T]##[R or N] -->Show all members logged in sorted by name(N), balance(B), useful balance(PB) or time logged out(T) in reverse(R) or natural(N) order\n" +
                "MEMS*o -->Show all members logged out\n" +
                "MEMS*o nbr##[B, N, PB, or T]##[R or N] -->Show all members logged out sorted by name(N), balance(B), useful balance(PB) or time logged out(T) in reverse(R) or natural(N) order\n" +
                "MEMSbi -->Show all buyers logged in\n" +
                "MEMSbi nbr##[B, N, PB, or T]##[R or N] -->Show all buyers logged in sorted by name(N), balance(B), useful balance(PB) or time logged out(T) in reverse(R) or natural(N) order\n" +
                "MEMSbo -->Show all buyers logged out\n" +
                "MEMSbo nbr##[B, N, PB, or T]##[R or N] -->Show all buyers logged out sorted by name(N), balance(B), useful balance(PB) or time logged out(T) in reverse(R) or natural(N) order\n" +
                "MEMSsi -->Show all sellers logged in\n" +
                "MEMSsi nbr##[B, N, PB, or T]##[R or N] -->Show all sellers logged in sorted by name(N), balance(B), useful balance(PB) or time logged out(T) in reverse(R) or natural(N) order\n" +
                "MEMSso -->Show all sellers logged out\n" +
                "MEMSso nbr##[B, N, PB, or T]##[R or N] -->Show all sellers logged out sorted by name(N), balance(B), useful balance(PB) or time logged out(T) in reverse(R) or natural(N) order\n";
    }

    private static String txtHelpS() {
        return "#Commands available for a Seller\n" +
                "\n" +
                "HELP -->Show all available commands for a Buyer\n" +
                "HELPc command -->Show use and useCase of command\n" +
                "CLEAR -->Clear the terminal\n" +
                "SELF -->Show self information\n" +
                "\n" +
                "ADDs desc##price##time -->Start sale of desc with askedPrice price for duration time(number of minutes)\n" +
                "\n" +
                "BID idBid -->Show bid with id idBid\n" +
                "BIDSs idSale -->Show all bids on sale with id idSale (if yours)\n" +
                "BIDSs idSale##nbr##[P or T]##[R or N] -->Show all bids on sale with id idSale sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "\n" +
                "BIDS* -->Show all bids on your sales\n" +
                "BIDS* nbr##[P or T]##[R or N] -->Show all bids on your sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSr -->Show all bids on your running sales\n" +
                "BIDSr nbr##[P or T]##[R or N] -->Show all bids on your running sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSc -->Show all bids on your closed sales\n" +
                "BIDSc nbr##[P or T]##[R or N] -->Show all bids on your closed sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSw -->Show all the bids that won your sales\n" +
                "BIDSw nbr##[P or T]##[R or N] -->Show nbr of the bids that won your sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSl -->Show all the bids that lost your sales\n" +
                "BIDSl nbr##[P or T]##[R or N] -->Show nbr of the bids that lost your sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "\n" +
                "SAL idSale -->Show sale with id idSale\n" +
                "SALS* -->Show all your sales\n" +
                "SALS* nbr##[P, BB, D, T or RT]##[R or N] -->Show all your sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSr -->Show all your running sales\n" +
                "SALSr nbr##[P, BB, D, T or RT]##[R or N] -->Show all your running sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSc -->Show all your closed sales\n" +
                "SALSc nbr##[P, BB, D, T or RT]##[R or N] -->Show all your closed sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSw -->Show all your purchased sales\n" +
                "SALSw nbr##[P, BB, D, T or RT]##[R or N] -->Show all your purchased sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSl -->Show all your not purchased sales\n" +
                "SALSl nbr##[P, BB, D, T or RT]##[R or N] -->Show all your not purchased sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "\n" +
                "TRAD idTrade -->Show trade with id idTrade\n" +
                "TRADS -->Show all your trades\n" +
                "TRADS nbr##[P or T]##[R or N] -->Show nbr of your trades sorted by price(P) or time(T) in reverse(R) or natural(N) order\n";
    }

    private static String txtHelpB() {
        return "#Commands available for a Buyer\n" +
                "\n" +
                "HELP -->Show all available commands for a Buyer\n" +
                "HELPc command -->Show use and useCase of command\n" +
                "CLEAR -->Clear the terminal\n" +
                "SELF -->Show self information\n" +
                "\n" +
                "ADDb idSale##price -->Put bid of amount price on sale with id idSale\n" +
                "\n" +
                "BID idBid -->Show bid with id idBid\n" +
                "BIDSs idSale -->Show all bids on sale with id idSale (if you are rich enough to bid on it)\n" +
                "BIDSs idSale##nbr##[P or T]##[R or N] -->Show nbr of the bids on sale with id idSale sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "\n" +
                "BIDS* -->Show all your bids\n" +
                "BIDS* nbr##[P or T]##[R or N] -->Show nbr of your bids sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSr -->Show all your bids on running sales\n" +
                "BIDSr nbr##[P or T]##[R or N] -->Show nbr of your bids on running sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSc -->Show all your bids on closed sales\n" +
                "BIDSc nbr##[P or T]##[R or N] -->Show nbr of your bids on closed sales sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSw -->Show all your bids that won their sale\n" +
                "BIDSw nbr##[P or T]##[R or N] -->Show nbr of your bids that won their sale sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "BIDSl -->Show all your bids that lost their sale\n" +
                "BIDSl nbr##[P or T]##[R or N] -->Show nbr of your bids that lost their sale sorted by price(P) or time(T) in reverse(R) or natural(N) order\n" +
                "\n" +
                "SAL idSale -->Show sale with id idSale\n" +
                "SALSr -->Show all sales\n" +
                "SALSr nbr##[P, BB, D, T or RT]##[R or N] -->Show all running sales sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "SALSa -->Show all sales you can bid on\n" +
                "SALSa nbr##[P, BB, D, T or RT]##[R or N] -->Show all sales you can bid on sorted by asked price(P), best bid (BB), duration(D) time closed(T) or time remaining(RT) in reverse(R) or natural(N) order\n" +
                "\n" +
                "TRAD idTrade -->Show trade with id idTrade\n" +
                "TRADS -->Show all your trades\n" +
                "TRADS nbr##[P or T]##[R or N] -->Show nbr of your trades sorted by price(P) or time(T) in reverse(R) or natural(N) order\n";
    }

    private static String txtHelpL() {
        return "#Commands available at login stage\n" +
                "\n" +
                "HELP -->Show all available commands for Login\n" +
                "HELPc command -->Show use and useCase of command\n" +
                "CLEAR -->Clear the terminal\n" +
                "\n" +
                "BUYER>name balance -->Connect as a buyer called name(first character has to be a letter) and owning balance(has to be a floating positive number)\n" +
                "SELLER>name -->Connect as a seller called name(first character has to be a letter)\n" +
                "AUCTIONEER>pinCode -->Connect as the auctioneer with the pinCode(a 4 digits natural number)\n";
    }
}

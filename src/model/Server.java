package model;

import model.comparators.*;

import java.io.*;
import java.net.*;
import java.time.*;
import java.util.*;
import java.time.temporal.*;

public abstract class Server {

    private static int cptBid = 1;
    private static int cptMember = 1;
    private static int cptPiggyBank = 1;
    private static int cptSale = 1;
    private static int cptTrade = 1;

    public static final String SERVER_ADDRESS = "127.0.0.1";
    public static final int SERVER_PORT = 5000;
    public static final String BALANCE_UNIT = "USD";
    public static final int TAG_BUYER = 100;
    public static final int TAG_SELLER = 101;
    public static final int SORT_BY_PRICE = 11;
    public static final int SORT_BY_TIME = 12;
    public static final int SORT_BY_BALANCE = 13;
    public static final int SORT_BY_POTENTIAL_B = 14;
    public static final int SORT_BY_BEST_BID = 15;
    public static final int SORT_BY_DURATION = 16;
    public static final int SORT_BY_REMAINING_T = 17;
    public static final int SORT_BY_NAME = 18;

    public static List<Bid> bidList = new ArrayList<>();
    public static List<Member> memberList = new ArrayList<>();
    public static List<Integer> buyerIdList = new ArrayList<>();
    public static List<Integer> sellerIdList = new ArrayList<>();
    public static List<PiggyBank> piggyBankList = new ArrayList<>();
    public static List<Sale> saleList = new ArrayList<>();
    public static List<Trade> tradeList = new ArrayList<>();

    public static Bid getBidById(int id){
        for(Bid b : Server.bidList) if(b.getId()==id) return b;
        return null;
    }
    public static List<Bid> getAllBid(){
        return new ArrayList<>(Server.bidList);
    }
    public static List<Bid> getAllRunBid(){
        List<Bid> list = new ArrayList<>();
        for (Bid b : Server.bidList) if(b.getSale().isRunning()) list.add(b);
        return list;
    }
    public static List<Bid> getAllWinBid(){
        List<Bid> list = new ArrayList<>();
        for (Bid b : Server.bidList) if(b.getBuyer()==b.getSale().getFinalBuyer()) list.add(b);
        return list;
    }
    public static List<Bid> getAllCloBid(){
        List<Bid> list = new ArrayList<>();
        for (Bid b : Server.bidList) if(!b.getSale().isRunning()) list.add(b);
        return list;
    }
    public static List<Bid> getAllLosBid(){
        List<Bid> list = new ArrayList<>();
        for (Bid b : Server.bidList) if(b.getBuyer()!=b.getSale().getFinalBuyer()) list.add(b);
        return list;
    }
    public static List<Bid> getAllBidBySale(Sale sale){
        List<Bid> list = new ArrayList<>();
        for (Bid b : Server.bidList) if(b.getSale()==sale) list.add(b);
        return list;
    }
    public static List<Bid> getAllBidByBuyer(Member buyer){
        List<Bid> list = new ArrayList<>();
        for (Bid b : Server.bidList) if(b.getBuyer()==buyer) list.add(b);
        return list;
    }
    public static List<Bid> getAllBidBySeller(Member seller){
        List<Bid> list = new ArrayList<>();
        for (Bid b : Server.bidList) if(b.getSale().getSeller()==seller) list.add(b);
        return list;
    }
    public static List<Bid> getAllRunBidByBuyer(Member buyer){
        List<Bid> list = new ArrayList<>();
        for (Bid b : Server.bidList) if(b.getBuyer()==buyer && b.getSale().isRunning()) list.add(b);
        return list;
    }
    public static List<Bid> getAllRunBidBySeller(Member seller){
        List<Bid> list = new ArrayList<>();
        for (Bid b : Server.bidList) if(b.getSale().getSeller()==seller && b.getSale().isRunning()) list.add(b);
        return list;
    }
    public static List<Bid> getAllCloBidByBuyer(Member buyer){
        List<Bid> list = new ArrayList<>();
        for (Bid b : Server.bidList) if(b.getBuyer()==buyer && !b.getSale().isRunning()) list.add(b);
        return list;
    }
    public static List<Bid> getAllCloBidBySeller(Member seller){
        List<Bid> list = new ArrayList<>();
        for (Bid b : Server.bidList) if(b.getSale().getSeller()==seller && !b.getSale().isRunning()) list.add(b);
        return list;
    }
    public static List<Bid> getAllWinBidByBuyer(Member buyer){
        List<Bid> list = new ArrayList<>();
        for (Bid b : Server.bidList) if(b.getBuyer()==buyer && b.getSale().getFinalBuyer()==buyer) list.add(b);
        return list;
    }
    public static List<Bid> getAllWinBidBySeller(Member seller){
        List<Bid> list = new ArrayList<>();
        for (Bid b : Server.bidList) if(b.getSale().getSeller()==seller && b.getSale().getFinalBuyer()==b.getBuyer()) list.add(b);
        return list;
    }
    public static List<Bid> getAllLosBidByBuyer(Member buyer){
        List<Bid> list = new ArrayList<>();
        for (Bid b : Server.bidList) if(b.getBuyer()==buyer && b.getSale().getFinalBuyer()!=buyer) list.add(b);
        return list;
    }
    public static List<Bid> getAllLosBidBySeller(Member seller){
        List<Bid> list = new ArrayList<>();
        for (Bid b : Server.bidList) if(b.getSale().getSeller()==seller && b.getSale().getFinalBuyer()!=b.getBuyer()) list.add(b);
        return list;
    }
    public static Member getMemberById(int id){
        for(Member m : Server.memberList) if(m.getId()==id) return m;
        return null;
    }
    public static Member getMemberByName(String name, int tag){
        for(Member m : Server.memberList) if(m.getName().equals(name) && m.getTag() ==  tag && m.isLogged()) return m;
        return null;
    }
    public static List<Member> getAllMember(){
        return new ArrayList<>(Server.memberList);
    }
    public static List<Member> getAllMemByTag(int tag){
        List<Member> list = new ArrayList<>();
        for (Member m : Server.memberList) if(m.getTag()==tag) list.add(m);
        return list;
    }
    public static List<Member> getAllMemByLog(boolean logged){
        List<Member> list = new ArrayList<>();
        for (Member m : Server.memberList) if(m.isLogged()==logged) list.add(m);
        return list;
    }
    public static List<Member> getAllMemByTagLog(int tag, boolean logged){
        List<Member> list = new ArrayList<>();
        for (Member m : Server.memberList) if(m.getTag()==tag && m.isLogged()==logged) list.add(m);
        return list;
    }
    public static PiggyBank getPiggyBankById(int id){
        for(PiggyBank p : Server.piggyBankList) if(p.getId()==id) return p;
        return null;
    }
    public static Sale getSaleById(int id){
        for(Sale s : Server.saleList) if(s.getId()==id) return s;
        return null;
    }
    public static List<Sale> getAllSale(){
        return new ArrayList<>(Server.saleList);
    }
    public static List<Sale> getAllRunSale(){
        List<Sale> list = new ArrayList<>();
        for(Sale s : Server.saleList) if(s.isRunning()) list.add(s);
        return list;
    }
    public static List<Sale> getAllCloSale(){
        List<Sale> list = new ArrayList<>();
        for(Sale s : Server.saleList) if(!s.isRunning()) list.add(s);
        return list;
    }
    public static List<Sale> getAllWinSale(){
        List<Sale> list = new ArrayList<>();
        for(Sale s : Server.saleList) if(s.getFinalBuyer()!=null) list.add(s);
        return list;
    }
    public static List<Sale> getAllLosSale(){
        List<Sale> list = new ArrayList<>();
        for(Sale s : Server.saleList) if(s.getFinalBuyer()==null) list.add(s);
        return list;
    }
    public static List<Sale> getAllSaleBySeller(Member seller){
        List<Sale> list = new ArrayList<>();
        for(Sale s : Server.saleList) if(s.getSeller()==seller) list.add(s);
        return list;
    }
    public static List<Sale> getAllRunSaleBySeller(Member seller) {
        List<Sale> list = new ArrayList<>();
        for(Sale s : Server.saleList) if(s.getSeller()==seller && s.isRunning()) list.add(s);
        return list;
    }
    public static List<Sale> getAllCloSaleBySeller(Member seller){
        List<Sale> list = new ArrayList<>();
        for(Sale s : Server.saleList) if(s.getSeller()==seller && !s.isRunning()) list.add(s);
        return list;
    }
    public static List<Sale> getAllWinSaleBySeller(Member seller){
        List<Sale> list = new ArrayList<>();
        for(Sale s : Server.saleList) if(s.getSeller()==seller && s.getFinalBuyer()!=null) list.add(s);
        return list;
    }
    public static List<Sale> getAllLosSaleBySeller(Member seller){
        List<Sale> list = new ArrayList<>();
        for(Sale s : Server.saleList) if(s.getSeller()==seller && s.getFinalBuyer()==null) list.add(s);
        return list;
    }
    public static List<Sale> getAllSaleForBuyer(Member buyer){
        List<Sale> list = new ArrayList<>();
        for(Sale s : Server.saleList) if(s.isRunning() && canBidOnSale(buyer, s)) list.add(s);
        return list;
    }
    public static Trade getTradeById(int id){
        for(Trade t : Server.tradeList) if(t.getId()==id) return t;
        return null;
    }
    public static Trade getTradeByBid(Bid bid){
        for(Trade t : Server.tradeList) if(t.getBid()==bid) return t;
        return null;
    }
    public static Trade getTradeBySale(Sale sale){
        for(Trade t : Server.tradeList) if(t.getSale()==sale) return t;
        return null;
    }
    public static List<Trade> getAllTrade(){
        return new ArrayList<>(Server.tradeList);
    }
    public static List<Trade> getAllTradeByBuyer(Member buyer){
        List<Trade> list = new ArrayList<>();
        for(Trade t : Server.tradeList) if(t.getBid().getBuyer()==buyer) list.add(t);
        return list;
    }
    public static List<Trade> getAllTradeBySeller(Member seller){
        List<Trade> list = new ArrayList<>();
        for(Trade t : Server.tradeList) if(t.getSale().getSeller()==seller) list.add(t);
        return list;
    }

    public static String alertAuctioneerLogSuccess(){
        return "Successfully logged in as the Auctioneer";
    }
    public static String alertAuctioneerWrongPinCode(){
        return "Wrong Auctioneer Pin Code";
    }
    public static String alertAuctioneerAlreadyLogged(){
        return "Auctioneer Already Logged in";
    }
    public static String alertEmptyList(){
        return "NULL";
    }
    public static String alertAccDenied() {
        return "Access Denied";
    }
    public static String alertDisconnectedAuctioneer(Socket s) {
        return "Connection Lost with Auctioneer at "+s.getInetAddress()+":"+s.getPort();
    }
    public static String alertNameInUse(String s){
        return "Name " + s + " already in use";
    }
    public static String alertNewBuyer(Member m){
        return "Successfully logged in as a Buyer with id: " + m.getId();
    }
    public static String alertDisconnectedBuyer(Member member, Socket s) {
        return "Connection Lost with Buyer "+member.getName()+"("+ member.getId()+")"+" at "+s.getInetAddress()+":"+s.getPort();
    }
    public static String alertNewSeller(Member m){
        return "Successfully logged in as a Seller with id: " + m.getId();
    }
    public static String alertDisconnectedSeller(Member member, Socket s) {
        return "Connection Lost with Seller "+member.getName()+"("+ member.getId()+")"+" at "+s.getInetAddress()+":"+s.getPort();
    }
    public static String alertNewBid(Bid b) {
        return "New Bid put with id: "+b.getId();
    }
    public static String alertNewSale(Sale s) {
        return "New Sale launched with id: "+s.getId();
    }
    public static String alertCantBidOnSale(Sale s) {
        return "Not rich enough to bid on sale with id: "+s.getId();
    }
    public static String alertCantPutBid(double p) {
        return "Not rich enough to bid price: "+p;
    }
    public static String alertBidNotValid(Sale s) {
        return "Can't put bid on sale with current price: "+s.currentPrice();
    }
    public static String alertWrongSyntaxToLogIn() {
        return "Wrong syntax to log in";
    }
    public static String alertPurchaseProceeded(Trade trade) {
        return "You purchased sale: " +trade.getSale().getId()+" Total cost: "+(trade.getFinalPrice()+trade.getPurchaseTax())+"/"+trade.getFinalPrice();
    }
    public static String alertSaleProceeded(Trade trade) {
        return "You sold sale: " +trade.getSale().getId()+" Total gain: "+(trade.getFinalPrice()-trade.getSaleTax())+"/"+trade.getFinalPrice();
    }
    public static String alertSaleNotProceeded(Sale sale) {
        return "Sale "+sale.getId()+" closed without buyer";
    }
    public static String alertSaleAlrClosed(Sale s) {
        return "Sale with id "+s.getId()+" already closed";
    }
    public static String alertSaleNotFound(int id) {
        return "Sale with id "+id+" doesn't exist";
    }
    public static String alertMemberNotFound(int id) {
        return "Member with id "+id+" doesn't exist";
    }
    public static String alertUnrSyntax() {
        return "Syntax not recognized";
    }
    public static String alertWrongUseOfSyntax(String syntax) {
        return "Wrong syntax. Use "+syntax;
    }
    public static String alertMemberInfo(Member m, Instant i){
        if(m.getTag()==TAG_BUYER) return alertBuyerInfo(m, i);
        return alertSellerInfo(m, i);
    }
    public static String alertBuyerInfo(Member m, Instant i) {
        String s="Logged in since "+Server.timeFormat(Server.elapsedTime(m.getTimeIn(), i));
        if(!m.isLogged()) s+="   Logged out since "+Server.timeFormat(Server.elapsedTime(m.getTimeOut(), i));
        return String.format("Buyer %s(%s)   balance %s %s   useful balance %s %s   %s",
                m.getName(), m.getId(), m.getPiggyBank().getBalance(), Server.BALANCE_UNIT, m.getPiggyBank().getPotentialB(), Server.BALANCE_UNIT, s);
    }
    public static String alertSellerInfo(Member m, Instant i) {
        String s="Logged in since "+Server.timeFormat(Server.elapsedTime(m.getTimeIn(), i));
        if(!m.isLogged()) s+="   Logged out since "+Server.timeFormat(Server.elapsedTime(m.getTimeOut(), i));
        return String.format("Seller %s(%s)   balance %s %s   Logged since %s",
                m.getName(), m.getId(), m.getPiggyBank().getBalance(), Server.BALANCE_UNIT, s);
    }
    public static String alertBidInfo(Bid bid, Instant i){
        String s = "";
        if (bid.getSale().getBestBid() == bid) s = "Best bid of sale";
        return String.format("Bid %s   on Sale %s   Bidder %s(%s)   Price %s %s   Time left when put %s   %s",
                bid.getId(), bid.getSale().getId(), bid.getBuyer().getName() , bid.getBuyer().getId(), bid.getPrice(), Server.BALANCE_UNIT,
                Server.timeFormat(Server.remainingTime(bid.getSale(), bid.getTi())), s);
    }
    public static String alertSaleInfo(Sale sale, Instant i){
        String bestBidInfo = "No bid";
        if (sale.getBestBid()!=null) bestBidInfo = String.format("Best bid (%s) %s %s", sale.getBestBid().getId(), sale.getBestBid().getPrice(), Server.BALANCE_UNIT);
        String state;
        if(sale.isRunning()) state="Time left "+Server.timeFormat(Server.remainingTime(sale, i));
        else state="Closed since "+Server.timeFormat(Server.elapsedTime(sale.to, i));
        return String.format("Sale %s   Desc %s   Seller %s(%s)   Asked price %s %s   %s   %s",
                sale.getId(), sale.getDesc(), sale.getSeller().getName(), sale.getSeller().getId(), sale.getAskPrice(), Server.BALANCE_UNIT, state, bestBidInfo);
    }
    public static String alertTradeInfo(Trade trade, Instant i){
        return String.format("Sale %s   final price %s %s   Seller %s(%s)   Seller tax %s %s   Buyer %s(%s)   Buyer tax %s %s   Occurred since %s",
                trade.getSale().getId(), trade.getFinalPrice(), Server.BALANCE_UNIT,
                trade.getSale().getSeller().getName(), trade.getSale().getSeller().getId(), trade.getSaleTax(), Server.BALANCE_UNIT,
                trade.getSale().getFinalBuyer().getName(), trade.getSale().getFinalBuyer().getId(), trade.getPurchaseTax(), Server.BALANCE_UNIT,
                Server.timeFormat(Server.elapsedTime(trade.getTi(), i)));
    }
    public static String alertAuctioneerInfo(Instant i) {
        String elapsedT = Server.timeFormat((Server.elapsedTime(Auctioneer.getI0(), i)));
        double ratioS = Auctioneer.getRatioSeller()*100;
        double ratioB = Auctioneer.getRatioBuyer()*100;
        double balance = Auctioneer.getPiggyBank().getBalance();
        return "Auction started since: "+elapsedT+"   initial ratios: "+ratioB+"% on purchase and "+ratioS+
                "% on sale   Current balance: "+balance+" "+Server.BALANCE_UNIT;
    }

    public static int elapsedTime(Instant i1, Instant i2){
        return (int)Duration.between(i1, i2).get(ChronoUnit.SECONDS);
    }
    public static int nbDigits(double d){
        int n=1;
        while (d/10>=1){
            d/=10;
            if(d>1) n++;
        }
        return n;
    }
    public static int sortField(String s) {
        int sf=0;
        switch (s){
            case "P" -> sf = SORT_BY_PRICE;
            case "T" -> sf = SORT_BY_TIME;
            case "B" -> sf = SORT_BY_BALANCE;
            case "PB" -> sf = SORT_BY_POTENTIAL_B;
            case "BB" -> sf = SORT_BY_BEST_BID;
            case "D" -> sf = SORT_BY_DURATION;
            case "RT" -> sf = SORT_BY_REMAINING_T;
            case "N" -> sf = SORT_BY_NAME;
        }
        return sf;
    }
    public static int remainingTime(Sale s, Instant i){
        return s.getDuration()*60-Server.elapsedTime(s.getTi(), i);
    }
    public static void disconnectMember(Member member, Instant i){
        member.setLogged(false);
        member.setTimeOut(i);
    }
    public static void applyBid(Bid bid){
        if(bid.getSale().getBestBid()!=null) Server.discardBid(bid.getSale().getBestBid());
        PiggyBank pb = bid.getBuyer().getPiggyBank();
        bid.getSale().setBestBid(bid);
        pb.setPotentialB(pb.getPotentialB()-bid.getPrice()-Server.purchaseTax(bid.getPrice()));
    }
    public static void discardBid(Bid bid){
        PiggyBank pb = bid.getBuyer().getPiggyBank();
        pb.setPotentialB(pb.getPotentialB()+bid.getPrice()+Server.purchaseTax(bid.getPrice()));
    }
    public static double purchaseTax(double d){
        return Server.balanceFormat(d*Auctioneer.taxBuyer(Server.nbDigits(d)));
    }
    public static double sellingTax(double d){
        return Server.balanceFormat(d*Auctioneer.taxSeller(Server.nbDigits(d)));
    }
    public static double balanceFormat(double b){
        return (double)((int)(b*100))/100;
    }
    public static boolean isDouble(String s){
        try {
            Double n = Double.parseDouble(s);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean isInteger(String s){
        try {
            int n = Integer.parseInt(s);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean isBalance(String s){
        return Server.isDouble(s) && Server.balanceFormat(Double.parseDouble(s))>0;
    }
    public static boolean isRatio(String s){
        return Server.isDouble(s) && Server.balanceFormat(Double.parseDouble(s))>0 && Server.balanceFormat(Double.parseDouble(s))<1;
    }
    public static boolean isPinCode(String s){
        return Server.isInteger(s) && s.length()==4;
    }
    public static boolean isId(String s){
        return isInteger(s) && Integer.parseInt(s)>0;
    }
    public static boolean isUserName(String s) {
        return ((s.charAt(0)-65>=0 && s.charAt(0)-65<26) || (s.charAt(0)-65>=0 && s.charAt(0)-97<26));
    }
    public static boolean isBid(Sale sale, double price){
        return price>sale.currentPrice();
    }
    public static boolean canBidOnSale(Member m, Sale s){
        double currentPrice=s.currentPrice();
        double potentialB=m.getPiggyBank().getPotentialB();
        if(s.getBestBid()!=null && s.getBestBid().getBuyer()==m) potentialB+=currentPrice+Server.purchaseTax(currentPrice);
        return potentialB>currentPrice+Server.purchaseTax(currentPrice);
    }
    public static boolean canPutBid(Member m, Sale s, double p){
        double currentPrice=s.currentPrice();
        double potentialB=m.getPiggyBank().getPotentialB();
        if(s.getBestBid()!=null && s.getBestBid().getBuyer()==m) potentialB+=currentPrice+Server.purchaseTax(currentPrice);
        return potentialB>currentPrice+Server.purchaseTax(p);
    }
    public static String clearSpace() {
        return "\n".repeat(50);
    }
    public static String timeFormat(int duration){
        return (int)(duration/60)+" min "+duration%60+" sec";
    }
    public static Trade closeSale(Sale s, Instant i) {
        Trade t = null;
        if(s.getBestBid() != null) t = Server.proceedTrade(s, i);
        s.setRunning(false);
        s.setTo(i);
        return t;
    }
    public static Trade proceedTrade(Sale s, Instant i){
        s.setFinalBuyer(s.getBestBid().getBuyer());
        double finalP=s.getBestBid().getPrice();
        double taxB=Server.purchaseTax(finalP);
        double taxS=Server.sellingTax(finalP);
        PiggyBank p1 = s.getFinalBuyer().getPiggyBank();
        p1.setBalance(p1.getBalance()-finalP-taxB);
        PiggyBank p2 = s.getSeller().getPiggyBank();
        p2.setBalance(p2.getBalance()+finalP-taxS);
        PiggyBank p0 = Auctioneer.getPiggyBank();
        p0.setBalance(p0.getBalance()+taxB+taxS);
        return Server.newTrade(s, s.getBestBid(), taxB, taxS, i);
    }
    public static Trade newTrade(Sale sale, Bid bid, double purchaseTax, double saleTax, Instant ti){
        Trade t = new Trade(Server.cptTrade++,sale, bid, purchaseTax, saleTax, ti);
        Server.tradeList.add(t);
        return t;
    }
    public static Member newMember(String name, double balance, int tag, PrintWriter contact, Instant ti){
        Member mbr = new Member(Server.cptMember++, name, Server.newPiggyBank(balance), tag, contact, ti);
        Server.memberList.add(mbr);
        return mbr;
    }
    public static PiggyBank newPiggyBank(double balance){
        PiggyBank pb = new PiggyBank(Server.cptPiggyBank++, balance);
        Server.piggyBankList.add(pb);
        return pb;
    }
    public static Bid newBid(Sale sale, Member member, double price, Instant ti){
        Bid b = new Bid(Server.cptBid++, sale, member, price, ti);
        Server.bidList.add(b);
        return b;
    }
    public static Sale newSale(Member seller, String desc, double askPrice, int duration, Instant start){
        Sale s = new Sale(Server.cptSale++, seller, desc, askPrice, duration, start);
        Server.saleList.add(s);
        return s;
    }

    public static List<Bid> filterBid(Member member, char tag){
        List<Bid> list=new ArrayList<>();
        if(member==null){
            switch (tag) {
                case '*' -> list = getAllBid();
                case 'r' -> list = getAllRunBid();
                case 'c' -> list = getAllCloBid();
                case 'w' -> list = getAllWinBid();
                case 'l' -> list = getAllLosBid();
            }
        }else if (member.getTag()==Server.TAG_BUYER){
            switch (tag) {
                case '*' -> list = getAllBidByBuyer(member);
                case 'r' -> list = getAllRunBidByBuyer(member);
                case 'c' -> list = getAllCloBidByBuyer(member);
                case 'w' -> list = getAllWinBidByBuyer(member);
                case 'l' -> list = getAllLosBidByBuyer(member);
            }
        }else {
            switch (tag) {
                case '*' -> list = getAllBidBySeller(member);
                case 'r' -> list = getAllRunBidBySeller(member);
                case 'c' -> list = getAllCloBidBySeller(member);
                case 'w' -> list = getAllWinBidBySeller(member);
                case 'l' -> list = getAllLosBidBySeller(member);
            }
        }
        return list;
    }
    public static List<Member> filterMember(int tagMember, char tag){
        List<Member> list=new ArrayList<>();
        if(tagMember==TAG_BUYER || tagMember==TAG_SELLER){
            switch (tag) {
                case '*' -> list = getAllMemByTag(tagMember);
                case 'i' -> list = getAllMemByTagLog(tagMember, true);
                case 'o' -> list = getAllMemByTagLog(tagMember, false);
            }
        }else {
            switch (tag) {
                case '*' -> list = getAllMember();
                case 'i' -> list = getAllMemByLog(true);
                case 'o' -> list = getAllMemByLog(false);
            }
        }
        return list;
    }
    public static List<Sale> filterSale(Member member, char tag){
        List<Sale> list=new ArrayList<>();
        if(member==null){
            switch (tag) {
                case '*' -> list = getAllSale();
                case 'r' -> list = getAllRunSale();
                case 'c' -> list = getAllCloSale();
                case 'w' -> list = getAllWinSale();
                case 'l' -> list = getAllLosSale();
            }
        }else if (member.getTag()==Server.TAG_BUYER){
            switch (tag) {
                case 'r' -> list = getAllRunSale();
                case 'a', '*' -> list = getAllSaleForBuyer(member);
            }
        }else {
            switch (tag) {
                case '*' -> list = getAllSaleBySeller(member);
                case 'r' -> list = getAllRunSaleBySeller(member);
                case 'c' -> list = getAllCloSaleBySeller(member);
                case 'w' -> list = getAllWinSaleBySeller(member);
                case 'l' -> list = getAllLosSaleBySeller(member);
            }
        }
        return list;
    }
    public static List<Trade> filterTrade(Member member){
        if (member==null) return getAllTrade();
        if (member.getTag()==TAG_BUYER) return getAllTradeByBuyer(member);
        return getAllTradeBySeller(member);
    }

    public static void sorterBid(List<Bid> list, int field, boolean reversed){
        switch (field) {
            case SORT_BY_PRICE -> list.sort(new ComparatorBidPrice());
            case SORT_BY_TIME -> list.sort(new ComparatorBidTime());
        }
        if(reversed) Collections.reverse(list);
    }
    public static void sorterMember(List<Member> list, int field, boolean reversed){
        switch (field) {
            case SORT_BY_BALANCE -> list.sort(new ComparatorMemberBalance());
            case SORT_BY_POTENTIAL_B -> list.sort(new ComparatorMemberPotentialB());
            case SORT_BY_NAME -> list.sort(new ComparatorMemberName());
            case SORT_BY_TIME -> list.sort(new ComparatorMemberTimeOut());
        }
        if(reversed) Collections.reverse(list);
    }
    public static void sorterSale(List<Sale> list, int field, boolean reversed, Instant i){
        switch (field) {
            case SORT_BY_PRICE -> list.sort(new ComparatorSaleAskPrice());
            case SORT_BY_TIME -> list.sort(new ComparatorSaleTimeClosed());
            case SORT_BY_BEST_BID -> list.sort(new ComparatorSaleBestBid());
            case SORT_BY_DURATION -> list.sort(new ComparatorSaleDuration());
            case SORT_BY_REMAINING_T -> list.sort(new ComparatorSaleTimeRemain(i));
        }
        if(reversed) Collections.reverse(list);
    }
    public static void sorterTrade(List<Trade> list, int field, boolean reversed){
        switch (field) {
            case SORT_BY_PRICE -> list.sort(new ComparatorTradeFinalPrice());
            case SORT_BY_TIME -> list.sort(new ComparatorTradeTime());
        }
        if(reversed) Collections.reverse(list);
    }

    public static String infoBid(List<Bid> list, int size, Instant i){
        if(size <= 0 || size>list.size())size=list.size();
        StringBuilder s = new StringBuilder();
        for(int i1=0; i1<size; i1++) s.append(i1 + 1).append(">").append(Server.alertBidInfo(list.get(i1), i)).append("\n");
        return s.toString();
    }
    public static String infoMember(List<Member> list, int size, Instant i){
        if(size <= 0 || size>list.size())size=list.size();
        StringBuilder s = new StringBuilder();
        for(int i1=0; i1<size; i1++) s.append(i1 + 1).append(">").append(Server.alertMemberInfo(list.get(i1), i)).append("\n");
        return s.toString();
    }
    public static String infoSale(List<Sale> list, int size, Instant i){
        if(size <= 0 || size>list.size())size=list.size();
        StringBuilder s = new StringBuilder();
        for(int i1=0; i1<size; i1++){
            s.append(i1 + 1).append(">").append(Server.alertSaleInfo(list.get(i1), i)).append("\n");
        }
        return s.toString();
    }
    public static String infoTrade(List<Trade> list, int size, Instant i){
        if(size <= 0 || size>list.size())size=list.size();
        StringBuilder s = new StringBuilder();
        for(int i1=0; i1<size; i1++){
            s.append(i1 + 1).append(">").append(Server.alertTradeInfo(list.get(i1), i)).append("\n");
        }
        return s.toString();
    }
}

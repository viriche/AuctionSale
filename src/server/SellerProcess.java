package server;

import model.*;

import java.io.*;
import java.net.*;
import java.time.*;
import java.util.List;

public final class SellerProcess extends Thread{
    private final Socket s;
    private final Member member;

    public SellerProcess(Socket s, Member member) {
        this.s = s;
        this.member = member;
    }

    public void run() {
        try {
            InputStreamReader in = new InputStreamReader(s.getInputStream());
            BufferedReader in_sc = new BufferedReader(in);
            OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream());
            PrintWriter out_sc = new PrintWriter(new BufferedWriter(out), true);

            while (true){
                out_sc.println(member.getWelcomeMsg());
                String msg = in_sc.readLine();
                if (msg.equals("HELP")) Auction.sendMsg(out_sc, Syntax.getHelpSeller());
                else if (msg.startsWith("HELPc")){
                    if(msg.length()>6 && msg.charAt(5) == ' ') Auction.sendMsg(out_sc, Syntax.commandS(msg.substring(6)));
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxS.get("HELPc")));
                }
                else if (msg.equals("CLEAR")) Auction.sendMsg(out_sc, Server.clearSpace());
                else if(msg.equals("SELF")) Auction.sendMsg(out_sc, Server.alertSellerInfo(member, Instant.now()));
                else if(msg.startsWith("ADDs")){
                    if(msg.length()>5 && msg.charAt(4) == ' ' && msg.substring(5).split("##").length == 3 &&
                            Server.isBalance(msg.substring(5).split("##")[1]) && Server.isId(msg.substring(5).split("##")[2]) &&
                            Integer.parseInt(msg.substring(5).split("##")[2])>0){
                        String[]t=msg.substring(5).split("##");
                        Sale s = Server.newSale(this.member, t[0], Server.balanceFormat(Double.parseDouble(t[1])), Integer.parseInt(t[2]),Instant.now());
                        SaleTimer st=new SaleTimer(s);
                        st.start();
                        Auction.sendMsg(out_sc, Server.alertNewSale(s));
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxS.get("ADDs")));
                }
                else if(msg.startsWith("BID") && msg.length()>4 && msg.charAt(3) == ' '){
                    if(Server.isId(msg.substring(4))){
                        Bid b=Server.getBidById(Integer.parseInt(msg.substring(4)));
                        if(b!=null){
                            if (b.getSale().getSeller()==this.member)Auction.sendMsg(out_sc, Server.alertBidInfo(b, Instant.now()));
                            else Auction.sendMsg(out_sc, Server.alertAccDenied());
                        }
                        else Auction.sendMsg(out_sc, Server.alertEmptyList());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxS.get("BID")));
                }
                else if(msg.startsWith("SAL") && msg.length()>4 && msg.charAt(3) == ' '){
                    if(Server.isId(msg.substring(4))){
                        Sale s=Server.getSaleById(Integer.parseInt(msg.substring(4)));
                        if(s!=null){
                            if (s.getSeller()==this.member)Auction.sendMsg(out_sc, Server.alertSaleInfo(s, Instant.now()));
                            else Auction.sendMsg(out_sc, Server.alertAccDenied());
                        }
                        else Auction.sendMsg(out_sc, Server.alertEmptyList());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxS.get("SAL")));
                }
                else if(msg.startsWith("TRAD") && msg.length()>5 && msg.charAt(4) == ' '){
                    if(Server.isId(msg.substring(5))){
                        Trade d=Server.getTradeById(Integer.parseInt(msg.substring(5)));
                        if(d!=null){
                            if (d.getSale().getSeller()==this.member)Auction.sendMsg(out_sc, Server.alertTradeInfo(d, Instant.now()));
                            else Auction.sendMsg(out_sc, Server.alertAccDenied());
                        }
                        else Auction.sendMsg(out_sc, Server.alertEmptyList());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxS.get("TRAD")));
                }
                else if(msg.startsWith("BIDSs") && msg.length()>6 && msg.substring(6).split("##").length==1){
                    if(msg.charAt(5)==' ' && Server.isId(msg.substring(6))){
                        Sale s=Server.getSaleById(Integer.parseInt(msg.substring(6)));
                        if(s!=null){
                            if (s.getSeller()==this.member) {
                                List<Bid> list = Server.getAllBidBySale(s);
                                if(!list.isEmpty()){
                                    Server.sorterBid(list, 0, false);
                                    Auction.sendMsg(out_sc, Server.infoBid(list, 0, Instant.now()));
                                }
                                else Auction.sendMsg(out_sc, Server.alertEmptyList());
                            }
                            else Auction.sendMsg(out_sc, Server.alertAccDenied());
                        }
                        else Auction.sendMsg(out_sc, Server.alertSaleNotFound(Integer.parseInt(msg.substring(6))));
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("BIDSs")));
                }
                else if(msg.startsWith("BIDSs")){
                    if(msg.length()>6 && msg.charAt(5) == ' ' && msg.substring(6).split("##").length == 4 &&
                            Server.isId(msg.substring(7).split("##")[0])){
                        String[] t=msg.substring(6).split("##");
                        int id=Integer.parseInt(t[0]);
                        Sale s=Server.getSaleById(id);
                        if(s!=null){
                            if (s.getSeller()==this.member) {
                                int size=0;
                                if(Server.isId(t[1])) size=Integer.parseInt(t[1]);
                                int field=Server.sortField(t[2]);
                                boolean reversed= t[3].equals("R") || t[3].equals("r");
                                List<Bid> list = Server.getAllBidBySale(s);
                                if(!list.isEmpty()){
                                    Server.sorterBid(list, field, reversed);
                                    Auction.sendMsg(out_sc, Server.infoBid(list, size, Instant.now()));
                                }
                                else Auction.sendMsg(out_sc, Server.alertEmptyList());
                            }
                            else Auction.sendMsg(out_sc, Server.alertAccDenied());
                        }
                        else Auction.sendMsg(out_sc, Server.alertSaleNotFound(id));
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("BIDSs p")));
                }
                else if(msg.startsWith("BIDS") && msg.length()==5){
                    char tag=msg.charAt(4);
                    if(tag == '*' || tag == 'r' || tag == 'c' || tag == 'w' || tag == 'l'){
                        List<Bid> list = Server.filterBid(this.member, tag);
                        if(!list.isEmpty()){
                            Server.sorterBid(list, 0, false);
                            Auction.sendMsg(out_sc, Server.infoBid(list, 0, Instant.now()));
                        }else Auction.sendMsg(out_sc, Server.alertEmptyList());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxS.get("BIDS*")));
                }
                else if(msg.startsWith("BIDS")){
                    if(msg.length()>6 && (msg.charAt(4) == '*' || msg.charAt(4) == 'r' || msg.charAt(4) == 'c' || msg.charAt(4) == 'w' ||
                            msg.charAt(4) == 'l') && msg.charAt(5) == ' ' && msg.substring(6).split("##").length == 3){
                        String[] t=msg.substring(6).split("##");
                        char tag=msg.charAt(4);
                        int size=0;
                        if(Server.isId(t[0])) size=Integer.parseInt(t[0]);
                        int field=Server.sortField(t[1]);
                        boolean reversed= t[2].equals("R") || t[2].equals("r");
                        List<Bid> list = Server.filterBid(this.member, tag);
                        if(!list.isEmpty()){
                            Server.sorterBid(list, field, reversed);
                            Auction.sendMsg(out_sc, Server.infoBid(list, size, Instant.now()));
                        }else Auction.sendMsg(out_sc, Server.alertEmptyList());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxS.get("BIDS* p")));
                }
                else if(msg.startsWith("SALS") && msg.length()==5){
                    char tag=msg.charAt(4);
                    if(tag == '*' || tag == 'r' || tag == 'c' || tag == 'w' || tag == 'l'){
                        List<Sale> list = Server.filterSale(this.member, tag);
                        if(!list.isEmpty()){
                            Server.sorterSale(list, 0, false, Instant.now());
                            Auction.sendMsg(out_sc, Server.infoSale(list, 0, Instant.now()));
                        }
                        else Auction.sendMsg(out_sc, Server.alertEmptyList());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxS.get("SALS*")));
                }
                else if(msg.startsWith("SALS")){
                    if(msg.length()>6 && (msg.charAt(4) == '*' || msg.charAt(4) == 'r' || msg.charAt(4) == 'c' || msg.charAt(4) == 'w' ||
                            msg.charAt(4) == 'l') && msg.charAt(5) == ' ' && msg.substring(6).split("##").length == 3){
                        String[] t=msg.substring(6).split("##");
                        char tag=msg.charAt(4);
                        int size=0;
                        if(Server.isId(t[0])) size=Integer.parseInt(t[0]);
                        int field=Server.sortField(t[1]);
                        boolean reversed= t[2].equals("R") || t[2].equals("r");
                        List<Sale> list = Server.filterSale(this.member, tag);
                        if(!list.isEmpty()){
                            Server.sorterSale(list, field, reversed, Instant.now());
                            Auction.sendMsg(out_sc, Server.infoSale(list, size, Instant.now()));
                        }
                        else Auction.sendMsg(out_sc, Server.alertEmptyList());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxS.get("SALS* p")));
                }
                else if(msg.startsWith("TRADS") && msg.length()==5){
                    List<Trade> list = Server.filterTrade(this.member);
                    if(!list.isEmpty()){
                        Server.sorterTrade(list, 0, false);
                        Auction.sendMsg(out_sc, Server.infoTrade(list, 0, Instant.now()));
                    }
                    else Auction.sendMsg(out_sc, Server.alertEmptyList());
                }
                else if(msg.startsWith("TRADS")){
                    if(msg.length()>6 && msg.charAt(5) == ' ' && msg.substring(6).split("##").length == 3){
                        String[] t=msg.substring(6).split("##");
                        int size=0;
                        if(Server.isId(t[0])) size=Integer.parseInt(t[0]);
                        int field=Server.sortField(t[1]);
                        boolean reversed= t[2].equals("R") || t[2].equals("r");
                        List<Trade> list = Server.filterTrade(this.member);
                        if(!list.isEmpty()){
                            Server.sorterTrade(list, field, reversed);
                            Auction.sendMsg(out_sc, Server.infoTrade(list, size, Instant.now()));
                        }else Auction.sendMsg(out_sc, Server.alertEmptyList());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxS.get("TRADS* p")));
                }
                else if(msg.length()>0) Auction.sendMsg(out_sc, Server.alertUnrSyntax());
            }
        }
        catch (Exception e){
            System.out.println(Server.alertDisconnectedSeller(this.member, this.s));
            Server.disconnectMember(this.member, Instant.now());
        }
    }
}

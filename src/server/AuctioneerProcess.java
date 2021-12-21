package server;

import model.*;

import java.io.*;
import java.net.*;
import java.time.*;
import java.util.List;

public final class AuctioneerProcess extends Thread{
    private final Socket s;

    public AuctioneerProcess(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            InputStreamReader in = new InputStreamReader(s.getInputStream());
            BufferedReader in_sc = new BufferedReader(in);
            OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream());
            PrintWriter out_sc = new PrintWriter(new BufferedWriter(out), true);

            while (true){
                out_sc.println("admin>");
                String msg = in_sc.readLine();
                if (msg.equals("HELP")) Auction.sendMsg(out_sc, Syntax.getHelpAdmin());
                else if (msg.startsWith("HELPc")){
                    if(msg.length()>6 && msg.charAt(5) == ' ') Auction.sendMsg(out_sc, Syntax.commandA(msg.substring(6)));
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("HELPc")));
                }
                else if (msg.equals("CLEAR")) Auction.sendMsg(out_sc, Server.clearSpace());
                else if(msg.equals("AUCTION")) Auction.sendMsg(out_sc, Server.alertAuctioneerInfo(Instant.now()));
                else if(msg.startsWith("RATIOb")){
                    if(msg.length()>7 && msg.charAt(6) == ' ' && msg.substring(7).split("##").length==2 &&
                            Server.isRatio(msg.substring(7).split("##")[1])){
                        String[] t=msg.substring(7).split("##");
                        if(Auctioneer.checkPinCode(t[0])) Auctioneer.setRatioBuyer(Double.parseDouble(t[1]));
                        else Auction.sendMsg(out_sc, Server.alertAuctioneerWrongPinCode());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("RATIOb")));
                }
                else if(msg.startsWith("RATIOs")){
                    if(msg.length()>7 && msg.charAt(6) == ' ' && msg.substring(7).split("##").length==2 &&
                            Server.isRatio(msg.substring(7).split("##")[1])){
                        String[] t=msg.substring(7).split("##");
                        if(Auctioneer.checkPinCode(t[0])) Auctioneer.setRatioSeller(Double.parseDouble(t[1]));
                        else Auction.sendMsg(out_sc, Server.alertAuctioneerWrongPinCode());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("RATIOs")));
                }
                else if(msg.startsWith("PIN")){
                    if(msg.length()>4 && msg.charAt(3) == ' ' && msg.substring(4).split("##").length==2 &&
                            Server.isPinCode(msg.substring(4).split("##")[1])){
                        String[] t=msg.substring(4).split("##");
                        if(Auctioneer.checkPinCode(t[0])) Auctioneer.setPinCode(t[1]);
                        else Auction.sendMsg(out_sc, Server.alertAuctioneerWrongPinCode());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("PIN")));
                }
                else if(msg.startsWith("BID") && msg.length()>4 && msg.charAt(3) == ' '){
                    if(Server.isId(msg.substring(4))){
                        Bid b=Server.getBidById(Integer.parseInt(msg.substring(4)));
                        if(b!=null) Auction.sendMsg(out_sc, Server.alertBidInfo(b, Instant.now()));
                        else Auction.sendMsg(out_sc, Server.alertEmptyList());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("BID")));
                }
                else if(msg.startsWith("SAL") && msg.length()>4 && msg.charAt(3) == ' '){
                    if(Server.isId(msg.substring(4))){
                        Sale s=Server.getSaleById(Integer.parseInt(msg.substring(4)));
                        if(s!=null) Auction.sendMsg(out_sc, Server.alertSaleInfo(s, Instant.now()));
                        else Auction.sendMsg(out_sc, Server.alertEmptyList());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("SAL")));
                }
                else if(msg.startsWith("MEM") && msg.length()>4 && msg.charAt(3) == ' '){
                    if(Server.isId(msg.substring(4))){
                        Member m=Server.getMemberById(Integer.parseInt(msg.substring(4)));
                        if(m!=null) Auction.sendMsg(out_sc, Server.alertMemberInfo(m, Instant.now()));
                        else Auction.sendMsg(out_sc, Server.alertEmptyList());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("MEM")));
                }
                else if(msg.startsWith("TRAD") && msg.length()>6 && msg.charAt(5) == ' '){
                    if(Server.isId(msg.substring(6))){
                        Trade d=Server.getTradeById(Integer.parseInt(msg.substring(6)));
                        if(d!=null) Auction.sendMsg(out_sc, Server.alertTradeInfo(d, Instant.now()));
                        else Auction.sendMsg(out_sc, Server.alertEmptyList());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("TRAD")));
                }
                else if(msg.startsWith("BIDSs") && msg.length()>6 && msg.substring(6).split("##").length==1){
                    if(msg.charAt(5)==' ' && Server.isId(msg.substring(6))){
                        Sale s=Server.getSaleById(Integer.parseInt(msg.substring(6)));
                        if(s!=null){
                            List<Bid> list = Server.getAllBidBySale(s);
                            if(!list.isEmpty()){
                                Server.sorterBid(list, 0, false);
                                Auction.sendMsg(out_sc, Server.infoBid(list, 0, Instant.now()));
                            }
                            else Auction.sendMsg(out_sc, Server.alertEmptyList());
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
                        else Auction.sendMsg(out_sc, Server.alertSaleNotFound(id));
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("BIDSs p")));
                }
                else if(msg.startsWith("BIDS*") && msg.length()==6){
                    char tag=msg.charAt(5);
                    if(tag == '*' || tag == 'r' || tag == 'c' || tag == 'w' || tag == 'l'){
                        List<Bid> list = Server.filterBid(null, tag);
                        if(!list.isEmpty()){
                            Server.sorterBid(list, 0, false);
                            Auction.sendMsg(out_sc, Server.infoBid(list, 0, Instant.now()));
                        }
                        else Auction.sendMsg(out_sc, Server.alertEmptyList());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("BIDS**")));
                }
                else if(msg.startsWith("BIDS*")){
                    if(msg.length()>7 && (msg.charAt(5) == '*' || msg.charAt(5) == 'r' || msg.charAt(5) == 'c' || msg.charAt(5) == 'w' ||
                            msg.charAt(5) == 'l') && msg.charAt(6) == ' ' && msg.substring(7).split("##").length == 3){
                        String[] t=msg.substring(7).split("##");
                        char tag=msg.charAt(5);
                        int size=0;
                        if(Server.isId(t[0])) size=Integer.parseInt(t[0]);
                        int field=Server.sortField(t[1]);
                        boolean reversed= t[2].equals("R") || t[2].equals("r");
                        List<Bid> list = Server.filterBid(null, tag);
                        if(!list.isEmpty()){
                            Server.sorterBid(list, field, reversed);
                            Auction.sendMsg(out_sc, Server.infoBid(list, size, Instant.now()));
                        }
                        else Auction.sendMsg(out_sc, Server.alertEmptyList());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("BIDS** p")));
                }
                else if(msg.startsWith("SALS*") && msg.length()==6){
                    char tag=msg.charAt(5);
                    if(tag == '*' || tag == 'r' || tag == 'c' || tag == 'w' || tag == 'l'){
                        List<Sale> list = Server.filterSale(null, tag);
                        if(!list.isEmpty()){
                            Server.sorterSale(list, 0, false, Instant.now());
                            Auction.sendMsg(out_sc, Server.infoSale(list, 0, Instant.now()));
                        }
                        else Auction.sendMsg(out_sc, Server.alertEmptyList());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("SALS**")));
                }
                else if(msg.startsWith("SALS*")){
                    if(msg.length()>7 && (msg.charAt(5) == '*' || msg.charAt(5) == 'r' || msg.charAt(5) == 'c' || msg.charAt(5) == 'w' ||
                            msg.charAt(5) == 'l') && msg.charAt(6) == ' ' && msg.substring(7).split("##").length == 3){
                        String[] t=msg.substring(7).split("##");
                        char tag=msg.charAt(5);
                        int size=0;
                        if(Server.isId(t[0])) size=Integer.parseInt(t[0]);
                        int field=Server.sortField(t[1]);
                        boolean reversed= t[2].equals("R") || t[2].equals("r");
                        List<Sale> list = Server.filterSale(null, tag);
                        if(!list.isEmpty()){
                            Server.sorterSale(list, field, reversed, Instant.now());
                            Auction.sendMsg(out_sc, Server.infoSale(list, size, Instant.now()));
                        }
                        else Auction.sendMsg(out_sc, Server.alertEmptyList());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("SALS** p")));
                }
                else if(msg.startsWith("TRADS*") && msg.length()==6){
                    List<Trade> list = Server.filterTrade(null);
                    if(!list.isEmpty()){
                        Server.sorterTrade(list, 0, false);
                        Auction.sendMsg(out_sc, Server.infoTrade(list, 0, Instant.now()));
                    }
                    else Auction.sendMsg(out_sc, Server.alertEmptyList());
                }
                else if(msg.startsWith("TRADS*")){
                    if(msg.length()>7 && msg.charAt(6) == ' ' && msg.substring(7).split("##").length == 3){
                        String[] t=msg.substring(7).split("##");
                        int size=0;
                        if(Server.isId(t[0])) size=Integer.parseInt(t[0]);
                        int field=Server.sortField(t[1]);
                        boolean reversed= t[2].equals("R") || t[2].equals("r");
                        List<Trade> list = Server.filterTrade(null);
                        if(!list.isEmpty()){
                            Server.sorterTrade(list, field, reversed);
                            Auction.sendMsg(out_sc, Server.infoTrade(list, size, Instant.now()));
                        }
                        else Auction.sendMsg(out_sc, Server.alertEmptyList());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("TRADS* p")));
                }
                else if(msg.startsWith("BIDSm") && msg.length()>7 && msg.substring(7).split("##").length==1){
                    char tag=msg.charAt(5);
                    if((tag == '*' || tag == 'r' || tag == 'c' || tag == 'w' || tag == 'l') && msg.charAt(6)==' ' && Server.isId(msg.substring(7))){
                        Member m = Server.getMemberById(Integer.parseInt(msg.substring(7)));
                        if(m!=null){
                            List<Bid> list = Server.filterBid(m, tag);
                            if(!list.isEmpty()){
                                Server.sorterBid(list, 0, false);
                                Auction.sendMsg(out_sc, Server.infoBid(list, 0, Instant.now()));
                            }
                            else Auction.sendMsg(out_sc, Server.alertEmptyList());
                        }
                        else Auction.sendMsg(out_sc, Server.alertMemberNotFound(Integer.parseInt(msg.substring(7))));
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("BIDSm*")));
                }
                else if(msg.startsWith("BIDSm")){
                    if(msg.length()>7 && (msg.charAt(5) == '*' || msg.charAt(5) == 'r' || msg.charAt(5) == 'c' || msg.charAt(5) == 'w' ||
                            msg.charAt(5) == 'l') && msg.charAt(6) == ' ' && msg.substring(7).split("##").length == 4 &&
                            Server.isId(msg.substring(7).split("##")[0])){
                        String[] t=msg.substring(7).split("##");
                        int id=Integer.parseInt(t[0]);
                        Member m = Server.getMemberById(id);
                        if(m!=null){
                            char tag=msg.charAt(5);
                            int size=0;
                            if(Server.isId(t[1])) size=Integer.parseInt(t[1]);
                            int field=Server.sortField(t[2]);
                            boolean reversed= t[3].equals("R") || t[3].equals("r");
                            List<Bid> list = Server.filterBid(m, tag);
                            if(!list.isEmpty()){
                                Server.sorterBid(list, field, reversed);
                                Auction.sendMsg(out_sc, Server.infoBid(list, size, Instant.now()));
                            }
                            else Auction.sendMsg(out_sc, Server.alertEmptyList());
                        }
                        else Auction.sendMsg(out_sc, Server.alertMemberNotFound(id));
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("BIDSm* p")));
                }
                else if(msg.startsWith("SALSm") && msg.length()>7 && msg.substring(7).split("##").length==1){
                    char tag=msg.charAt(5);
                    if(tag == '*' || tag == 'r' || tag == 'c' || tag == 'w' || tag == 'l' || tag == 'a'){
                        Member m = Server.getMemberById(Integer.parseInt(msg.substring(7)));
                        if(m!=null){
                            List<Sale> list = Server.filterSale(m, tag);
                            if(!list.isEmpty()){
                                Server.sorterSale(list, 0, false, Instant.now());
                                Auction.sendMsg(out_sc, Server.infoSale(list, 0, Instant.now()));
                            }else Auction.sendMsg(out_sc, Server.alertEmptyList());
                        }
                        else Auction.sendMsg(out_sc, Server.alertMemberNotFound(Integer.parseInt(msg.substring(7))));
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("SALSm*")));
                }
                else if(msg.startsWith("SALSm")){
                    if(msg.length()>7 && (msg.charAt(5) == '*' || msg.charAt(5) == 'r' || msg.charAt(5) == 'c' || msg.charAt(5) == 'w' ||
                            msg.charAt(5) == 'l' || msg.charAt(5) == 'a') && msg.charAt(6) == ' ' && msg.substring(7).split("##").length == 4 &&
                            Server.isId(msg.substring(7).split("##")[0])){
                        String[] t=msg.substring(7).split("##");
                        int id=Integer.parseInt(t[0]);
                        Member m = Server.getMemberById(id);
                        if(m!=null){
                            char tag=msg.charAt(5);
                            int size=0;
                            if(Server.isId(t[1])) size=Integer.parseInt(t[1]);
                            int field=Server.sortField(t[2]);
                            boolean reversed= t[3].equals("R") || t[3].equals("r");
                            List<Sale> list = Server.filterSale(m, tag);
                            if(!list.isEmpty()){
                                Server.sorterSale(list, field, reversed, Instant.now());
                                Auction.sendMsg(out_sc, Server.infoSale(list, size, Instant.now()));
                            }
                            else Auction.sendMsg(out_sc, Server.alertEmptyList());
                        }
                        else Auction.sendMsg(out_sc, Server.alertMemberNotFound(id));
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("SALSm* p")));
                }
                else if(msg.startsWith("TRADSm") && msg.length()>7 && msg.substring(7).split("##").length==1){
                    if(msg.charAt(6)==' ' && Server.isId(msg.substring(7))){
                        Member m = Server.getMemberById(Integer.parseInt(msg.substring(7)));
                        if(m!=null){
                            List<Trade> list = Server.filterTrade(m);
                            if(!list.isEmpty()){
                                Server.sorterTrade(list, 0, false);
                                Auction.sendMsg(out_sc, Server.infoTrade(list, 0, Instant.now()));
                            }
                            else Auction.sendMsg(out_sc, Server.alertEmptyList());
                        }
                        else Auction.sendMsg(out_sc, Server.alertMemberNotFound(Integer.parseInt(msg.substring(7))));
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("TRADSm")));
                }
                else if(msg.startsWith("TRADSm")){
                    if(msg.length()>7 && msg.charAt(6) == ' ' && msg.substring(7).split("##").length == 4 &&
                            Server.isId(msg.substring(7).split("##")[0])){
                        String[] t=msg.substring(7).split("##");
                        int id=Integer.parseInt(t[0]);
                        Member m = Server.getMemberById(id);
                        if(m!=null){
                            int size=0;
                            if(Server.isId(t[1])) size=Integer.parseInt(t[1]);
                            int field=Server.sortField(t[2]);
                            boolean reversed= t[3].equals("R") || t[3].equals("r");
                            List<Trade> list = Server.filterTrade(m);
                            if(!list.isEmpty()){
                                Server.sorterTrade(list, field, reversed);
                                Auction.sendMsg(out_sc, Server.infoTrade(list, size, Instant.now()));
                            }
                            else Auction.sendMsg(out_sc, Server.alertEmptyList());
                        }
                        else Auction.sendMsg(out_sc, Server.alertMemberNotFound(id));
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("TRADSm p")));
                }
                else if(msg.startsWith("MEMS") && msg.length()==6){
                    char tag1=msg.charAt(4);
                    char tag=msg.charAt(5);
                    if((tag1 == '*' || tag1 == 'b' || tag1 == 's') && (tag == '*' || tag == 'i' || tag == 'o')){
                        int tagMember=0;
                        switch (tag1){
                            case 'b' -> tagMember=Server.TAG_BUYER;
                            case 's' -> tagMember=Server.TAG_SELLER;
                        }
                        List<Member> list = Server.filterMember(tagMember, tag);
                        if(!list.isEmpty()){
                            Server.sorterMember(list, 0, false);
                            Auction.sendMsg(out_sc, Server.infoMember(list, 0, Instant.now()));
                        }
                        else Auction.sendMsg(out_sc, Server.alertEmptyList());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("MEMS**")));
                }
                else if(msg.startsWith("MEMS")){
                    if(msg.length()>7 && (msg.charAt(4) == '*' || msg.charAt(4) == 'b' || msg.charAt(4) == 's') &&
                            (msg.charAt(5) == '*' || msg.charAt(5) == 'i' || msg.charAt(5) == 'o') &&
                            msg.charAt(6) == ' ' && msg.substring(7).split("##").length == 3){
                        String[] t=msg.substring(7).split("##");
                        int tagMember=0;
                        switch (msg.charAt(4)){
                            case 'b' -> tagMember=Server.TAG_BUYER;
                            case 's' -> tagMember=Server.TAG_SELLER;
                        }
                        char tag=msg.charAt(5);
                        int size=0;
                        if(Server.isId(t[0])) size=Integer.parseInt(t[0]);
                        int field=Server.sortField(t[1]);
                        boolean reversed=t[2].equals("R") || t[2].equals("r");
                        List<Member> list = Server.filterMember(tagMember, tag);
                        if(!list.isEmpty()){
                            Server.sorterMember(list, field, reversed);
                            Auction.sendMsg(out_sc, Server.infoMember(list, size, Instant.now()));
                        }
                        else Auction.sendMsg(out_sc, Server.alertEmptyList());
                    }
                    else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxA.get("MEMS** p")));
                }
                else if(msg.length()>0) Auction.sendMsg(out_sc, Server.alertUnrSyntax());
            }
        }catch (Exception e){
            System.out.println(Server.alertDisconnectedAuctioneer(this.s));
            Auctioneer.disconnectAuctioneer();
        }
    }
}

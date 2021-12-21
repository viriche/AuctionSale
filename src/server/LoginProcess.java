package server;

import model.*;

import java.io.*;
import java.net.*;
import java.time.Instant;

public final class LoginProcess extends Thread{
    private final Socket s;

    public LoginProcess(Socket s) {
        this.s = s;
    }


    public void run() {
        try {
            InputStreamReader in = new InputStreamReader(s.getInputStream());
            BufferedReader in_sc = new BufferedReader(in);
            OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream());
            PrintWriter out_sc = new PrintWriter(new BufferedWriter(out), true);
            boolean logged=false;
            while(!logged) {
                out_sc.println(">");
                String msg = in_sc.readLine();
                 if (msg.equals("HELP")) Auction.sendMsg(out_sc, Syntax.getHelpLogin());
                 else if (msg.startsWith("HELPc")){
                     if(msg.length()>6 && msg.charAt(5) == ' ') Auction.sendMsg(out_sc, Syntax.commandL(msg.substring(6)));
                     else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxL.get("HELPc")));
                 }
                 else if (msg.equals("CLEAR")) Auction.sendMsg(out_sc, Server.clearSpace());
                 else if (msg.startsWith("BUYER")){
                     if(msg.length()>6 && msg.charAt(5) == '>' && msg.substring(6).split(" ").length == 2 &&
                             Server.isBalance(msg.substring(6).split(" ")[1])){
                         if (Server.getMemberByName(msg.substring(6).split(" ")[0], Server.TAG_BUYER) == null) {
                             Member mbr = Server.newMember(msg.substring(6).split(" ")[0],
                                     Double.parseDouble(msg.substring(6).split(" ")[1]), Server.TAG_BUYER, out_sc, Instant.now());
                             BuyerProcess t = new BuyerProcess(s, mbr);
                             Auction.sendMsg(out_sc, Server.alertNewBuyer(mbr));
                             logged = true;
                             t.start();
                         } else Auction.sendMsg(out_sc, Server.alertNameInUse(msg.substring(6).split(" ")[0]));
                     }
                     else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxL.get("BUYER>")));
                }
                 else if (msg.startsWith("SELLER")) {
                     if(msg.length()>7 && msg.charAt(6) == '>' && msg.substring(7).split(" ").length == 1 &&
                             Server.isUserName(msg.substring(7).split(" ")[0])){
                         if (Server.getMemberByName(msg.substring(7).split(" ")[0], Server.TAG_SELLER) == null) {
                             Member mbr = Server.newMember(msg.substring(7).split(" ")[0], 0, Server.TAG_SELLER, out_sc, Instant.now());
                             SellerProcess t = new SellerProcess(s, mbr);
                             Auction.sendMsg(out_sc, Server.alertNewSeller(mbr));
                             logged = true;
                             t.start();
                         }
                         else Auction.sendMsg(out_sc, Server.alertNameInUse(msg.substring(7).split(" ")[0]));
                     }
                     else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxL.get("SELLER>")));
                }
                 else if (msg.startsWith("AUCTIONEER")) {
                     if(msg.length()>11 && msg.charAt(10) == '>' && Server.isPinCode(msg.substring(11))){
                         if(!Auctioneer.isLogged()) {
                             if(Auctioneer.checkPinCode(msg.substring(11))){
                                 Auctioneer.connectAuctioneer();
                                 AuctioneerProcess t = new AuctioneerProcess(s);
                                 Auction.sendMsg(out_sc, Server.alertAuctioneerLogSuccess());
                                 logged = true;
                                 t.start();
                             }
                             else Auction.sendMsg(out_sc, Server.alertAuctioneerWrongPinCode());
                         }
                         else Auction.sendMsg(out_sc, Server.alertAuctioneerAlreadyLogged());
                     }
                     else Auction.sendMsg(out_sc, Server.alertWrongUseOfSyntax(Syntax.syntaxL.get("AUCTIONEER>")));
                }
                 else if(msg.length()>0) Auction.sendMsg(out_sc, Server.alertWrongSyntaxToLogIn());
            }
        }
        catch (Exception ignored) {}
    }
}

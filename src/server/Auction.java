package server;

import model.*;

import java.io.*;
import java.net.*;

public final class Auction {

    public static void sendMsg(PrintWriter out_sc, String s){
        out_sc.println(s);
    }

    public static void main(String[] args) {
        Auctioneer.init();
        try {
            Syntax.init();
            try {
                ServerSocket sc = new ServerSocket(Server.SERVER_PORT);
                while(true) {
                    Socket s = sc.accept();
                    LoginProcess l = new LoginProcess(s);
                    l.start();
                }
            }catch (BindException e){
                System.out.println("Server already running");
            }catch (Exception e) {
                System.out.println(e.getMessage());
                //e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Initialisation Failed");
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
    }
}

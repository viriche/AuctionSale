package client;

import model.*;

import java.net.*;

public final class UserTerminal {

    public static void main(String[] args){
        try {
            Socket s = new Socket(Server.SERVER_ADDRESS, Server.SERVER_PORT);

            UserSend us = new UserSend(s);
            us.start();
            UserReceive ur = new UserReceive(s);
            ur.start();
        }catch (Exception e){
            System.out.println("Server at "+Server.SERVER_ADDRESS +":"+Server.SERVER_PORT +" is Unreachable");
        }
    }
}

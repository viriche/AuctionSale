package client;

import java.io.*;
import java.net.*;

public final class UserReceive extends Thread{
    private final Socket s;

    public UserReceive(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            InputStreamReader in = new InputStreamReader(s.getInputStream());
            BufferedReader in_sc = new BufferedReader(in);

            while (true) {
                String msgRcv = in_sc.readLine();
                System.out.print(msgRcv);
                if(!msgRcv.endsWith(">")) System.out.println(" ");
            }
        }catch (Exception e){
            try { System.in.close();} catch (IOException ignored) {}
            System.out.println("\rServer at "+ s.getInetAddress().toString()+":"+s.getPort()+" has been Shutdown");
        }
    }
}

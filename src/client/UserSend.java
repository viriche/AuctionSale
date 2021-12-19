package client;

import java.io.*;
import java.net.*;

public final class UserSend extends Thread{
    private final Socket s;

    public UserSend(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream());
            PrintWriter out_sc = new PrintWriter(new BufferedWriter(out),true);
            BufferedReader in_cl = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String msgSend = in_cl.readLine();
                out_sc.println(msgSend);
            }
        }catch (Exception ignored){}
    }
}

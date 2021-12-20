package model;

import java.io.*;
import java.util.*;

public abstract class Syntax {
    public static List<String> keySetL = new ArrayList<>();
    public static List<String> keySetB = new ArrayList<>();
    public static List<String> keySetS = new ArrayList<>();
    public static List<String> keySetA = new ArrayList<>();
    public static HashMap<String, String> syntaxL=new HashMap<>();
    public static HashMap<String, String> syntaxB=new HashMap<>();
    public static HashMap<String, String> syntaxS=new HashMap<>();
    public static HashMap<String, String> syntaxA=new HashMap<>();
    public static HashMap<String, String> useCaseL=new HashMap<>();
    public static HashMap<String, String> useCaseB=new HashMap<>();
    public static HashMap<String, String> useCaseS=new HashMap<>();
    public static HashMap<String, String> useCaseA=new HashMap<>();

    public static void init() throws Exception {
        fillL();
        fillB();
        fillS();
        fillA();
        //fill();
    }

    public static void fillL() throws Exception {
        File myObj = new File("src\\model\\commands\\commandLogin.txt");
        Scanner myReader = new Scanner(myObj);
        String data, key;
        while (myReader.hasNextLine()) {
            data = myReader.nextLine();
            key = data;
            keySetL.add(key);
            data = myReader.nextLine();
            syntaxL.put(key, data);
            data = myReader.nextLine();
            useCaseL.put(key, data);
        }
        myReader.close();
    }
    public static void fillB() throws Exception {
        File myObj = new File("src\\model\\commands\\commandBuyer.txt");
        Scanner myReader = new Scanner(myObj);
        String data, key;
        while (myReader.hasNextLine()) {
            data = myReader.nextLine();
            key = data;
            keySetB.add(key);
            data = myReader.nextLine();
            syntaxB.put(key, data);
            data = myReader.nextLine();
            useCaseB.put(key, data);
        }
        myReader.close();
    }
    public static void fillS() throws Exception {
        File myObj = new File("src\\model\\commands\\commandSeller.txt");
        Scanner myReader = new Scanner(myObj);
        String data, key;
        while (myReader.hasNextLine()) {
            data = myReader.nextLine();
            key = data;
            keySetS.add(key);
            data = myReader.nextLine();
            syntaxS.put(key, data);
            data = myReader.nextLine();
            useCaseS.put(key, data);
        }
        myReader.close();
    }
    public static void fillA() throws Exception {
        File myObj = new File("src\\model\\commands\\commandAdmin.txt");
        Scanner myReader = new Scanner(myObj);
        String data, key;
        while (myReader.hasNextLine()) {
            data = myReader.nextLine();
            key = data;
            keySetA.add(key);
            data = myReader.nextLine();
            syntaxA.put(key, data);
            data = myReader.nextLine();
            useCaseA.put(key, data);
        }
        myReader.close();
    }

    public static String commandL(String c){
        String h="";
        h += Syntax.syntaxL.get(c)+" -->"+Syntax.useCaseL.get(c);
        if(h.equals("null -->null")) return "Command not found";
        return h;
    }
    public static String commandB(String c){
        String h="";
        h += Syntax.syntaxB.get(c)+" -->"+Syntax.useCaseB.get(c);
        if(h.equals("null -->null")) return "Command not found";
        return h;
    }
    public static String commandS(String c){
        String h="";
        h += Syntax.syntaxS.get(c)+" -->"+Syntax.useCaseS.get(c);
        if(h.equals("null -->null")) return "Command not found";
        return h;
    }
    public static String commandA(String c){
        String h="";
        h += Syntax.syntaxA.get(c)+" -->"+Syntax.useCaseA.get(c);
        if(h.equals("null -->null")) return "Command not found";
        return h;
    }

    public static String commandsL(){
        StringBuilder s= new StringBuilder();
        s.append("#Commands available at login stage\n");
        for(String k: keySetL){
            s.append(syntaxL.get(k)).append(" -->").append(useCaseL.get(k)).append("\n");
        }
        return s.toString();
    }
    public static String commandsB(){
        StringBuilder s= new StringBuilder();
        s.append("#Commands available for a Buyer\n");
        for(String k: keySetB){
            s.append(syntaxB.get(k)).append(" -->").append(useCaseB.get(k)).append("\n");
        }
        return s.toString();
    }
    public static String commandsS(){
        StringBuilder s= new StringBuilder();
        s.append("#Commands available for a Seller\n");
        for(String k: keySetS){
            s.append(syntaxS.get(k)).append(" -->").append(useCaseS.get(k)).append("\n");
        }
        return s.toString();
    }
    public static String commandsA(){
        StringBuilder s= new StringBuilder();
        s.append("#Commands available for the Auctioneer\n");
        for(String k: keySetA){
            s.append(syntaxA.get(k)).append(" -->").append(useCaseA.get(k)).append("\n");
        }
        return s.toString();
    }


    public static void fill() throws Exception {
        FileWriter myWriter;
        myWriter = new FileWriter("Commands\\f1_CommandsLogin.txt");
        myWriter.write(commandsL());
        myWriter.close();
        myWriter = new FileWriter("Commands\\f2_CommandsBuyer.txt");
        myWriter.write(commandsB());
        myWriter.close();
        myWriter = new FileWriter("Commands\\f3_CommandsSeller.txt");
        myWriter.write(commandsS());
        myWriter.close();
        myWriter = new FileWriter("Commands\\f4_CommandsAdmin.txt");
        myWriter.write(commandsA());
        myWriter.close();
    }
}

package model;

import java.util.*;

public abstract class Syntax {
    public static String helpLogin;
    public static String helpBuyer;
    public static String helpSeller;
    public static String helpAdmin;
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

    public static void init() throws IllegalStateException{
        fill(SyntaxTxt.CODE_L);
        fill(SyntaxTxt.CODE_B);
        fill(SyntaxTxt.CODE_S);
        fill(SyntaxTxt.CODE_A);
    }

    public static void fill(int code) throws IllegalStateException{
        List<String> ks;
        HashMap<String, String> st, uc;
        String[] t;
        switch (code) {
            case SyntaxTxt.CODE_L -> {
                helpLogin=SyntaxTxt.help(SyntaxTxt.CODE_L);
                t = SyntaxTxt.commands(SyntaxTxt.CODE_L).split("\n");
                ks = Syntax.keySetL;
                st = Syntax.syntaxL;
                uc = Syntax.useCaseL;
            }
            case SyntaxTxt.CODE_B -> {
                helpBuyer=SyntaxTxt.help(SyntaxTxt.CODE_B);
                t = SyntaxTxt.commands(SyntaxTxt.CODE_B).split("\n");
                ks = Syntax.keySetB;
                st = Syntax.syntaxB;
                uc = Syntax.useCaseB;
            }
            case SyntaxTxt.CODE_S -> {
                helpSeller=SyntaxTxt.help(SyntaxTxt.CODE_S);
                t = SyntaxTxt.commands(SyntaxTxt.CODE_S).split("\n");
                ks = Syntax.keySetS;
                st = Syntax.syntaxS;
                uc = Syntax.useCaseS;
            }
            case SyntaxTxt.CODE_A -> {
                helpAdmin=SyntaxTxt.help(SyntaxTxt.CODE_A);
                t = SyntaxTxt.commands(SyntaxTxt.CODE_A).split("\n");
                ks = Syntax.keySetA;
                st = Syntax.syntaxA;
                uc = Syntax.useCaseA;
            }
            default -> throw new IllegalStateException("Unexpected value: " + code);
        }
        for(int i=0; i<t.length; i+=3){
            ks.add(t[i]);
            st.put(t[i], t[i+1]);
            uc.put(t[i], t[i+2]);
        }
    }
    public static String commandL(String c){
        String h=Syntax.syntaxL.get(c)+" -->"+Syntax.useCaseL.get(c);
        if(h.equals("null -->null")) return "Command not found";
        return h;
    }
    public static String commandB(String c){
        String h=Syntax.syntaxB.get(c)+" -->"+Syntax.useCaseB.get(c);
        if(h.equals("null -->null")) return "Command not found";
        return h;
    }
    public static String commandS(String c){
        String h=Syntax.syntaxS.get(c)+" -->"+Syntax.useCaseS.get(c);
        if(h.equals("null -->null")) return "Command not found";
        return h;
    }
    public static String commandA(String c){
        String h=Syntax.syntaxA.get(c)+" -->"+Syntax.useCaseA.get(c);
        if(h.equals("null -->null")) return "Command not found";
        return h;
    }

    public static String getHelpLogin() {
        return helpLogin;
    }

    public static String getHelpBuyer() {
        return helpBuyer;
    }

    public static String getHelpSeller() {
        return helpSeller;
    }

    public static String getHelpAdmin() {
        return helpAdmin;
    }
}

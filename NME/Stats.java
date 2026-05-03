package NME;

import java.util.ArrayList;
public class Stats extends BaseTarget{
    private ArrayList<String> inventry;
    private int NRG;

    public Stats(String n, double h){
        super(n, h, 0.0);
        inventry = new ArrayList<String>();
        inventry.add("Dagger");
        NRG = 0;
    }

    public int getNRG(){return NRG;}
    public void setNRG(int a){NRG = a;}
    public void incrementNRG(){NRG = Math.min(25, NRG+1);}
    public void useNRG(int amount){ 
        Math.max(0, NRG-=amount);
        if(NRG > 25) NRG = 25;}
    public void initInventry(ArrayList<String> itms){
        inventry = itms;}
    public void addToInventry(String itm){
        inventry.add(itm);}
    
    public ArrayList<String> getInventry(){return inventry;}


}

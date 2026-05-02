package NME;

import java.util.ArrayList;
public class Stats extends BaseTarget{
    private ArrayList<String> inventry;

    public Stats(String n, double h){
        super(n, h, 0);
    }
    
    public void initInventry(ArrayList<String> itms){
        inventry = itms;}
    public void addToInventry(String itm){
        inventry.add(itm);}
    

    public void setOptions(){
        
    }

}

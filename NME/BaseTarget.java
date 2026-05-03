package NME;

import java.util.ArrayList;

import StatsObjects.Status;

public class BaseTarget {
    private double health, attkRng;
    private double hpMod=1, dmgMod=1, resistanceMod=1;
    private String name;
    private ArrayList<Status> statuses = new ArrayList<Status>();
    

    public BaseTarget(String n, double h, double at){
        health = h;
        attkRng = at;
        name = n;
    }

    public String getName(){return name;}
    public double getAttackRange(){return attkRng;}
    public void addStatus(StatusType name, int duration) {
        statuses.add(new Status(name, duration));
    }
    
    
    public void incrementStatuses() {
        for (int i = 0; i < statuses.size(); i++) {
            Status s = statuses.get(i);
            s.decrement();
            if (s.isExpired()) {
                statuses.remove(i);
                i--; }
        }
    }
    public void checkStatuses(){
        hpMod = 1; dmgMod = 1; resistanceMod = 1;
        for(Status s : statuses){
            switch(s.getType()){
                case INVIGORATION:
                    hpMod += 0.3; dmgMod += 0.3; break;
                case STRENGTH:
                    dmgMod += 0.6; break;
                case WEAKNESS:
                    dmgMod -= 0.5; resistanceMod += 0.6; hpMod += 1.0; break;
                case PLATED:
                    hpMod += 0.5; resistanceMod -= 0.3; break;
                case RESISTANT:
                    resistanceMod -= 0.7; break;
                case UBER:
                    hpMod += 2.0; resistanceMod -= 2.0; dmgMod -= 0.2; break;
                case KRITZ:
                    hpMod -= 0.5; resistanceMod += 0.5; dmgMod += 2.0; break;
            }
        }
        if(hpMod <= 0.0) hpMod = 0.1;
        if(dmgMod <= 0.0) dmgMod = 0.1;
        if(resistanceMod <= 0.0) resistanceMod = 0.1;
    }



    
    public void takeDmg(double dmg, BaseTarget target){
        if(target.equals(this))
            dmg *= hpMod;
        else if(dmg > 0)
            dmg *= dmgMod;
        dmg *= target.getResistanceMod();
        target.setHP(Math.max(0, target.getHP() - dmg));
    }
    public double getResistanceMod(){return resistanceMod;}
    public double getHP(){return health;}
    public void setHP(double hp){health = hp;}
}

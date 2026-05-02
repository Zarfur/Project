package NME;

public class BaseTarget {
    private double health, attkRng;
    private String name;

    public BaseTarget(String n, double h, double at){
        health = h;
        attkRng = at;
        name = n;
    }

    public String getName(){return name;}
    public double getAttackRange(){return attkRng;}
    
    public void takeDmg(double dmg){
        health = Math.max(0, health - dmg);
    }
    public double getHP(){return health;}
}

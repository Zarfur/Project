package NME;

public class BaseTarget {
    private double health, attkRng;
    private String name;

    public BaseTarget(String n, double h, double at){
        health = h;
        attkRng = at;
        name = n;
    }
    
    public void takeDmg(double dmg){
        health -= dmg;
    }
    public void dealDmg(BaseTarget target, double dmg){
        target.takeDmg(dmg);
    }
    public double getHP(){return health;}
}

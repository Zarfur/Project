package NME;

public class NmeBase extends BaseTarget {
    private double interval;
    public NmeBase(String n, double h, double at, double i){
        super(n, h, at);
        interval = i;
    }

    public void runInterval(){
        for(double index = 5 + (interval*0.5); index > -10; index--){
            System.out.println(index);
        }
    }

    
}

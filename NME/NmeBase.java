package NME;

import java.util.Scanner;
import java.util.Random;

public class NmeBase extends BaseTarget {
    private int interval;
    private double attkMin;
    private Stats player;
    private boolean heal, haste;
    private Random random = new Random();
    public NmeBase(String n, double h, double attkRange, int i, double attkMin, Stats player, boolean canHeal, boolean haste){
        super(n, h, attkRange);
        heal = canHeal;
        interval = i;
        this.attkMin = attkMin;
        this.player = player;
    }

    

    // run Combat

    public void nmeTurn(){
        decision();
        if(haste) decision();
    }

    public void decision(){
        player.incrementNRG();
        int bound = 1;
        if (heal) bound+=3;
        bound = random.nextInt(bound);
        switch(bound){
            case(2):
                if(heal){
                    int healAmount = random.nextInt((int) attkMin, (int) this.getAttackRange());
                    System.out.println(this.getName() + " healed " + healAmount + " health points!");
                    this.takeDmg(-healAmount);}
            break;
            default:
                runInterval();
            break;
    
        }
    }

    private volatile boolean endThread = false;
    private int index = 0, range = 10;
    public void runInterval(){
        System.out.println("Click any button to block! Try to block as close to ZERO as possible.");
        Scanner hitCheck = new Scanner(System.in);
        endThread = false;
        if(interval >= 0.7) range = 5;
        Thread dodgeWindow = new Thread(() -> {
            for(index = range; index > -range; index--){
                if(endThread) break;
                System.out.print(index + " ");
                try {
                    Thread.sleep(interval); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); break; }
            }
        });
    dodgeWindow.start();
    String input = hitCheck.next();
    endThread = true;
    handleDodges(index);

    }

    public void handleDodges(int input){
        double damage = random.nextInt((int) attkMin, (int) super.getAttackRange());
        if(index == 0){
            damage = Math.max(0, damage/3 - 20);
            int ouch = random.nextInt(0, 5);
            this.takeDmg(ouch);
            System.out.println("Counter!");
            System.out.println(this.getName() + " took " + ouch + " damage from the counter!");
        }else if(index <= 2 || index >= -2){
            System.out.println("Block!");
            damage = damage/3;
        System.out.printf(" -%.1f hp!%n", damage);
        player.takeDmg(damage);
    }
    
    }
}

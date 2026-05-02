package Managers;

import NME.NmeBase;
import NME.Stats;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class NmeManager {
    private File enemyBlock;
    private Random random;
    private ArrayList<String[]> data = new ArrayList<String[]>();
    private Scanner sc;
    private NmeBase nmePicked;
    private Stats player;
    private boolean isDummy = true;

    public NmeManager(Stats player) throws FileNotFoundException{
        this.player = player;
        random = new Random();
        enemyBlock = new File("Data/NME.txt");
        sc = new Scanner(enemyBlock);
        sc.nextLine();
        while(sc.hasNextLine()){
            // enemy name;health;attackRange;interval;attackMinimum;canHeal;hasHaste
            // String    ; int  ;   double  ; double ;   double    ;boolean;boolean
            data.add(sc.nextLine().split(";"));
        }
        sc.close();
    }
    public NmeBase pickNme(){
        String[] chosen = data.get(random.nextInt(data.size()));
        if(isDummy){ chosen = data.get(0); isDummy = false;}
        String name = chosen[0];
        double health = Double.parseDouble(chosen[1]), attackRange = Double.parseDouble(chosen[2]),
                        attackMin = Double.parseDouble(chosen[4]);
        int interval = Integer.parseInt(chosen[3]);
        boolean canHeal = Boolean.parseBoolean(chosen[5]), hasHaste = Boolean.parseBoolean(chosen[6]);
        nmePicked = new NmeBase(name, health, attackRange, interval, attackMin, player, canHeal, hasHaste);
        return nmePicked;
    }
}

import NME.Stats;

import java.util.Scanner;

import Managers.PlayerTurnManager;
public class Game {
    
    private boolean state;
    private Stats plr;
    private Scanner sc;
    private PlayerTurnManager playerTurnManager;

    public Game(String n, int i){
        // inits
        state = true;
        sc = new Scanner(System.in);
        plr = new Stats(n, i);
        try { playerTurnManager = new PlayerTurnManager(plr);
        } catch (Exception e) {
            System.out.println("File not found");}
        
        // gameplay loop
        while(plr.getHP() > 0){
            playerState();
            otherState();
        }
        playerTurnManager.closeScanner();
        sc.close();
    }

    public void playerState(){

        state = !state;
    }

    public void otherState(){


        state = !state;
    }
}

import NME.NmeBase;
import NME.Stats;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import Managers.NmeManager;
import Managers.PlayerTurnManager;
public class Game {
    
    private Stats player;
    private NmeBase enemy;
    private Scanner sc;
    private PlayerTurnManager playerTurnManager;
    private int turn, battle;
    private NmeManager enemyManager;
    private Random random = new Random();
    private ArrayList<String> itemData = new ArrayList<String>();

    public Game(String n, int i, Scanner sc) throws FileNotFoundException{
        // inits
        turn = 0;
        battle = 1;
        this.sc = sc;
        player = new Stats(n, i);
        enemyManager = new NmeManager(player);
        playerTurnManager = new PlayerTurnManager(player);
        Scanner file = new Scanner(new File("Data/ITM.txt"));
        while(file.hasNextLine())
            itemData.add(file.nextLine().split(";")[0]);
        file.close();
        
    }

    public void runGame() throws FileNotFoundException{      
        // gameplay loop
        while(player.getHP() > 0){
            turn = 0;
            player.setNRG(0);
            enemy = enemyManager.pickNme();
            while(enemy.getHP() > 0)
                combat();
            battle++;
            for(int i=0; i < random.nextInt(4);i++)
                getItem();
        }
        System.out.println("You lasted " + battle + " battles but were thwarted by " + enemy.getName() + "!");
        playerTurnManager.closeScanner();
        sc.close();
    }

    public void combat() throws FileNotFoundException{
        turn++;
        System.out.println("\n\nTurn " + turn + " of Battle " + battle + " Against " + enemy.getName() + "\n");
        playerState();
        otherState();
    }

    public void getItem() throws FileNotFoundException{
        player.addToInventry(itemData.get(random.nextInt(0, itemData.size())));
    }
    public void playerState() throws FileNotFoundException{
        int turns = 2;
        while(turns > 0){
            System.out.println("\nPlay your turn!\n 1) go to inventory | 2) use NRG skills");
            System.out.printf("Current HP: %.1f%n", player.getHP());
            System.out.println("Current Enemy HP: " + enemy.getHP());
            System.out.println("Current NRG: " + player.getNRG() + "\n");

            int input = sc.nextInt();
            switch(input){
                case(1):
                    playerTurnManager.printItems();
                    int choice = Integer.parseInt(sc.next());
                    playerTurnManager.useItems(choice, enemy);
                    turns--; break;
                case(2):
                    playerTurnManager.printSkills();
                    choice = Integer.parseInt(sc.next());
                    playerTurnManager.runSkill(choice, enemy); break;
                default:
                    System.out.println("You taunt the enemy!");
                    turns--; break;
            }
        }
    }

    public void otherState(){
        enemy.nmeTurn();
    }
}

package Managers;

import NME.NmeBase;
import NME.Stats;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class PlayerTurnManager {
    private Stats player;
    private File itemBlock;
    private Random random = new Random();
    private ArrayList<String[]> dictionary;
    private Scanner fileScanner;

    public PlayerTurnManager(Stats plr) throws FileNotFoundException{
        player = plr;
        dictionary = new ArrayList<String[]>();
        itemBlock = new File("Data/ITM.txt");
    }
    public void printItems(){
        dictionary.clear();
        System.out.println("ITEMS");
        int option = 0;
        for(String item : player.getInventry()){
            System.out.println(option + ") " + item);
            String[] data = {"" + option, item};
            dictionary.add(data);
            option++;
        }
    }

    public void useItems(int input, NmeBase nme) throws FileNotFoundException{
        for(String[] data : dictionary){
            if(input == Integer.parseInt(data[0])){
                this.findItem(data[1], nme);
                return;
            }
        }
    }

    public void findItem(String item, NmeBase target) throws FileNotFoundException{
        String[] data = new String[3];
        boolean found = false;
        try {
            fileScanner = new Scanner(itemBlock);
        } catch (Exception e) {System.out.println("Scanner could not connect!");}
        // data format: name;function;value;permanent
        //-------------String;String;double;boolean
        while(fileScanner.hasNextLine()){
            data = fileScanner.nextLine().split(";");
            if(!data[0].equals(item)) continue;
            found = true;
            break;
        }
        if(!found) return;
        functionality(data, target);
    }

    public void functionality(String[] itemData, NmeBase Nme){
        String function = itemData[1];
        double value = Double.parseDouble(itemData[2]);
        if(!Boolean.parseBoolean(itemData[3]))
            player.getInventry().remove(itemData[0]);
        switch(function){
            case("DMG"):
                if(random.nextInt(20) == 0){
                    value *=2;
                    System.out.println("Critical hit!");}
                System.out.println("Your " + itemData[0] + " dealt " + value + " damage!");
                player.takeDmg(value, Nme); break;
            case("HEA"):
                System.out.println("You healed " + value +" HP with your " + itemData[0]);
                player.takeDmg(-value, player); break;
            case("FOC"):
                System.out.println("You gained " + value + " NRG using the " + itemData[0]);
                player.useNRG(-1 * (int) value - 1);
            default:
                player.incrementNRG();
            break;
        }

    }
    // Manage NRG usage... SUPER INEFFICIENT, I KNOW!
    // i was tired-- this will be fixed in future
    public void printSkills(){
        System.out.println("NRG skills: ");
        System.out.println("1) Bind | HEAL 10 hp | 2 FOC" +
                            "\n2) Strike | ATTK 10 dmg | 3 FOC" +
                            "\n3) Ouroborus | FOCUS 2 FOC | 2 FOC" + 
                            "\n4) Planning | [???] | 4 FOC" +
                            "\n5) The Flaming Brand | ATTK 40 dmg | 10 FOC" +
                            "\n6) The Icey Brand | ATTK 12 dmg & HEAL 15 hp | 5 FOC");
    }
    
    public void runSkill(int choice, NmeBase nme){
        boolean doSomething = false;
        switch(choice){
            case(1): 
                if(player.getNRG() >= 2){doSomething = true;
                System.out.println("You binded for 10 HP!");
                player.takeDmg(-10, player); player.useNRG(2); break;}
            case(2): 
                if(player.getNRG() >= 3){doSomething = true;
                System.out.println("You striked the enemy for 10 DMG!");
                player.takeDmg(10, nme); player.useNRG(3); break;}
            case(4): 
                if(player.getNRG() >= 4){doSomething = true;
                System.out.println("Your planning something...");
                player.useNRG(4);
                if(random.nextInt(2) == 0) player.takeDmg(12, nme);
                if(random.nextInt(4) == 0) player.takeDmg(16, player);
                if(random.nextInt(6) == 0) player.takeDmg(16, nme);
                if(random.nextInt(2) == 0) player.takeDmg(-12, player); break;}
            case(5): 
                if(player.getNRG() >= 10){doSomething = true;
                System.out.println("You use the Grand Brand to imbue the enemy with FLAME!");
                player.takeDmg(44, nme); player.useNRG(10); break;}
            case(6):
                if(player.getNRG() >= 5){doSomething = true;
                System.out.println("You use the Grand Brand to imbue yourself with HEALTH at the cost of the enemies WARMTH!");
                player.takeDmg(12, nme); player.takeDmg(-12, player); player.useNRG(5); break;}
            case(3): default:
                if(player.getNRG() >= 2){doSomething = true;
                System.out.println("The cycle continues..."); break;}
        }
        if(!doSomething){
            System.out.println("Not enough NRG!");
        }
    }

    public void closeScanner(){
        fileScanner.close();
    }
    

}

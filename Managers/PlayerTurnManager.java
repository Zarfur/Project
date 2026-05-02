package Managers;

import NME.Stats;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlayerTurnManager {
    private Stats player;
    private File itemBlock;
    private ArrayList<String[]> dictionary;
    private Scanner fileScanner;
    public PlayerTurnManager(Stats plr) throws FileNotFoundException{
        player = plr;
        dictionary = new ArrayList<String[]>();
        try {
            itemBlock = new File("ITM.txt");
        } catch (Exception e) { System.out.print("FILE NOT FOUND");}
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
    public void functionality(String item) throws FileNotFoundException{
        String[] data;
        try {
            fileScanner = new Scanner(itemBlock);
        } catch (Exception e) {System.out.println("Scanner could not connect!");}

        while(fileScanner.hasNextLine()){
            
        }
    }
}

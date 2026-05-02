import java.io.FileNotFoundException;
import java.util.Scanner;
public class Main{
    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name, adventurer!: ");
        String name = sc.nextLine();
        Game game = new Game(name, 100, sc);
        game.runGame();
    }
}
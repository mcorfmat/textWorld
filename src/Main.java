import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Level level = new Level();

        Player player = new Player("Marc", "description");
        level.initialize(player);
        player.setCurrentRoom(level.getRoom("hall"));

        String response = "";
        Scanner s = new Scanner(System.in);

        do {
            System.out.println("[You are now in the " + player.getCurrentRoom().getName() + "]");
            System.out.println("What would you like to do? >");
            response = s.nextLine();

            String[] words = response.split(" ");

            Command command = new Command(response, level, player);

        } while (!response.equals("quit"));
    }
}

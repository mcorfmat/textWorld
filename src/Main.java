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

            executeCommands(words, level, player);

        } while (!response.equals("quit"));
    }

    private static void executeCommands(String[] words, Level level, Player player) {
        if (words[0].equals("go") && words.length >= 2) {
            player.setCurrentRoom(player.getCurrentRoom().getNeighbor(words[1]));
            level.moveCreatures();

        } else if (words[0].equals("look")) {
            System.out.println(player.getCurrentRoom().getNeighborNames());
            System.out.println("");
            System.out.println("[Items in this Room]");
            player.getCurrentRoom().displayItems();
            System.out.println("");
            System.out.println("[Creatures in this Room]");
            player.getCurrentRoom().displayCreatures();
            System.out.println("");
            System.out.println("");

        } else if (words[0].equals("add") && words.length >= 3) {
            player.getCurrentRoom().addNeighbor(new Level().new Room(words[2], "Blank"));

        } else if (words[0].equals("take") && words.length >= 2) {
            player.addItem(player.getCurrentRoom().removeItem(words[1]));
            System.out.println("You took the " + words[1] + " and added it to your inventory.");

        } else if (words[0].equals("quit")) {
            System.out.println("Quiting...");
        } else if (words[0].equals("drop") && words.length >= 2) {
            player.currentRoom.addItem(player.removeItem(words[1]));
            System.out.println("You dropped the " + words[1] + " and added it to the room.");

        } else {
            System.out.println("You can type \"look\" to find nearby rooms, \" go _roomname_ \" to go to it, \"add room <roomname\" to add a room, or \"quit\" to exit.");
        }
    }
}

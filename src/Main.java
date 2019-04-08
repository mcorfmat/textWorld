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
            System.out.println("You are in the " + player.getCurrentRoom().getName());
            System.out.println("What do you want to do? >");
            response = s.nextLine();

            String[] words = response.split(" ");

            if (words[0].equals("go") && words.length >= 2) {
                player.setCurrentRoom(player.getCurrentRoom().getNeighbor(words[1]));
                level.moveCreatures();
            } else if (words[0].equals("look")) {
                System.out.println(player.getCurrentRoom().getNeighborNames());
                player.getCurrentRoom().displayItems();
                player.getCurrentRoom().displayCreatures();
            } else if (words[0].equals("add") && words.length >= 3) {
                player.getCurrentRoom().addNeighbor(new Level().new Room(words[2], "Blank"));
            } else if (words[0].equals("take") && words.length >= 2) {
                player.addItem(player.getCurrentRoom().removeItem(words[1]));
            } else if (words[0].equals("quit")) {
                break;
            } else if (words[0].equals("drop") && words.length >= 2){
                player.currentRoom.addItem(player.removeItem(words[1]));
                System.out.println("Dropped" + words[1] + " and added it to the room");
            } else {
                System.out.println("You can type \"look\" to find nearby rooms, \" go _roomname_ \" to go to it, \"add room <roomname\" to add a room, or \"quit\" to exit.");
            }
        } while (!response.equals("quit"));
    }
}

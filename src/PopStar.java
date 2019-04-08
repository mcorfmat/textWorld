import java.util.ArrayList;
import java.util.HashMap;

public class PopStar extends Creature {
    private HashMap<String, Level.Room> rooms;
    private Player player;

    public PopStar(String name, String description, Level.Room currentRoom, Player player) {
        rooms = currentRoom.getNeighbors();
        this.player = player;

        this.currentRoom = currentRoom;
        this.name = name;
        this.description = description;

        currentRoom.addCreature(this);
    }

    public void move() {
        //TODO - The player should be 2 or less neighbors close to the popStar in order for it to move closer
        if (player.getCurrentRoom().isItANeighbor(currentRoom.getName()) == true) {
            this.currentRoom = moveCloser(player.currentRoom);
        }
    }

    private Level.Room moveCloser(Level.Room playerRoom) {
        //TODO - Complete the moveCloser() method which makes the PopStar move closer to the player's currentRoom

        return playerRoom;
    }
}

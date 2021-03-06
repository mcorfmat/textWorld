import java.util.ArrayList;
import java.util.HashMap;

public class Wumpus extends Creature {
    private HashMap<String, Level.Room> rooms;
    private Player player;

    public Wumpus(String name, String description, Level.Room currentRoom, Player player, HashMap<String, Level.Room> rooms) {
        this.rooms = rooms;
        this.player = player;

        this.currentRoom = currentRoom;
        this.name = name;
        this.description = description;

        currentRoom.addCreature(this);
    }

    public void move() {
        if (player.isTwoOrLessNeighborsAway(currentRoom, rooms)) {
            currentRoom.removeCreature(this);
            this.currentRoom = getRandomRoom(player.getCurrentRoom());
            currentRoom.addCreature(this);
        }
    }

    private Level.Room getRandomRoom(Level.Room omitRoom) {
        ArrayList<Level.Room> roomsArr = new ArrayList<>(rooms.values());
        roomsArr.remove(omitRoom);

        int randInt = (int) (Math.random() * roomsArr.size());

        return roomsArr.get(randInt);
    }
}

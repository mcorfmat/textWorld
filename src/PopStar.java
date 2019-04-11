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
        if( player.isTwoOrLessNeighborsAway(currentRoom, rooms)){
            this.currentRoom = moveCloser(player.currentRoom);
        }
    }

    private Level.Room moveCloser(Level.Room playerRoom) {
        ArrayList<Level.Room> possibleRooms = new ArrayList<>();

        possibleRooms = getPossibleRooms(playerRoom);

        int randInt = (int) (Math.random() * possibleRooms.size());

        return possibleRooms.get(randInt);
    }

    private ArrayList<Level.Room> getPossibleRooms(Level.Room playerRoom) {
        ArrayList<Level.Room> playerNeighbors = new ArrayList<>(playerRoom.getNeighbors().values());
        ArrayList<Level.Room> possibleRooms = new ArrayList<>();

        for (int i = 0; i < playerNeighbors.size(); i++){
            possibleRooms.add(playerNeighbors.get(i));

            ArrayList<Level.Room> playerNeighborNeighbors = new ArrayList<>(playerNeighbors.get(i).getNeighbors().values());
            for (int j = 0; j < playerNeighborNeighbors.size(); i++){
                possibleRooms.add(playerNeighborNeighbors.get(i));
            }
        }

        return possibleRooms;
    }
}

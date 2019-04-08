import java.util.ArrayList;
import java.util.HashMap;

public class Chicken extends Creature {
    private HashMap<String, Level.Room> rooms;

    public Chicken(String name, String description, Level.Room currentRoom){
        rooms = currentRoom.getNeighbors();

        this.currentRoom = currentRoom;
        this.name = name;
        this.description = description;

        currentRoom.addCreature(this);
    }
    
    public void move(){
        currentRoom.removeCreature(this);
        this.currentRoom = getRandomRoom();
        currentRoom.addCreature(this);
    }

    private Level.Room getRandomRoom(){
        ArrayList<Level.Room> roomsArr =new ArrayList<>(rooms.values());

        int randInt = (int)(Math.random()*roomsArr.size());

        return roomsArr.get(randInt);
    }
}

import java.util.HashMap;

public abstract class Creature {
    protected Level.Room currentRoom;
    protected String name;
    protected String description;

    public abstract void move();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level.Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Level.Room newRoom) {
        this.currentRoom = newRoom;
    }
}

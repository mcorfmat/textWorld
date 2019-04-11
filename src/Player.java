import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    String name;
    String description;
    ArrayList<Item> items;
    Level.Room currentRoom;

    public Player(String name, String description) {
        items = new ArrayList<>();
        this.name = name;
        this.description = description;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item removeItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                Item removedItem = item;
                items.remove(item);
                return removedItem;
            }
        }
        return null;
    }

    public boolean destroyItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return items.remove(item);
            }
        }
        return false;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void displayInventory() {
        System.out.println("");
        for (Item item : items) {
            System.out.print(item.getName() + ", ");
        }
    }

    public Level.Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Level.Room newRoom) {
        currentRoom = newRoom;
    }

    public boolean moveToRoom(String name) {
        Level.Room moveTo = currentRoom.getNeighbor(name);
        if (moveTo == null) {
            return false;
        }

        currentRoom = moveTo;
        return true;
    }

    public boolean isTwoOrLessNeighborsAway(Level.Room creatureRoom, HashMap<String, Level.Room> rooms) {
        ArrayList<Level.Room> roomsArr = new ArrayList<>(rooms.values());

        for (int i = 0; i < roomsArr.size(); i++){
            if(roomsArr.get(i).getNeighbor(creatureRoom.getName()) != null) {
                return true;
            }
            else {
                ArrayList<Level.Room> roomNeighbors = new ArrayList<>(roomsArr.get(i).getNeighbors().values());
                for(int j = 0; j < roomNeighbors.size(); j++){
                    if (roomNeighbors.get(j).getNeighbor(creatureRoom.getName()) != null){
                        return true;
                    }
                }
            }
        }

        return false;
    }
}

import java.util.ArrayList;

public class Player {
    String name;
    String description;
    ArrayList<Item> items;
    Level.Room currentRoom;

    public Player(String name, String description){
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

    public boolean destroyItem(String name){
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return items.remove(item);
            }
        }
        return false;
    }

    public ArrayList<Item> getItems(){
        return items;
    }

    public void displayInventory(){
        System.out.println("");
        for (Item item: items) {
            System.out.print(item.getName() + ", ");
        }
    }

    public Level.Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Level.Room newRoom){
        currentRoom = newRoom;
    }

    public boolean moveToRoom(String name){
        Level.Room moveTo = currentRoom.getNeighbor(name);
        if (moveTo == null) {
            return false;
        }

        currentRoom = moveTo;
        return true;
    }
}

import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;
import java.util.HashMap;

public class Level {
    private HashMap<String, Room> rooms;
    private ArrayList<Creature> creatures;

    public Level() {
        rooms = new HashMap<>();
    }

    public void addRoom(String name, String description) {
        Room n = new Room(name, description);
        rooms.put(name, n);
    }

    public ArrayList<Creature> getCreatures(){
        return creatures;
    }

    public Level.Room getRandomRoom() {
        ArrayList<Level.Room> roomsArr = new ArrayList<>(rooms.values());

        int randInt = (int) (Math.random() * roomsArr.size());

        return roomsArr.get(randInt);
    }

    public void addDirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1);
        Room n2 = getRoom(name2);

        n1.addNeighbor(n2);
    }

    public void moveCreatures() {
        for (Creature creature : creatures) {
            creature.move();
        }
    }

    public HashMap<String, Room> getAllRooms() {
        return rooms;
    }

    public void addUndirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1);
        Room n2 = getRoom(name2);

        n1.addNeighbor(n2);
        n2.addNeighbor(n1);
    }

    public Room getRoom(String name) {
        Room room = new Room("UNDEFINED", "UNDEFINED");

        if (rooms.get(name) != null) {
            return rooms.get(name);
        }

        return room;
    }

    public void initialize(Player player) {
        this.addRoom("hall", "A long hallway with some creaking here and there.");
        this.addRoom("closet", "Clothes. What else did you expect?");
        this.addRoom("dungeon", "Huh, so there actually was something besides clothes in the closet.");
        this.addRoom("bedroom", "A place to rest.");
        this.addRoom("attic", "It's really hot.");
        this.addRoom("garden", "There's some nice flowers here.");
        this.addRoom("garage", "The car is missing...");
        this.addRoom("lounge", "This couch is really comfortable!");
        this.addRoom("kitchen", "I'm very hungry...");

        this.addUndirectedEdge("hall", "bedroom");
        this.addDirectedEdge("hall", "dungeon");
        this.addUndirectedEdge("hall", "closet");
        this.addUndirectedEdge("closet", "attic");
        this.addUndirectedEdge("bedroom", "lounge");
        this.addUndirectedEdge("hall", "garage");
        this.addUndirectedEdge("kitchen", "garden");
        this.addUndirectedEdge("hall", "kitchen");

        Item mug = new Item("mug", "for drinking");
        Item cookie = new Item("cookie", "requires milk");
        Item PS4 = new Item("PS4", "looks broken");
        Item rustyKey = new Item("Rusty Key", "what does it open?");
        Item oldLegoSet = new Item("old Lego Set", "probably worth something nowadays");

        creatures = new ArrayList<>();

        creatures.add(new Chicken("Bob", "*cluck*", this.getRandomRoom(), this.getAllRooms()));
        creatures.add(new Chicken("Bobby", "*cluck*", this.getRandomRoom(), this.getAllRooms()));
        creatures.add(new Chicken("Bobert", "*cluck*", this.getRandomRoom(), this.getAllRooms()));

        creatures.add(new Wumpus("Steve", "*anti-social*", this.getRandomRoom(), player, this.getAllRooms()));
        creatures.add(new Wumpus("Steven", "*anti-social*", this.getRandomRoom(), player, this.getAllRooms()));

        this.getRandomRoom().addItem(mug);
        this.getRandomRoom().addItem(PS4);
        this.getRandomRoom().addItem(oldLegoSet);
        this.getRandomRoom().addItem(rustyKey);
        this.getRandomRoom().addItem(cookie);
    }

    public class Room {
        private ArrayList<Creature> creatures;

        private String name;
        private String description;
        private HashMap<String, Room> neighbors;
        private ArrayList<Item> items;

        public Room(String name, String description) {
            creatures = new ArrayList<>();

            neighbors = new HashMap<>();
            items = new ArrayList<>();
            this.name = name;
            this.description = description;
        }

        public void addNeighbor(Room n) {
            neighbors.put(n.getName(), n);
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getNeighborNames() {
            String names = "";

            names = neighbors.keySet().toString();

            return names;
        }

        public Room getNeighbor(String name) {
            if (neighbors.get(name) != null) {
                return neighbors.get(name);
            }

            return null;
        }

        public boolean isItANeighbor(String name) {
            return (getNeighbor(name) != null);
        }

        public String getName() {
            return name;
        }

        public HashMap<String, Room> getNeighbors() {
            return neighbors;
        }

        public ArrayList<Item> getItems() {
            return items;
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

        public void displayItems() {
            for (Item item : items) {
                System.out.print(item.getName() + ", ");
            }
        }

        public void addCreature(Creature newCreature) {
            creatures.add(newCreature);
        }

        public void removeCreature(Creature creature) {
            creatures.remove(creature);
        }

        public void displayCreatures() {
            for (Creature creature : creatures) {
                System.out.print(creature.getName() + ", ");
            }
        }
    }
}

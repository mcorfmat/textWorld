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

    public void addDirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1);
        Room n2 = getRoom(name2);

        n1.addNeighbor(n2);
    }

    public void moveCreatures() {
        for (Creature creature: creatures) {
            creature.move();
        }
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
        this.addRoom("bedroom", "Finally, a bed.");

        this.addUndirectedEdge("hall", "bedroom");
        this.addDirectedEdge("hall", "dungeon");
        this.addUndirectedEdge("hall", "closet");

        Item mug = new Item("mug", "for drinking");
        Item cookie = new Item("cookie", "requires milk");
        Item PS4 = new Item("PS4", "looks broken");
        Item oldLegoSet = new Item("old Lego Set", "probably worth something nowadays");

        creatures = new ArrayList<>();

        creatures.add(new Chicken("Bob", "*cluck*", this.getRoom("hall")));
        creatures.add(new Chicken("Bobby", "*cluck*", this.getRoom("hall")));
        creatures.add(new Chicken("Bobert", "*cluck*", this.getRoom("hall")));

        creatures.add(new Wumpus("Steve", "*anti-social*", this.getRoom("hall"), player));
        creatures.add(new Wumpus("Steven", "*anti-social*", this.getRoom("hall"), player));


        this.getRoom("hall").addItem(mug);
        this.getRoom("hall").addItem(PS4);

        this.getRoom("closet").addItem(oldLegoSet);

        this.getRoom("dungeon").addItem(PS4);
        this.getRoom("dungeon").addItem(cookie);
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
            if (neighbors.get(name) != null){
                return neighbors.get(name);
            }

            return null;
        }

        public boolean isItANeighbor(String name){
            return (getNeighbor(name) != null);
        }

        public String getName() {
            return name;
        }

        public HashMap<String, Room> getNeighbors(){
            return neighbors;
        }

        public ArrayList<Item> getItems(){
            return items;
        }

        public void addItem(Item item){
            items.add(item);
        }

        public Item removeItem(String name){
            for (Item item : items) {
                if (item.getName().equals(name)) {
                    Item removedItem = item;
                    items.remove(item);
                    return removedItem;
                }
            }
            return null;
        }

        public void displayItems(){
            System.out.println("");

            for (Item item: items) {
                System.out.print(item.getName() + ", ");
            }
        }

        public void addCreature(Creature newCreature) {
            creatures.add(newCreature);
        }

        public void removeCreature(Creature creature){
            creatures.remove(creature);
        }

        public void displayCreatures() {
            System.out.println("");

            for (Creature creature: creatures) {
                System.out.print(creature.getName() + ", ");
            }
        }
    }
}

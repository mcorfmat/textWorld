public class Command {
    private String[] words;
    private Player player;
    private Level level;

    public Command(String userInput, Level level, Player player) {
        this.words = userInput.split(" ");
        this.level = level;
        this.player = player;

        execute();
    }

    private void execute() {
        if (words[0].equals("go") && words.length >= 2) {
            player.setCurrentRoom(player.getCurrentRoom().getNeighbor(words[1]));
            level.moveCreatures();

        } else if (words[0].equals("look")) {
            System.out.println(player.getCurrentRoom().getNeighborNames());
            System.out.println("");
            System.out.println("[Items in this Room]");
            player.getCurrentRoom().displayItems();
            System.out.println("");
            System.out.println("[Creatures in this Room]");
            player.getCurrentRoom().displayCreatures();
            System.out.println("");
            System.out.println("");

        } else if (words[0].equals("add") && words.length >= 3) {
            player.getCurrentRoom().addNeighbor(new Level().new Room(words[2], "Blank"));

        } else if (words[0].equals("take") && words.length >= 2) {
            player.addItem(player.getCurrentRoom().removeItem(words[1]));
            System.out.println("You took the " + words[1] + " and added it to your inventory.");

        } else if (words[0].equals("quit")) {
            System.out.println("Quiting...");

        } else if (words[0].equals("drop") && words.length >= 2) {
            player.currentRoom.addItem(player.removeItem(words[1]));
            System.out.println("You dropped the " + words[1] + " and added it to the room.");

        } else {
            System.out.println("You can type \"look\" to find nearby rooms, \" go _roomname_ \" to go to it, \"add room <roomname\" to add a room, or \"quit\" to exit.");
        }

        System.out.println("-------------------------------------------");
    }
}

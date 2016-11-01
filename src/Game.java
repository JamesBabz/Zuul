
import java.util.Stack;


/**
 *  This class is the main class of the "World of Zuul" application.
 *  "World of Zuul" is a very simple, text based adventure game.  Users
 *  can walk around some scenery. That's all. It should really be extended
 *  to make it more interesting!
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Game {

    private Parser parser;
    private Room currentRoom;
    private Room lastRoom;
    private Stack<Room> backList;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        createRooms();
        parser = new Parser();
        backList = new Stack();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        // create the rooms
        Room town, wilderness1, wilderness2, wilderness3, wilderness4, wilderness5, wilderness6, denOutside, denInside, cave1, cave2, cave3, cave4, cave5, cave6, chestRoom;

        // Start
        town = new Room("inside the town");
        currentRoom = town;  // start game in town

        //Wilderness areas
        wilderness1 = new Room("out in the wilderness");
        wilderness2 = new Room("out in the wilderness");
        wilderness3 = new Room("out in the wilderness");
        wilderness4 = new Room("out in the wilderness");
        wilderness5 = new Room("out in the wilderness");
        wilderness6 = new Room("out in the wilderness");
        denOutside = new Room("out in the wilderness near an entrance to a den"); // Wilderness with den

        //Cave areas
        denInside = new Room("at the exit of the den"); // Start of the den
        cave1 = new Room("ainside the cave");
        cave2 = new Room("inside the cave");
        cave3 = new Room("inside the cave");
        cave4 = new Room("inside the cave");
        cave5 = new Room("inside the cave");
        cave6 = new Room("inside the cave");
        chestRoom = new Room("in a room with a golden chest");

        // initialise room exits
        town.setExit("east", wilderness1);
        wilderness1.setExit("south", wilderness2);
        wilderness1.setExit("west", town);
        wilderness2.setExit("north", wilderness1);
        wilderness2.setExit("east", wilderness3);
        wilderness2.setExit("south", wilderness4);
        wilderness3.setExit("east", denOutside);
        wilderness3.setExit("south", wilderness5);
        wilderness3.setExit("west", wilderness2);
        wilderness4.setExit("north", wilderness2);
        wilderness4.setExit("east", wilderness5);
        wilderness5.setExit("north", wilderness3);
        wilderness5.setExit("west", wilderness4);
        wilderness6.setExit("south", denOutside);
        denOutside.setExit("north", wilderness6);
        denOutside.setExit("west", wilderness3);
        denOutside.setExit("down", denInside);
        denInside.setExit("up", denOutside);
        denInside.setExit("south", cave1);
        cave1.setExit("north", denInside);
        cave1.setExit("east", cave2);
        cave1.setExit("south", cave4);
        cave2.setExit("north", cave1);
        cave2.setExit("west", cave3);
        cave3.setExit("south", cave2);
        cave4.setExit("north", cave1);
        cave4.setExit("south", cave6);
        cave4.setExit("west", cave5);
        cave5.setExit("east", cave4);
        cave6.setExit("north", cave4);
        cave6.setExit("east", chestRoom);
        chestRoom.setExit("west", cave6);

        // Create the items        
        Item club = new Item("club", "a", "This is a club", 2);
        Item axe = new Item("axe", "an", "this is an axe", 3);
        Item rareClub = new Item("rare club", "a", "this is a rare club", 2);

        // Set items in the rooms
        wilderness6.addItem(club);
        wilderness1.addItem(rareClub);
        wilderness1.addItem(axe);

    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        boolean finished = false;
        while (!finished)
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if (command.isUnknown())
        {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
        {
            printHelp();
        }
        else if (commandWord.equals("go"))
        {
            goRoom(command);
        }
        else if (commandWord.equals("look"))
        {
            look();
        }
        else if (commandWord.equals("eat"))
        {
            eat();
        }
        else if (commandWord.equals("back"))
        {
            back();
        }
        else if (commandWord.equals("quit"))
        {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:
    /**
     * Print out some help information. Here we print some stupid, cryptic
     * message and a list of the command words.
     */
    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /**
     * Try to go in one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     */
    private void goRoom(Command command)
    {
        if (!command.hasSecondWord())
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
        {
            System.out.println("There is no door!");
        }
        else
        {
            backList.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    private void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the game.
     *
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if (command.hasSecondWord())
        {
            System.out.println("Quit what?");
            return false;
        }
        else
        {
            return true;  // signal that we want to quit
        }
    }

    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    private void eat()
    {
        System.out.println("You have eaten and is now saturated");
    }

    private void back()
    {
        if (backList.empty())
        {
            System.out.println("You cannot go back from here");
        }
        else
        {
            currentRoom = backList.pop();
            printLocationInfo();
        }
    }
}


/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  The exits are labelled north,
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Room {

    private String description;
    private HashMap<String, Room> exits;
    private List<Item> items;

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "a kitchen" or "an open court yard".
     *
     * @param description The room's description.
     */
    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new ArrayList<>();
    }

    /**
     * Define an exit from this room.
     *
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    void addItem(Item item)
    {
        items.add(item);
    }

    public List<Item> getItems()
    {
        return items;
    }
     
     
     
    

    
    
    /**
     * Return a description of the room’s exits, for example "Exits: north
     * west".
     *
     * @return A description of the available exits.
     */
    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys)
        {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Return a long description of this room, of the form: You are in the
     * kitchen. Exits: north west
     *
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        String returnString = "You are " + description;
        if(!items.isEmpty())
        {
            returnString = returnString + getAllItems();
        }
        returnString = returnString + ".\n" + getExitString();
        return returnString;
    }
    
    public String getAllItems()
    {
        String itemText = ".\nThere is " + items.get(0).getPrefix() + " " + items.get(0).getColor() + items.get(0).getName() + Game.ANSI_RESET;
            for (int i = 1; i < items.size(); i++)
            {
                itemText = itemText + " and " + items.get(i).getPrefix() + " " + items.get(i).getColor() + items.get(i).getName() + Game.ANSI_RESET;
            }
            itemText = itemText + " on the ground";
            return itemText;
    }
 
}

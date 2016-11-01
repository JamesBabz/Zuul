
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author James
 */
public class Player {

    private String name;
    private String archetype;
    private List<Item> items;
    private int level;
    Room currentRoom;

    public Player(String name, String archetype, int level)
    {
        this.name = name;
        this.archetype = archetype;
        this.level = level;
        items = new ArrayList<>();
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
     *
     * @param item
     */
    public void pickUpItem(String item)
    {
        for (int i = 0; i < currentRoom.getItems().size(); i++)
        {
            if (currentRoom.getItems().get(i).getName().equals(item))
            {
                System.out.println("You picked up the " + currentRoom.getItems().get(i).getColor() + currentRoom.getItems().get(i).getName() + Game.ANSI_RESET);
                addItem(currentRoom.getItems().get(i));
                currentRoom.getItems().remove(i);
                return;
            }
        }
        System.out.println("Cannot find the specified item");
    }

    /**
     *
     */
    public void dropItem(String item)
    {
        for (int i = 0; i < items.size(); i++)
        {
            if (items.get(i).getName().equals(item))
            {
                System.out.println("You dropped the " + items.get(i).getColor() + items.get(i).getName() + Game.ANSI_RESET);
                currentRoom.addItem(items.get(i));
                getItems().remove(i);
                return;
            }
        }
        System.out.println("Cannot find the specified item");
    }
}


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
    private int maxWeight;
    private int currentWeight;
    private int weightLeft;
    private int maxHitPoints;
    private int currentHitPoints;
    Room currentRoom;

    public Player(String name, String archetype, int level)
    {
        this.name = name;
        this.archetype = archetype;
        this.level = level;
        this.maxWeight = 4;
        this.currentWeight = 0;
        this.maxHitPoints = 100;
        this.currentHitPoints = this.maxHitPoints;
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

    public int getMaxWeight()
    {
        return maxWeight;
    }

    public void setMaxWeight(int MaxWeight)
    {
        this.maxWeight = MaxWeight;
    }

    public int getCurrentWeight()
    {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight)
    {
        this.currentWeight = currentWeight;
    }

    public int getWeightLeft()
    {
        return weightLeft = maxWeight - currentWeight;
    }

    /**
     *
     * @param item
     */
    public void pickUpItem(String item)
    {
        for (int i = 0; i < currentRoom.getItems().size(); i++)
        {
            Item currentItem = currentRoom.getItems().get(i);
            if (currentItem.getName().equals(item))
            {
                if (currentItem.getWeight() > getWeightLeft())
                {
                    System.out.println("You are overencoumbered. The " + currentItem.getName() + " weighs " + currentItem.getWeight() + ". "
                            + "You can only carry " + getWeightLeft());
                    return;
                }
                System.out.println("You picked up the " + currentItem.getColor() + currentItem.getName() + Game.ANSI_RESET);
                currentWeight = currentWeight + currentItem.getWeight();
                addItem(currentItem);
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
                currentWeight = currentWeight - items.get(i).getWeight();
                currentRoom.addItem(items.get(i));
                items.remove(i);
                return;
            }
        }
        System.out.println("Cannot find the specified item");
    }

    String displayItems()
    {
        String returnText = "You do not currently have any items";
        if (items.isEmpty())
        {
            return returnText;
        }
        returnText = "Current items: " + items.get(0).getColor() + items.get(0).getName() + Game.ANSI_RESET;
        for (int i = 1; i < items.size(); i++)
        {
            returnText = returnText + ", " + items.get(i).getColor() + items.get(i).getName() + Game.ANSI_RESET;
        }
        returnText = returnText + "\nWith a weight of (" + getCurrentWeight() + "/" + getMaxWeight() + ")";
        return returnText;
    }

    void eat(String item)
    {
        String k;
        int tempInt;
        for (int i = 0; i < items.size(); i++)
        {
            Item currentItem = items.get(i);
            if (currentItem.getName().equals(item))
            {
                if (currentItem.isEatable())
                {
                    System.out.println("you ate the " + currentItem.getName());
                    items.remove(i);
                    currentWeight = currentWeight - currentItem.getWeight();
                    
                    k = "maxWeight";
                    if (currentItem.getStats().containsKey(k))
                    {
                        tempInt = maxWeight;
                        maxWeight = maxWeight + currentItem.getStats().get(k);
                        System.out.println("Your maximum carry weight went from " + tempInt + " to " + maxWeight);
                    }
                    k = "hitPoints";
                    if (currentItem.getStats().containsKey(k))
                    {
                        tempInt = currentHitPoints;
                        currentHitPoints = currentHitPoints + currentItem.getStats().get(k);
                        System.out.println("Your current hp went from " + tempInt + " to " + currentHitPoints);
                    }
                    return;
                }
                System.out.println("I can not eat that!");
                return;
            }
        }
        System.out.println("I can not seem to find that item");
    }
}


import java.util.HashMap;
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
public class Item {

    
    static final String ANSI_BLACK = "\u001B[30m";
    static final String ANSI_BLUE = "\u001B[34m";
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_PURPLE = "\u001B[35m";
    
//    private static final String ANSI_YELLOW = "\u001B[33m";
//    private static final String ANSI_CYAN = "\u001B[36m";
//    private static final String ANSI_WHITE = "\u001B[37m";

    private String name;
    private String prefix;
    private String description;
    private int weight;
    private String rarity;
    private String rarityColor;
    private boolean eatable;
    private HashMap<String, Integer> stats;

    public Item(String name, String prefix, String description, int weight, String rarity, boolean eatable)
    {
        this.name = name;
        this.prefix = prefix;
        this.description = description;
        this.weight = weight;
        this.eatable = eatable;
        stats = new HashMap<String, Integer>();
        setRarity(rarity);
    }

    public String getName()
    {
        return name;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public String getDescription()
    {
        return description;
    }

    public int getWeight()
    {
        return weight;
    }

    public boolean isEatable()
    {
        return eatable;
    }

    public HashMap<String, Integer> getStats()
    {
        return stats;
    }

    public void setStats(String stat, int change)
    {
        stats.put(stat, change);
    }
    
    public String getRarity()
    {
        return rarity;
    }

    public String getColor()
    {
        return rarityColor;
    }

    private void setRarity(String rarity)
    {
        this.rarity = rarity;
        setRarityColor(rarity);
    }
    
    public void setRarityColor(String rarity)
    {
        String color = "";
        if(rarity.equals("common"))
        {
            color = ANSI_BLACK;
        }
        else if(rarity.equals("uncommon"))
        {
            color = ANSI_BLUE;
        }
        else if(rarity.equals("rare"))
        {
            color = ANSI_GREEN;
        }
        else if(rarity.equals("epic"))
        {
            color = ANSI_RED;
        }
        else if(rarity.equals("legendary"))
        {
            color = ANSI_PURPLE;
        }
        this.rarityColor = color;
    }

    
    

}

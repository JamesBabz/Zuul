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
    
    private String name;
    private String prefix;
    private String description;
    private double weight;
    
    public Item(String name, String prefix, String description, double weight){
        this.name = name;
        this.prefix = prefix;
        this.description = description;
        this.weight = weight;
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

    public double getWeight()
    {
        return weight;
    }
    
    
    
}

package com.jackrocks.baseballcards;

/**
 * Created by TheDude on 7/11/16.
 */
public class BaseballCard {
    public int id;
    public String Name;
    public String Brand;
    public String Year;
    public String Team;
    public String Description;
    public double Price;

    public BaseballCard(int id, String name, String brand, String year, String team, String description, double price) {
        this.id = id;
        this.Name = name;
        this.Brand = brand;
        this.Year = year;
        this.Team = team;
        this.Description = description;
        this.Price = price;



    }

}

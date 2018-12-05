package com.example.danieloneill.healthyhabits;

public class Drinks {

    String drinkId;
    String drinkName;
    int drinkCalorie;
    String drinkCategory;

    public Drinks(){

    }

    public Drinks(String drinkId, String drinkName, int drinkCalorie, String drinkCategory) {
        this.drinkId = drinkId;
        this.drinkName = drinkName;
        this.drinkCalorie = drinkCalorie;
        this.drinkCategory = drinkCategory;
    }

    public String getDrinkId() {
        return drinkId;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public int getDrinkCalorie() {
        return drinkCalorie;}

    public String getDrinkCategory() {
        return drinkCategory;
    }
}
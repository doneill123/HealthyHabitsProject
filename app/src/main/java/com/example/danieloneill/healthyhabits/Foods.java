package com.example.danieloneill.healthyhabits;

public class Foods {

    String foodId;
    String foodName;
    String foodCalorie;
    String foodCategory;

    public Foods(String foodId, String foodName, String foodCalorie, String foodCategory) {
    this.foodId = foodId;
    this.foodName = foodName;
    this.foodCalorie = foodCalorie;
    this.foodCategory = foodCategory;
    }

    public Foods(String id, String name, String category) {
    }

    public String getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodCalorie() { return foodCalorie; }

    public String getFoodCategory() {
        return foodCategory;
    }
}

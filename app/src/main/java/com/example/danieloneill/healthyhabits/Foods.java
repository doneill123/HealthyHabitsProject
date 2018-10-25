package com.example.danieloneill.healthyhabits;

public class Foods {

    String foodId;
    String foodName;
    String foodCategory;

    public Foods(){
    }

    public Foods(String foodId, String foodName, String foodCategory) {
    this.foodId = foodId;
    this.foodName = foodName;
    this.foodCategory = foodCategory;
    }

    public String getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodCategory() {
        return foodCategory;
    }
}

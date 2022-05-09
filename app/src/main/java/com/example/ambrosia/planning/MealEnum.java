package com.example.ambrosia.planning;

public enum MealEnum {
    PetitDéjeuner(0),
    Déjeuner(1),
    Goûter(2),
    Diner(3);

    MealEnum(int i) {
    }

    public static String get(int i) {
        return MealEnum.values()[i].toString();
    }
}

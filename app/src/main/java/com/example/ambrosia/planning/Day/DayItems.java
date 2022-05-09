package com.example.ambrosia.planning.Day;

import com.example.ambrosia.planning.DayEnum;
import com.example.ambrosia.planning.Details.Food;

import java.util.Arrays;
import java.util.Observable;

public class DayItems extends Observable {
    private DayEnum day;
    private Food[] foodList;

    public DayItems(DayEnum day) {
        this.day = day;
    }

    public void setRepas(Food dej, Food midi, Food gouter, Food diner) {
        foodList = new Food[]{dej, midi, gouter, diner};
    }

    public DayEnum getDay() {
        return day;
    }

    public Food getFood(int i) {
        return foodList[i];
    }

    public int getSize() {
        return foodList.length;
    }

    public int sumKcal() {
        return Arrays.stream(foodList).map(Food::getCal).reduce(0, Integer::sum);
    }

    public void click(int i){
        setChanged();
        notifyObservers(this.getFood(i));
    }
}

package com.example.ambrosia.planning.Week;

import java.util.ArrayList;
import java.util.List;

public class WeekItems {
    private final List<Integer> kcals;

    public WeekItems() {
        this.kcals = new ArrayList<>();
    }

    public List<Integer> getKcals() {
        return kcals;
    }

    public int getSize() {
        return this.kcals.size();
    }

    public int getItems(int i) {
        return kcals.get(i);
    }

    public void add(int kcal) {
        kcals.add(kcal);
    }
}

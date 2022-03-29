package com.example.ambrosia.planning.Week;

import java.util.ArrayList;
import java.util.List;

public class WeekItems {
    private final List<Integer> kcals;

    public WeekItems(List<Integer> kcals){
        this.kcals = kcals;
    }

    public List<Integer> getKcals() {
        return kcals;
    }
}

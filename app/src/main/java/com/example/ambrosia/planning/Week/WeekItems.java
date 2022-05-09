package com.example.ambrosia.planning.Week;

import com.example.ambrosia.planning.Day.DayItems;

import java.util.ArrayList;
import java.util.List;

public class WeekItems {
    private List<DayItems> dayItems;

    public WeekItems() {
        this.dayItems = new ArrayList<>();
    }

    public int getSize() {
        return this.dayItems.size();
    }

    public List<DayItems> getDayItems() {
        return new ArrayList<>(dayItems);
    }

    public DayItems getDay(int i) {
        return dayItems.get(i);
    }

    public void add(DayItems dayItem) {
        dayItems.add(dayItem);
    }
}

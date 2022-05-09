package com.example.ambrosia.planning.Day;

import com.example.ambrosia.planning.Food;

import java.util.Observable;

public class DayItems extends Observable {
    private Food dej;
    private Food midi;
    private Food gouter;
    private Food diner;
    private DayEnum day;

    public DayItems(DayEnum day) {
        this.day = day;
    }

    public void setRepas(Food dej, Food midi, Food gouter, Food diner) {
        this.dej = dej;
        this.midi = midi;
        this.gouter = gouter;
        this.diner = diner;
        setChanged();
        notifyObservers();
    }

    public DayEnum getDay() {
        return day;
    }

    public String getDej() {
        return dej.getName();
    }

    public String getMidi() {
        return midi.getName();
    }

    public String getDiner() {
        return diner.getName();
    }

    public String getGouter() {
        return gouter.getName();
    }

    public int sumKcal() {
        return this.dej.getCal() + this.midi.getCal() + this.gouter.getCal() + this.diner.getCal();
    }
}

package com.example.ambrosia.planning.Day;

import java.util.Observable;

public class DayItems extends Observable {
    private String dej;
    private String midi;
    private String gouter;
    private String diner;
    private DayEnum day;

    public DayItems(DayEnum day){
        this.day = day;
    }

    public void setRepas(String dej, String midi, String gouter, String diner){
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
        return dej;
    }

    public String getMidi() {
        return midi;
    }

    public String getDiner() {
        return diner;
    }

    public String getGouter() {
        return gouter;
    }
}

package com.example.ambrosia.planning.Day;

public class DayItems {
    private String dej;
    private String midi;
    private String gouter;
    private String diner;
    private DayEnum day;

    public DayItems(DayEnum day){
        this.day = day;
    }

    public DayEnum getDay() {
        return day;
    }

    public String getDej() {
        return dej;
    }

    public void setDej(String dej) {
        this.dej = dej;
    }

    public String getMidi() {
        return midi;
    }

    public void setMidi(String midi) {
        this.midi = midi;
    }

    public String getDiner() {
        return diner;
    }

    public void setDiner(String diner) {
        this.diner = diner;
    }

    public String getGouter() {
        return gouter;
    }

    public void setGouter(String gouter) {
        this.gouter = gouter;
    }
}

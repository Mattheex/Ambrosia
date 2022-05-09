package com.example.ambrosia.planning;

public enum DayEnum {
    Lundi(0),
    Mardi(1),
    Mercredi(2),
    Jeudi(3),
    Vendredi(4),
    Samedi(5),
    Dimanche(6);

    DayEnum(int i) {
    }

    public static String get(int i) {
        return DayEnum.values()[i].toString();
    }
}

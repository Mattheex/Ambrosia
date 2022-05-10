package com.example.ambrosia.programmes;

public abstract class ProgrammeFactory {
    public static final String PRISEPOIDS="Prise de poids";
    public static final String PERTEPOIDS="Perte de poids";
    public static final String FITNESS="Prise de masse musculaire";
    public static final String VEGAN="Devenir végétarien";
    abstract protected Programme choose(String type, int jour) throws Throwable;

}

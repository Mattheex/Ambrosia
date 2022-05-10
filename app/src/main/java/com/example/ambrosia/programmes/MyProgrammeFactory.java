package com.example.ambrosia.programmes;

public class MyProgrammeFactory extends ProgrammeFactory{
    public Programme choose(String type, int jour) throws  Throwable{
        switch (type){
            case PRISEPOIDS: return new PrisePoids(jour);
            case PERTEPOIDS: return new PertePoids(jour);
            case FITNESS: return new Fitness(jour);
            case VEGAN: return new Vegan();
            default: throw new Throwable("not a regime");
        }
    }
}

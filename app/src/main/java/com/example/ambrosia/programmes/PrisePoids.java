package com.example.ambrosia.programmes;

public class PrisePoids extends ProgrammeTime{
    public PrisePoids(int jour){
        super(jour);
        nom = "PrisePoids";
        System.out.println("PrisePoids is created");
        url.addArguments("calories", "1000-10000");
    }
    @Override
    public String toString() {
        return ProgrammeFactory.PRISEPOIDS;
    }
}

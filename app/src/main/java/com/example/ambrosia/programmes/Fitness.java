package com.example.ambrosia.programmes;

public class Fitness extends ProgrammeTime {
    public Fitness(int jour){
        super(jour);
        nom = "Fitness";
        System.out.println("Fitness is created");
//        url.addArguments("diet", "high-fiber");
//        url.addArguments("diet", "high-protein");
    }
    @Override
    public String toString() {
        return ProgrammeFactory.FITNESS;
    }
}

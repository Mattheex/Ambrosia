package com.example.ambrosia.programmes;

import com.example.ambrosia.planning.URL;

public class PertePoids extends ProgrammeTime {
    public PertePoids(int jour){
        super(jour);
        nom = "PertePoids";
        url.addArguments("diet", "low-carb");
        url.addArguments("diet", "low-fat");
        url.addArguments("diet", "low-sodium");
    }

    @Override
    public String toString() {
        return ProgrammeFactory.PERTEPOIDS;
    }

}

package com.example.ambrosia.programmes;

public class Vegan extends Programme{
    public Vegan(){
        super();
        nom = "Vegan";
        System.out.println("Vegan is created");
        url.addArguments("diet", "balanced");
        url.addArguments("health", "vegetarian");
    }
    @Override
    public String toString() {
        return ProgrammeFactory.VEGAN;
    }
}

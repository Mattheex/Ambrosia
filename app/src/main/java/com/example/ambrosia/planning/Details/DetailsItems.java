package com.example.ambrosia.planning.Details;

import java.util.List;

public class DetailsItems {
    private final List<Integer> kcals;

    public DetailsItems(List<Integer> kcals){
        this.kcals = kcals;
    }

    public List<Integer> getKcals() {
        return kcals;
    }
}

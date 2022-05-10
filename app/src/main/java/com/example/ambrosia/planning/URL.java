package com.example.ambrosia.planning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class URL {
    String base;
    Map<String, List<String>> arguments = new HashMap<>();

    public URL(String base) {
        this.base = base;
    }

    public void addArguments(String name, String value) {
        if (arguments.containsKey(name)) {
            arguments.get(name).add(value);
        } else {
            arguments.put(name, new ArrayList<>(Arrays.asList(value)));
        }
    }

    String getUrl() {
        base += "?";
        arguments.forEach((key, values) -> values.forEach((value) -> base += key + "=" + value + "&"));
        return base.substring(0, base.length() - 1);
    }
}

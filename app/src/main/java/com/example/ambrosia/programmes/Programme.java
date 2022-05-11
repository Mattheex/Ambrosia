package com.example.ambrosia.programmes;

import com.example.ambrosia.planning.URL;

public abstract class Programme {
    protected String nom;
    protected URL url;
    public Programme(){
        url = new URL("https://api.edamam.com/api/recipes/v2");
        url.addArguments("type", "public");
        url.addArguments("app_id", "844cd12e");
        url.addArguments("app_key", "9fc9723d156610f2a652ddedeaa141ad");
        url.addArguments("field", "label");
        url.addArguments("field", "calories");
        url.addArguments("field", "image");
        url.addArguments("field", "url");
        url.addArguments("field", "source");
        url.addArguments("field", "ingredientLines");
        url.addArguments("field", "totalNutrients");
        url.addArguments("health", "alcohol-free");
        url.addArguments("cuisineType", "French");
        url.addArguments("random", "true");
    }
    public URL getURL(){
        return url;
    }



}

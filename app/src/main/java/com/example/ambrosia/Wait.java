package com.example.ambrosia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ambrosia.Users.User;
import com.example.ambrosia.planning.Details.Food;
import com.example.ambrosia.planning.URL;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Wait extends AppCompatActivity {
    public User user;
    String url = "https://api.edamam.com/api/recipes/v2";
    List<Food> repas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        user = getIntent().getParcelableExtra("Profil");

        OkHttpHandler okHttpHandler = new OkHttpHandler();
        try {
            okHttpHandler.execute(url, user.getProgramme());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public class OkHttpHandler extends AsyncTask {

        OkHttpClient client = new OkHttpClient();
        String[] typeMeal = new String[]{"Breakfast", "Lunch", "Snack", "Dinner"};
        boolean trad = false;

        @Override
        protected Object doInBackground(Object[] objects) {
            URL url;
            Response response;
            for (int i = 0; i < 4; i++) {
                url = user.getMyProgramme((String) objects[1]).getURL();
                url.addArguments("mealType", typeMeal[i]);
                Log.d("appDev", url.getUrl());
                Request request = new Request.Builder().url(url.getUrl()).build();
                try {
                    response = client.newCall(request).execute();
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    JSONArray jsonArray = jsonObject.getJSONArray("hits");
                    for (int k = 0; k < jsonArray.length(); k++) {
                        jsonObject = jsonArray
                                .getJSONObject(k)
                                .getJSONObject("recipe");
                        String name = jsonObject.getString("label");
                        String image = jsonObject.getString("image");
                        String source = jsonObject.getString("source");
                        String urlFood = jsonObject.getString("url");
                        Integer cal = (int) Math.round(jsonObject.getDouble("calories"));
                        JSONArray jsonArrayIngredients = jsonObject.getJSONArray("ingredientLines");
                        List<String> ingredientList = new ArrayList<>();
                        for (int l = 0; l < jsonArrayIngredients.length(); l++)
                            ingredientList.add((String) jsonArrayIngredients.get(l));
                        JSONObject jsonObjectNutrients = jsonObject.getJSONObject("totalNutrients");
                        JSONArray nutrientsName = jsonObjectNutrients.names();
                        List<String> nutrientsList = new ArrayList<>();
                        for (int l = 0; l < nutrientsName.length(); l++) {
                            JSONObject nutrient = jsonObjectNutrients.getJSONObject(nutrientsName.getString(l));
                            nutrientsList.add(nutrient.getString("label") + " : " +
                                    Math.round(nutrient.getDouble("quantity")) + " " + nutrient.getString("unit"));
                        }
                        if (trad) {
                            Request requestTranslate = new Request.Builder().url("https://api.mymemory.translated.net/get?q=" + name + "&langpair=en|fr").build();
                            Response responseTranslate = client.newCall(requestTranslate).execute();
                            JSONObject jsonTrans = new JSONObject(responseTranslate.body().string());
                            name = jsonTrans.getJSONObject("responseData").getString("translatedText");
                        }

                        //Bitmap bitmap = BitmapFactory.decodeStream(new java.net.URL(image).openStream());

                        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
                        repas.add(new Food(name, cal, image, urlFood, source, ingredientList, nutrientsList));
                    }
                    Log.d("appDev", "size " + repas.size());
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Intent home = new Intent(Wait.this, MainActivity.class);
            home.putExtra("Profil", user);
            home.putParcelableArrayListExtra("repas", (ArrayList<? extends Parcelable>) repas);
            //home.putExtra("repas", (Serializable) repas);

            //Log.d("appDev length", String.valueOf(repas.size()));
            startActivity(home);
            Wait.this.finish();
        }
    }
}
package com.example.ambrosia.planning;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ambrosia.MainActivity;
import com.example.ambrosia.R;
import com.example.ambrosia.Users.User;
import com.example.ambrosia.planning.Day.DayAdapter;
import com.example.ambrosia.planning.Day.DayItems;
import com.example.ambrosia.planning.Details.Details;
import com.example.ambrosia.planning.Details.Food;
import com.example.ambrosia.planning.Week.WeekAdapter;
import com.example.ambrosia.planning.Week.WeekItems;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * A simple {@link Fragment} subclass.
 */
public class Planning extends Fragment implements Observer {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static TextView motivation;
    //static int number = (int) (Math.random()*(10-1));
    String url = "https://api.edamam.com/api/recipes/v2";
    boolean day = true;
    DayAdapter dayAdapter;
    WeekAdapter weekAdapter;
    LinearLayout linearLayout;
    ViewPager2 viewPager2;
    List<WeekItems> weekItemsList = new ArrayList<>();
    User user;
    private WeekItems weekItems = new WeekItems();
    private Button changeScale;

    public static void newMotivatiion() {
        DocumentReference docRef = db.collection("Motivation").document(String.valueOf(MainActivity.number));
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    motivation.setText(document.getData().get("Phrase").toString());
                    Log.d("MotivationText", "DocumentSnapshot data: " + document.getData());
                } else {
                    MainActivity.number = 1;
                    Log.d("MotivationText", "No such document");
                }
            } else {
                Log.d("MotivationText", "get failed with ", task.getException());
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        newMotivatiion();
        user = getArguments().getParcelable("Profil");
        Log.d("Le profil recupéré est celui de: ", user.getFirst());
        viewPager2 = view.findViewById(R.id.pager2);

        if (weekItems.getSize() == 0) {
            Planning.OkHttpHandler okHttpHandler = new Planning.OkHttpHandler();
            okHttpHandler.execute(url, user.getProgramme());
        }

        weekAdapter = new WeekAdapter(weekItemsList, this);
        linearLayout = view.findViewById(R.id.linearLayoutDays);
        motivation = view.findViewById(R.id.motivQuoteText);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //Log.d("appDev", "position " + position);
                if (day) {
                    setCurrentDay(position);
                } else {
                    setCurrentWeek(position);
                }
            }
        });

        changeScale = view.findViewById(R.id.buttonChange);
        changeScale.setOnClickListener(view1 -> {
            if (day) {
                viewPager2.setAdapter(weekAdapter);
                setupIndicator(1);
                setCurrentWeek(0);
                day = false;
                changeScale.setText("Days");
            } else {
                viewPager2.setAdapter(dayAdapter);
                setupIndicator(0);
                setCurrentDay(0);
                day = true;
                changeScale.setText("Weeks");
            }
        });
        /*Food food = new Food("nutella");
        view.findViewById(R.id.motivQuoteText).setOnClickListener(view2 -> {
            Fragment detail = new Details();
            Bundle bundle = new Bundle();
            bundle.putParcelable("food", food);
            detail.setArguments(bundle);
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout, detail)
                    .addToBackStack(null)
                    .commit();
        });*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_planning, container, false);
    }

    public void newDays(Integer weekPos, int position) {
        dayAdapter = new DayAdapter(weekItemsList.get(weekPos).getDayItems());
        viewPager2.setAdapter(dayAdapter);
        viewPager2.post(() -> viewPager2.setCurrentItem(position, true));
        setupIndicator(0);
        day = true;
        changeScale.setText("Weeks");
    }

    @Override
    public void update(Observable observable, Object o) {
        DayItems dayItem = (DayItems) observable;
        Food food = (Food) o;
        Fragment detail = new Details();
        Bundle bundle = new Bundle();
        bundle.putParcelable("food", food);
        detail.setArguments(bundle);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frameLayout, detail)
                .addToBackStack(null)
                .commit();
    }

    private void setupIndicator(int scale) {
        TextView[] days = new TextView[3];
        linearLayout.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(10, 0, 10, 0);
        for (int i = 0; i < 3; i++) {
            days[i] = new TextView(getContext());
            days[i].setText((scale == 0) ? dayAdapter.getDay(i).toString() : "Week " + i);
            days[i].setLayoutParams(layoutParams);
            days[i].setTextSize(20);
            linearLayout.addView(days[i]);
        }
    }

    private void setCurrentDay(int index) {
        int[] limites = findIndex(index, dayAdapter.getItemCount());
        Log.d("appDev", Arrays.toString(limites));
        Log.d("appDev", "index " + index);
        for (int i = limites[0]; i <= limites[1]; i++) {
            TextView textView = (TextView) linearLayout.getChildAt(i - limites[0]);
            textView.setText(dayAdapter.getDay(i).toString());
            textView.setTextColor(Color.parseColor((i == index) ? "#000000" : "#616161"));
        }
    }

    private void setCurrentWeek(int index) {
        int[] limites = findIndex(index, weekAdapter.getItemCount());
        Log.d("appDev", Arrays.toString(limites));
        for (int i = limites[0]; i <= limites[1]; i++) {
            TextView textView = (TextView) linearLayout.getChildAt(i - limites[0]);
            textView.setText("Week " + i);
            textView.setTextSize(20);
            textView.setTextColor(Color.parseColor((i == index) ? "#000000" : "#616161"));
        }
    }

    private int[] findIndex(int index, int size) {
        int min, max;
        if (index == 0) {
            min = index;
            max = index + 2;
        } else if (index == size - 1) {
            min = index - 2;
            max = index;
        } else {
            min = index - 1;
            max = index + 1;
        }
        return new int[]{min, max};
    }


    public class OkHttpHandler extends AsyncTask {

        OkHttpClient client = new OkHttpClient();
        String[] typeMeal = new String[]{"Breakfast", "Lunch", "Snack", "Dinner"};
        List<Food> repas = new ArrayList<>();

        @Override
        protected Object doInBackground(Object[] objects) {
            URL url;
            Response response;
            for (int i = 0; i < 4; i++) {
                url = new URL((String) objects[0]);
                url.addArguments("type", "public");
                url.addArguments("app_id", "844cd12e");
                url.addArguments("app_key", "9fc9723d156610f2a652ddedeaa141ad");
                url.addArguments("mealType", typeMeal[i]);
                url.addArguments("field", "label");
                url.addArguments("field", "calories");
                url.addArguments("field", "totalNutrients");
                url.addArguments("health", "alcohol-free");
                url.addArguments("cuisineType", "French");
                url.addArguments("random", "true");
                switch ((String) objects[1]) {
                    case "Perte de poids":
                        url.addArguments("diet", "low-carb");
                        url.addArguments("diet", "low-fat");
                        url.addArguments("diet", "low-sodium");
                        break;
                    case "Prise de poids":
                        url.addArguments("calories", "1000-10000");
                        break;
                    case "Prise de masse musculaire":
                        url.addArguments("diet", "high-fiber");
                        url.addArguments("diet", "high-protein");
                        break;
                    case "Devenir végétarien":
                        url.addArguments("diet", "balanced");
                        url.addArguments("health", "vegetarian");
                        break;
                    default:
                        Log.e("Error Programme Name", (String) objects[1]);
                        return null;
                }
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
                        /*Request requestTranslate = new Request.Builder().url("https://api.mymemory.translated.net/get?q=" + name + "&langpair=en|fr").build();
                        Response responseTranslate = client.newCall(requestTranslate).execute();
                        JSONObject jsonTrans = new JSONObject(responseTranslate.body().string());
                        name = jsonTrans.getJSONObject("responseData").getString("translatedText");*/
                        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
                        Integer cal = (int) Math.round(jsonObject.getDouble("calories"));
                        repas.add(new Food(name, cal));
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
            for (int i = 0; i < 7 * 3; i++) {
                DayItems dayItems = new DayItems(DayEnum.values()[i % 7]);
                dayItems.addObserver(Planning.this);
                dayItems.setRepas(repas.get(i % 20),
                        repas.get(i % 20 + 20),
                        repas.get(i % 20 + 40),
                        repas.get(i % 20 + 60));
                weekItems.add(dayItems);
                if (weekItems.getSize() == 7) {
                    weekItemsList.add(weekItems);
                    weekItems = new WeekItems();
                }
            }
            dayAdapter = new DayAdapter(weekItemsList.get(0).getDayItems());
            viewPager2.setAdapter(dayAdapter);
            setupIndicator(0);
            setCurrentDay(0);
        }
    }
}
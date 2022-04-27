package com.example.ambrosia.planning;

import static com.example.ambrosia.planning.Day.DayEnum.Jeudi;
import static com.example.ambrosia.planning.Day.DayEnum.Lundi;
import static com.example.ambrosia.planning.Day.DayEnum.Mardi;
import static com.example.ambrosia.planning.Day.DayEnum.Mercredi;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ambrosia.R;
import com.example.ambrosia.planning.Day.DayAdapter;
import com.example.ambrosia.planning.Day.DayEnum;
import com.example.ambrosia.planning.Day.DayItems;
import com.example.ambrosia.planning.Details.Details;
import com.example.ambrosia.planning.Week.WeekAdapter;
import com.example.ambrosia.planning.Week.WeekItems;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
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
    boolean day = true;
    DayAdapter dayAdapter;
    WeekAdapter weekAdapter;
    LinearLayout linearLayout;
    List<DayItems> dayItemsList = new ArrayList<>();
    List<WeekItems> weekItemsList = new ArrayList<>();
    ViewPager2 viewPager2;
    String url = "https://www.foodrepo.org/api/v3/products?name_translations=lindt";
    TextView motivation;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewPager2 = view.findViewById(R.id.pager2);
        OkHttpHandler okHttpHandler = new OkHttpHandler();
        okHttpHandler.execute(url);
        //List<WeekItems> weekItemsList = listWeeks();

        weekAdapter = new WeekAdapter(weekItemsList);
        linearLayout = view.findViewById(R.id.linearLayoutDays);
        this.motivation = (TextView) view.findViewById(R.id.motivQuoteText);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //Log.d("position", String.valueOf(position));
                if (day) {
                    setCurrentDay(position);
                } else {
                    setCurrentWeek(position);
                }
            }
        });

        Button button = view.findViewById(R.id.buttonChange);
        button.setOnClickListener(view1 -> {
            if (day) {
                viewPager2.setAdapter(weekAdapter);
                setupWeeksIndicator();
                setCurrentWeek(0);
                day = false;
                button.setText("Days");
            } else {
                viewPager2.setAdapter(dayAdapter);
                setupDaysIndicator();
                setCurrentDay(0);
                day = true;
                button.setText("Weeks");
            }
        });

        Food food = new Food("nutella", 450);
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
        });
        /*view.findViewById(R.id.daysLayout).setOnClickListener(view3 -> {
            TextView textView = view3.findViewById(R.id.descriptionDej);
            Toast.makeText(getContext(),textView.getText(),Toast.LENGTH_LONG).show();
            //view3.findViewWithTag(R.id.);
        });*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_planning, container, false);
    }

    private WeekItems weekItems = new WeekItems();

    @Override
    public void update(Observable observable, Object o) {
        DayItems dayItems = (DayItems) observable;
        this.dayItemsList.add(dayItems);
        weekItems.add(dayItems.sumKcal());
        if(weekItems.getSize() == 3){
            this.weekItemsList.add(weekItems);
            weekItems = new WeekItems();
        }
    }

    private void setupDaysIndicator() {
        TextView[] days = new TextView[3];
        linearLayout.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(10, 0, 10, 0);
        for (int i = 0; i < 3; i++) {
            days[i] = new TextView(getContext());
            days[i].setText(dayAdapter.getDay(i).toString());
            days[i].setLayoutParams(layoutParams);
            days[i].setTextSize(20);
            linearLayout.addView(days[i]);
        }
    }

    private void setupWeeksIndicator() {
        TextView[] days = new TextView[3];
        linearLayout.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(10, 0, 10, 0);
        for (int i = 0; i < 3; i++) {
            days[i] = new TextView(getContext());
            days[i].setText("Week " + i);
            days[i].setLayoutParams(layoutParams);
            linearLayout.addView(days[i]);
        }
    }

    private void setCurrentDay(int index) {
        int[] limites = findIndex(index,dayAdapter.getItemCount());
        for (int i = limites[0]; i <= limites[1]; i++) {
            TextView textView = (TextView) linearLayout.getChildAt(i - limites[0]);
            textView.setText(dayAdapter.getDay(i).toString());
            textView.setTextColor(Color.parseColor((i == index) ? "#000000" : "#616161"));
        }
    }

    private void setCurrentWeek(int index) {
        int[] limites = findIndex(index,weekAdapter.getItemCount());
        for (int i = limites[0]; i <= limites[1]; i++) {
            TextView textView = (TextView) linearLayout.getChildAt(i - limites[0]);
            textView.setText("Week " + i);
            textView.setTextSize(20);
            textView.setTextColor(Color.parseColor((i == index) ? "#000000" : "#616161"));
        }
    }
    private void newMotivation(){
        motivation.setText("bonjour");
    }

    private int[] findIndex(int index, int size){
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
        return new int[]{min,max};
    }

    public class OkHttpHandler extends AsyncTask {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected Object doInBackground(Object[] objects) {
            Request request = new Request.Builder()
                    .url((String) objects[0])
                    .header("User-Agent", "Ambrosia")
                    .header("Accept", "application/json")
                    .header("Authorization", "Token token=0773fa9ce6eafe47256f9329542c6e32")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            try {
                JSONObject jsonObject = new JSONObject(String.valueOf(o));
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                List<Food> repas = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray
                            .getJSONObject(i)
                            .getJSONObject("name_translations");
                    if (jsonObject.has("fr")) {
                        String name = jsonObject.getString("fr");
                        repas.add(new Food(name,10));
                    }
                }
                int k = 0;
                for (int i = 0; i < 7; i++) {
                    DayItems dayItems = new DayItems(DayEnum.values()[i]);
                    dayItems.addObserver(Planning.this);
                    dayItems.setRepas(repas.get(k++ % repas.size()),
                            repas.get(k++ % repas.size()),
                            repas.get(k++ % repas.size()),
                            repas.get(k++ % repas.size()));
                }
                dayAdapter = new DayAdapter(dayItemsList);
                viewPager2.setAdapter(dayAdapter);
                setupDaysIndicator();
                setCurrentDay(0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
package com.example.ambrosia.planning;

import static com.example.ambrosia.planning.Day.DayEnum.Jeudi;
import static com.example.ambrosia.planning.Day.DayEnum.Lundi;
import static com.example.ambrosia.planning.Day.DayEnum.Mardi;
import static com.example.ambrosia.planning.Day.DayEnum.Mercredi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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

import com.example.ambrosia.MainActivity;
import com.example.ambrosia.R;
import com.example.ambrosia.Users.User;
import com.example.ambrosia.planning.Day.DayAdapter;
import com.example.ambrosia.planning.Day.DayItems;
import com.example.ambrosia.planning.Details.Details;
import com.example.ambrosia.planning.Week.WeekAdapter;
import com.example.ambrosia.planning.Week.WeekItems;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class Planning extends Fragment implements Observer {
    boolean day = true;
    DayAdapter dayAdapter;
    WeekAdapter weekAdapter;
    LinearLayout linearLayout;
    List<DayItems> dayItemsList = new ArrayList<>();
    User user;


    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    //static int number = (int) (Math.random()*(10-1));
    static TextView motivation;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        newMotivatiion();
        listDays();
        List<WeekItems> weekItemsList = listWeeks();
        dayAdapter = new DayAdapter(dayItemsList);
        weekAdapter = new WeekAdapter(weekItemsList);
        linearLayout = view.findViewById(R.id.linearLayoutDays);

        // user = this.getArguments().getParcelable("Profil");
        user = getArguments().getParcelable("Profil");
        Log.d("Le profil recupéré est celui de: ", user.getFirst());


        ViewPager2 viewPager2 = view.findViewById(R.id.pager2);

        viewPager2.setAdapter(dayAdapter);
        setupDaysIndicator();
        setCurrentDay(0);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
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
        view.findViewById(R.id.motivQuoteText).setOnClickListener(view2 -> {
            Food food = new Food("Nutella", 300);
            Intent intent = new Intent(view2.getContext(), Details.class);
            intent.putExtra("food", food);
            startActivity(intent);
        });

        motivation = (TextView) view.findViewById(R.id.motivQuoteText);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_planning, container, false);
    }

    private void listDays() {
        //List<DayItems> dayItemsList = new ArrayList<>();

        DayItems dayItems = new DayItems(Lundi);
        dayItems.addObserver(this);
        dayItems.setRepas("Nutella, pain de mie, pomme",
                "Poisson pané, lentilles",
                "clémentine, thé",
                "salade, tomate, mais");

        dayItems = new DayItems(Mardi);
        dayItems.addObserver(this);
        dayItems.setRepas("pom'pote",
                "burger sale",
                "tartine confiture fraise",
                "ptit salade");

        dayItems = new DayItems(Mercredi);
        dayItems.addObserver(this);
        dayItems.setRepas("oeufs",
                "wraps thon",
                "verre de lait",
                "tomate cerise");

        dayItems = new DayItems(Jeudi);
        dayItems.addObserver(this);
        dayItems.setRepas("Nutella, pain de mie, pomme",
                "Poisson pané, lentilles",
                "clémentine, thé",
                "salade, tomate, mais");
    }

    @Override
    public void update(Observable observable, Object o) {
        this.dayItemsList.add((DayItems) observable);
    }

    private List<WeekItems> listWeeks() {
        List<WeekItems> weekItemsList = new ArrayList<>();
        WeekItems weekItems = new WeekItems(Arrays.asList(2, 3, 4, 5));
        weekItemsList.add(weekItems);
        weekItems = new WeekItems(Arrays.asList(2, 9, 4, 15));
        weekItemsList.add(weekItems);
        weekItems = new WeekItems(Arrays.asList(37, 40, 18, 5));
        weekItemsList.add(weekItems);
        weekItems = new WeekItems(Arrays.asList(2, 80, 160, 550));
        weekItemsList.add(weekItems);
        return weekItemsList;
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
        int min, max;
        if (index == 0) {
            min = index;
            max = index + 2;
        } else if (index == dayAdapter.getItemCount() - 1) {
            min = index - 2;
            max = index;
        } else {
            min = index - 1;
            max = index + 1;
        }
        for (int i = min; i <= max; i++) {
            TextView textView = (TextView) linearLayout.getChildAt(i - min);
            textView.setText(dayAdapter.getDay(i).toString());
            textView.setTextColor(Color.parseColor((i == index) ? "#000000" : "#616161"));
        }
    }

    private void setCurrentWeek(int index) {
        int min, max;
        if (index == 0) {
            min = index;
            max = index + 2;
        } else if (index == weekAdapter.getItemCount() - 1) {
            min = index - 2;
            max = index;
        } else {
            min = index - 1;
            max = index + 1;
        }
        for (int i = min; i <= max; i++) {
            TextView textView = (TextView) linearLayout.getChildAt(i - min);
            textView.setText("Week " + i);
            textView.setTextSize(20);
            textView.setTextColor(Color.parseColor((i == index) ? "#000000" : "#616161"));
        }
    }
    public static void newMotivatiion(){
        DocumentReference docRef = db.collection("Motivation").document(String.valueOf(MainActivity.number));
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
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
            }
        });


    }
}
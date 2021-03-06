package com.example.ambrosia.parametres;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ambrosia.MainActivity;
import com.example.ambrosia.R;
import com.example.ambrosia.Users.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ParametreProfil extends Fragment {
    public Calendar calendar;
    private DatePicker datePicker;
    public User user = new User();
    AlertDialog.Builder builder;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RadioButton radioSexButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_profile_name, container, false);
        ImageView button = (ImageView) myView.findViewById(R.id.imageAnniv);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        TextView text = (TextView) myView.findViewById(R.id.anniversaire);
                        text.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                        dateSetListener, 2000, 00, 1);
                datePickerDialog.show();
                }
        });
        calendar= Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month  = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Log.d("CALENDRIER","so far not so good");
        TextView endOfRegime = (TextView) myView.findViewById(R.id.endRegime);
        endOfRegime.setText(calendar.getTime().toString());
        ImageView endRegime = (ImageView) myView.findViewById(R.id.finReg);
        Log.d("CALENDRIER","so far so good");
        endRegime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        setTime(year,monthOfYear,dayOfMonth);
                        endOfRegime.setText(calendar.getTime().toString());
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                        dateSetListener, year, month, day);
                datePickerDialog.show();

            }
        });
        Button calendarButton = (Button) myView.findViewById(R.id.calendar);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long time = calendar.getTime().getTime();
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra("beginTime",time);
                intent.putExtra("allDay", true);
                intent.putExtra("rule", "FREQ=YEARLY");
                intent.putExtra("endTime", time+60*60*1000);
                intent.putExtra("title", "Fin du r??gime");
                startActivity(intent);
            }
        });

        EditText lasts = (EditText) myView.findViewById(R.id.lastName);
        EditText firsts = (EditText) myView.findViewById(R.id.firstName);
        Button creationProfil = (Button) myView.findViewById(R.id.buttonValidation);
        EditText pseudos = (EditText) myView.findViewById(R.id.pseudo);
        EditText mdps = (EditText) myView.findViewById(R.id.mot_de_passe);
        EditText confirmMdps = (EditText) myView.findViewById(R.id.confirm_mdp);

        TextView borns = (TextView) myView.findViewById(R.id.anniversaire);
        EditText poidsActuel = (EditText) myView.findViewById(R.id.actual_weight);
        EditText poidsSouhait?? = (EditText) myView.findViewById(R.id.wanted_weight);
        Spinner programmes = (Spinner) myView.findViewById(R.id.spinner);
        Spinner allergies = (Spinner) myView.findViewById(R.id.spinnerAllergie);
        RadioGroup radioSexGroup = (RadioGroup) myView.findViewById(R.id.radioGroup);

        creationProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String last = lasts.getText().toString();
                String first = firsts.getText().toString();
                String pseudo = pseudos.getText().toString();
                String mdp = mdps.getText().toString();
                String confirmMdp = confirmMdps.getText().toString();
                String born = borns.getText().toString();
                Integer poidsActu = Integer.valueOf(poidsActuel.getText().toString());
                Integer poidsSou = Integer.valueOf(poidsSouhait??.getText().toString());
                String programme = programmes.getSelectedItem().toString();
                String allergie = allergies.getSelectedItem().toString();
                int selectedID = radioSexGroup.getCheckedRadioButtonId();
                radioSexButton = (RadioButton) myView.findViewById(selectedID);
                String sexe = radioSexButton.getText().toString();


                if (pseudo.length() >= 5 && last.length() >= 2 && first.length() >= 2 && mdp.length() >= 8 && confirmMdp.equals(mdp) && poidsActu.toString().length() > 1 && poidsSou.toString().length() >1) {
                    user.setPseudo(pseudo);
                    user.setFirst(first);
                    user.setLast(last);
                    user.setPassword(mdp);
                    user.setBorn(born);
                    user.setPoidsSouhait??(poidsSou);
                    user.setPoidsActuel(poidsActu);
                    user.setProgramme(programme);
                    user.setSexe(sexe);
                    user.setAllergie(allergie);
                    existe(pseudo);
                } else {
                    alerte("Veuillez v??rifier tous les champs");
                }


            }
        });

        return myView;
    }

    public void alerte(String msg){
        builder = new AlertDialog.Builder(getContext());
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle("ERREUR LORS DE L'INSCRIPTION");
        alert.show();
    }

    public void existe(String email){

        DocumentReference docRef = db.collection("user").document(email);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()){
                        alerte("Le pseudo est d??ja utilis??");
                    }else{
                        Log.d("bonjour", "No such document");
                        db.collection("user").document(user.getPseudo()).set(user);
                        Intent home = new Intent(getActivity(), MainActivity.class);
                        home.putExtra("Profil", (Parcelable) user);
                        startActivity(home);
                        getActivity().finish();
                    }
                }else{
                    Log.d("bonjour", "get failed with ", task.getException());
                }
            }
        });
    }
    public void setTime(int year, int month, int dayOfMonth){
        calendar = new GregorianCalendar(year, month, dayOfMonth);
    }


}

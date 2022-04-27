package com.example.ambrosia.Forum;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.ambrosia.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

public class Chat extends Fragment {
    private TextInputEditText input;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseFirestore fb =FirebaseFirestore.getInstance();
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        input = (TextInputEditText) view.findViewById(R.id.input);



        // Inflate the layout for this fragment
        Button fab =(Button)view.findViewById(R.id.send);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("TEST", "onClick: " + input);
                ChatMessage message=new ChatMessage(input.getText().toString(),"Nicolas");
                fb.collection("Chat").document(message.getMessageTime()).set(message);
            }
        });

        return view;
    }
}

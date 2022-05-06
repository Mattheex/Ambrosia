package com.example.ambrosia.Forum;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ambrosia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Chat extends Fragment {
    private final static String TAG= "MESSAGERIE";
    private TextInputEditText input;
    ArrayList<ChatMessage> messages = new ArrayList<>();
    ListView listView;
    FirebaseFirestore fb =FirebaseFirestore.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        listView = (ListView)view.findViewById(R.id.messages);
        UpdateMessages(view);



        input = (TextInputEditText) view.findViewById(R.id.input);



        // Inflate the layout for this fragment
        Button fab =(Button)view.findViewById(R.id.send);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("TEST", "onClick: " + input);
                ChatMessage message=new ChatMessage(input.getText().toString(),"Nicolas");
                fb.collection("Chat").document(message.getMessageTime()).set(message);
                input.setText("");
                messages.add(message);
                ArrayAdapter<ChatMessage> arrayAdapter
                        = new ArrayAdapter<ChatMessage>(view.getContext(), android.R.layout.simple_list_item_1 , messages);
                listView.setAdapter(arrayAdapter);
            }
        });

        return view;
    }

    public void UpdateMessages(View view){
        fb.collection("Chat")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document: task.getResult()){
                                messages.add(document.toObject(ChatMessage.class));
                            }

                            ArrayAdapter<ChatMessage> arrayAdapter
                                    = new ArrayAdapter<ChatMessage>(view.getContext(), android.R.layout.simple_list_item_1 , messages);
                            listView.setAdapter(arrayAdapter);
                        }
                        else{
                            Log.d(TAG,"Error getting documents : ", task.getException());
                        }


                    }
                });

    }

}

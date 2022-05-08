package com.example.ambrosia.Forums;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ambrosia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class Chat extends Fragment {
    private final static String TAG= "MESSAGERIE";
    private TextInputEditText input;
    ArrayList<ChatMessage> messages = new ArrayList<>();
    ListView listView;
    StorageReference storageRef;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fb =FirebaseFirestore.getInstance();
    ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Task signed = mAuth.signInAnonymously();
        storageRef = FirebaseStorage.getInstance().getReference();
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        listView = (ListView)view.findViewById(R.id.messages);
        UpdateMessages(view);
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            try {
                                final Uri imageUri = result.getData().getData();
                                final InputStream imageStream = view.getContext().getContentResolver().openInputStream(imageUri);
                                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                                //selectedImg.setImageBitmap(selectedImage);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                                Toast.makeText(view.getContext().getApplicationContext(), "Une erreur s'est produite",Toast.LENGTH_LONG).show();

                            }

                        }else {
                            Toast.makeText(view.getContext().getApplicationContext(),"Vous n'avez pas choisi d'image", Toast.LENGTH_LONG).show();

                        }
                    }
                });



        input = (TextInputEditText) view.findViewById(R.id.input);



        // Inflate the layout for this fragment
        Button fab =(Button)view.findViewById(R.id.send);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("TEST", "onClick: " + input);
                String image= "/DCIM/Camera/IMG_20210805_150101.jpg";
                ChatMessage message=new ChatMessage(input.getText().toString(),"Nicolas",image);
                try {
                    createImage(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                fb.collection("Chat").document(message.getMessageTime()).set(message);
                input.setText("");
                messages.add(message);
                ArrayAdapter<ChatMessage> arrayAdapter
                        = new ArrayAdapter<ChatMessage>(view.getContext(), android.R.layout.simple_list_item_1 , messages);
                listView.setAdapter(new ChatAdapter(view.getContext(),messages));
            }
        });

        return view;
    }

    public void createImage(String image) throws FileNotFoundException {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        Uri file = Uri.fromFile(new File(path + image));
        StorageReference monImage = storageRef.child("Images/retest");
        UploadTask uploadTask = monImage.putFile(file);
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
                            listView.setAdapter(new ChatAdapter(view.getContext(),messages));
                        }
                        else{
                            Log.d(TAG,"Error getting documents : ", task.getException());
                        }


                    }
                });

    }

}

package com.example.ambrosia.Forums;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ambrosia.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class Forum extends Fragment {
    StorageReference storageRef;
    static final int RESULT_LOAD_IMG = 1;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ImageView selectedImg;
    View view;
    ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //FirebaseUser user = mAuth.getCurrentUser();
        Task signed = mAuth.signInAnonymously();
        storageRef = FirebaseStorage.getInstance().getReference();
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_forum, container, false);
        Button button =(Button) view.findViewById(R.id.input);
        selectedImg = view.findViewById(R.id.imageTest);
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Bundle bundle =result.getData().getExtras();
                            final Bitmap selectedImage = (Bitmap) bundle.get("data"); //BitmapFactory.decodeStream(imageStream);
                            selectedImg.setImageBitmap(selectedImage);
                        }else {
                            Toast.makeText(view.getContext().getApplicationContext(),"Vous n'avez pas choisi d'image", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.frameLayout,new Chat())
                        .commit();

            }
        });

        Button testButton= (Button) view.findViewById(R.id.test);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultLauncher.launch(photoPickerIntent);
            }
        });




        return view;
    }


    public void createImage() throws FileNotFoundException {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        Uri file = Uri.fromFile(new File(path + "/DCIM/Camera/IMG_20210529_134318.jpg"));
        StorageReference monImage = storageRef.child("Images/retest");
        UploadTask uploadTask = monImage.putFile(file);

// Register observers to listen for when the download is done or if it fails
    }


}
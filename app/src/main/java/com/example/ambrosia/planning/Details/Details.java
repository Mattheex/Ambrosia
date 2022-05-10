package com.example.ambrosia.planning.Details;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ambrosia.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Details extends Fragment {
    private Food food;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();
        food = bundle.getParcelable("food");

        ImageView image = view.findViewById(R.id.imageView);
        FetchImageTask fetchImageTask = new FetchImageTask();
        fetchImageTask.execute(food.getImage(), image);

        TextView mealLabel = view.findViewById(R.id.mealLabel);
        mealLabel.setText(food.getName());

        IngredientsAdapter adapter = new IngredientsAdapter(view.getContext(), food.getIngredientList());
        ListView list = view.findViewById(R.id.recipeList);
        LinearLayout.LayoutParams mParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                food.getIngredientList().size() * 110);
        list.setLayoutParams(mParam);
        list.setMinimumHeight(food.getIngredientList().size() * 25);
        list.setAdapter(adapter);
    }

    private class FetchImageTask extends AsyncTask {
        private ImageView image;

        @Override
        protected Bitmap doInBackground(Object[] objects) {
            image = (ImageView) objects[1];
            Log.d("appDev",(String) objects[0]);
            try {
                return BitmapFactory.decodeStream(new URL((String) objects[0]).openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            image.setImageBitmap((Bitmap) o);
        }
    }
}
package com.example.ambrosia.planning.Details;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
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
    private ImageView image;

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

        image = view.findViewById(R.id.imageView);
        new LoadImageTask().execute(food.getImage());

        TextView mealLabel = view.findViewById(R.id.mealLabel);
        mealLabel.setText(food.getName());

        Log.d("appDev", String.valueOf(food.getIngredientList()));
        IngredientsAdapter adapter = new IngredientsAdapter(view.getContext(), food.getIngredientList());
        ListView list = view.findViewById(R.id.recipeList);
        LinearLayout.LayoutParams mParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                food.getIngredientList().size() * 110);
        list.setLayoutParams(mParam);
        list.setAdapter(adapter);

        NutrientsAdapter adapter2 = new NutrientsAdapter(view.getContext(), food.getNutrients());
        list = view.findViewById(R.id.nutritionList);
        mParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                food.getIngredientList().size() * 110);
        list.setLayoutParams(mParam);
        list.setAdapter(adapter2);

        TextView source = view.findViewById(R.id.source);
        Spanned Text = Html.fromHtml("La source : <a href='" + food.getUrlFood() + "'>" +
                food.getSource() + "</a>");
        source.setMovementMethod(LinkMovementMethod.getInstance());
        source.setText(Text);
    }

    public class LoadImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... args) {
            try {
                return BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            image.setImageBitmap(bitmap);
        }
    }


}
package com.example.ambrosia;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class Forum extends Fragment {
    OkHttpClient client = new OkHttpClient();
    String url = "https://www.foodrepo.org/api/v3/products?excludes=images%2Cnutrients&barcodes=7610848337010%2C7610849657797";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forum, container, false);

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        /*try {
            this.get("https://www.foodrepo.org/api/v3/products?excludes=images%2Cnutrients&barcodes=7610848337010%2C7610849657797");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        OkHttpHandler okHttpHandler= new OkHttpHandler();
        okHttpHandler.execute(url);
    }

    private void get(String url2) throws IOException {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url2).newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .header("Accept", "application/json")
                .header("Authorization", "0773fa9ce6eafe47256f9329542c6e32")
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        Log.d("reponse",response.body().string());
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
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Log.d("reponse", String.valueOf(o));
        }
    }
}
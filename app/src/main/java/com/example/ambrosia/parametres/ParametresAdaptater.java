package com.example.ambrosia.parametres;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ambrosia.R;

public class ParametresAdaptater extends RecyclerView.Adapter<ParametresAdaptater.ParametreViewHolder> {
    @NonNull
    @Override
    public ParametresAdaptater.ParametreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ParametreViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.fragment_profile_name, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ParametresAdaptater.ParametreViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ParametreViewHolder extends RecyclerView.ViewHolder {
        private final TextView firstAnswer, secondAnswer, thirdAnswer;

        public ParametreViewHolder(@NonNull View itemView) {
            super(itemView);
            firstAnswer = itemView.findViewById(R.id.premierChamp);
            secondAnswer = itemView.findViewById(R.id.deuxiemeChamp);
            thirdAnswer = itemView.findViewById(R.id.troisiemeChamp);
        }
    }
}

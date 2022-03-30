package com.example.ambrosia.planning.Week;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ambrosia.R;

import java.util.ArrayList;
import java.util.List;

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.WeekViewHolder> {

    private final List<WeekItems> weekList;

    public WeekAdapter(List<WeekItems> weekList) {
        this.weekList = weekList;
    }

    @NonNull
    @Override
    public WeekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WeekViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.fragment_week, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull WeekViewHolder holder, int position) {
        holder.setDayData(weekList.get(position));
    }

    @Override
    public int getItemCount() {
        return weekList.size();
    }

    class WeekViewHolder extends RecyclerView.ViewHolder {

        private final List<TextView> textViewList = new ArrayList<>();

        public WeekViewHolder(@NonNull View itemView) {
            super(itemView);
            //Log.d("error",itemView.findViewById(R.id.dayMardi).toString());
            textViewList.add(itemView.findViewById(R.id.kcal1));
            textViewList.add(itemView.findViewById(R.id.kcal2));
            textViewList.add(itemView.findViewById(R.id.kcal3));
        }

        void setDayData(WeekItems weekData) {
            for (int i = 0; i < textViewList.size(); i++) {
                textViewList.get(i).setText("kcal : " + weekData.getKcals().get(i));
            }
        }
    }
}

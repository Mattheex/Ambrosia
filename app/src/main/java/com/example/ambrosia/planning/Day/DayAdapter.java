package com.example.ambrosia.planning.Day;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ambrosia.R;
import com.example.ambrosia.planning.DayEnum;
import com.example.ambrosia.planning.Planning;

import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder> {

    private final List<DayItems> dayList;

    public DayAdapter(List<DayItems> dayList) {
        this.dayList = dayList;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DayViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.fragment_list, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        holder.setDayData(dayList.get(position));
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    public DayEnum getDay(int id) {
        return dayList.get(id).getDay();
    }

    class DayViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            this.context = itemView.getContext();
        }

        void setDayData(DayItems dayData) {
            MealsAdapter adapter = new MealsAdapter(context, dayData);
            ListView list = itemView.findViewById(R.id.listView);
            list.setAdapter(adapter);
        }
    }
}

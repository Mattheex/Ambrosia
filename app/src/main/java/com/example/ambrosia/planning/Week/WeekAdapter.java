package com.example.ambrosia.planning.Week;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ambrosia.R;
import com.example.ambrosia.planning.Planning;

import java.util.List;

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.WeekViewHolder> {

    private final List<WeekItems> weekList;
    private Planning planning;

    public WeekAdapter(List<WeekItems> weekList, Planning planning) {
        this.weekList = weekList;
        this.planning = planning;
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
        holder.setDayData(weekList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return weekList.size();
    }

    class WeekViewHolder extends RecyclerView.ViewHolder {

        private Context context;

        public WeekViewHolder(@NonNull View itemView) {
            super(itemView);
            this.context = itemView.getContext();
        }

        void setDayData(WeekItems weekData, Integer weekPosition) {
            DaysAdapter adapter = new DaysAdapter(context, weekData, weekPosition, planning);
            ListView list = itemView.findViewById(R.id.listView);
            list.setAdapter(adapter);
        }
    }
}

package com.example.ambrosia.planning.Day;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ambrosia.R;

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
                R.layout.fragment_day, parent, false
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

    public DayEnum getDay(int id){return dayList.get(id).getDay();}

    class DayViewHolder extends RecyclerView.ViewHolder {

        private final TextView dejText, midiText, goutText, dinerText;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            dejText = itemView.findViewById(R.id.dejeuner);
            midiText = itemView.findViewById(R.id.midi);
            goutText = itemView.findViewById(R.id.gouter);
            dinerText = itemView.findViewById(R.id.diner);
        }

        void setDayData(DayItems dayData) {
            dejText.setText(dayData.getDej());
            midiText.setText(dayData.getMidi());
            goutText.setText(dayData.getGouter());
            dinerText.setText(dayData.getDiner());
        }
    }
}

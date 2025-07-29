package com.example.weighttracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.Date;

public class WeightAdapter
        extends ListAdapter<WeightEntry, WeightAdapter.VH> {

    public WeightAdapter() { super(DIFF); }


    static class VH extends RecyclerView.ViewHolder {
        final TextView tv;
        VH(View v) {
            super(v);
            tv = v.findViewById(R.id.tv_weight_row);   //   eigene ID
        }
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_weight, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        WeightEntry e = getItem(pos);
        DateFormat df = DateFormat.getDateInstance();
        h.tv.setText(df.format(new Date(e.timestamp)) + "  –  "
                + e.weightKg + " kg");
    }

    private static final DiffUtil.ItemCallback<WeightEntry> DIFF =
            new DiffUtil.ItemCallback<>() {
                public boolean areItemsTheSame(WeightEntry a, WeightEntry b){
                    return a.id == b.id;
                }
                public boolean areContentsTheSame(WeightEntry a, WeightEntry b){
                    return a.weightKg == b.weightKg &&
                            a.timestamp == b.timestamp;
                }
            };
}

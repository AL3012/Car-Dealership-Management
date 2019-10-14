package com.example.cardealershipmanagement.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cardealershipmanagement.Database.CarModel;
import com.example.cardealershipmanagement.R;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<CarModel> arrayList;

    public CarAdapter(ArrayList<CarModel> ArrayList)
    {
        this.arrayList = ArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View item = layoutInflater.inflate(R.layout.recycler_cell, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder.makeTv.setText(arrayList.get(i).getMake());
        ViewHolder.modelTv.setText(arrayList.get(i).getModel());
        ViewHolder.vinTv.setText(arrayList.get(i).getVin());
        ViewHolder.colorTv.setText(arrayList.get(i).getColor());
        ViewHolder.yearTv.setText(arrayList.get(i).getYear());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        static TextView makeTv, modelTv, colorTv, vinTv, yearTv;
        static Button detailsBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            makeTv = itemView.findViewById(R.id.Make);
            modelTv = itemView.findViewById(R.id.Model);
            vinTv = itemView.findViewById(R.id.vin);
            yearTv = itemView.findViewById(R.id.yearTV);
            colorTv = itemView.findViewById(R.id.color);
            detailsBtn = itemView.findViewById(R.id.detailsBtn);
        }
    }
}

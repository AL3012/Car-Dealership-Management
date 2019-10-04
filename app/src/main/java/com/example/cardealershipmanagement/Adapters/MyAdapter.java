package com.example.cardealershipmanagement.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cardealershipmanagement.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> arrayList;

    public MyAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View item = layoutInflater.inflate(R.layout.recycler_cell, viewGroup, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        /*ViewHolder.makeTv.setText(arrayList.get(i).getMake());
        ViewHolder.modelTv.setText(arrayList.get(i).getModel());
        ViewHolder.vinTv.setText(arrayList.get(i).getVin());
        ViewHolder.ownerTv.setText(arrayList.get(i).getOwner());
        ViewHolder.colorTv.setText(arrayList.get(i).getColor());
        ViewHolder.detailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this,"Done", Toast.LENGTH_LONG).show();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        static TextView makeTv, modelTv, colorTv, vinTv, ownerTv;
        static Button detailsBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            makeTv = itemView.findViewById(R.id.Make);
            modelTv = itemView.findViewById(R.id.Model);
            vinTv = itemView.findViewById(R.id.vin);
            ownerTv = itemView.findViewById(R.id.owner);
            colorTv = itemView.findViewById(R.id.color);
            detailsBtn = itemView.findViewById(R.id.detailsBtn);
        }
    }
}

package com.example.surfclient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surfclient.R;
import com.example.surfclient.model.Spot;

import java.util.List;

public class SpotAdapter extends RecyclerView.Adapter<SpotHolder> {
    private List<Spot> spotList;

    public SpotAdapter(List<Spot> spotList) {
        this.spotList = spotList;
    }

    @NonNull
    @Override
    public SpotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_spot_item, parent, false);
        return new SpotHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpotHolder holder, int position) {
        Spot spot = spotList.get(position);
        holder.destination.setText(spot.getDestination());
        holder.address.setText(spot.getAddress());
        holder.difficulty_level.setText(String.valueOf(spot.getDifficulty_level()));
        holder.photo_url.setText(spot.getPhoto_url());
        holder.surfBreak.setText(spot.getSurfBreak().getDisplayName());
    }

    @Override
    public int getItemCount() {
        return spotList.size();
    }
}

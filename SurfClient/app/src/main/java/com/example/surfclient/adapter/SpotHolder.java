package com.example.surfclient.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surfclient.R;

public class SpotHolder extends RecyclerView.ViewHolder{

    TextView destination, address, difficulty_level, photo_url, surfBreak;
    public SpotHolder(@NonNull View itemView) {
        super(itemView);
        destination = itemView.findViewById(R.id.spotListItem_destination);
        address = itemView.findViewById(R.id.spotListItem_address);
        difficulty_level= itemView.findViewById(R.id.spotListItem_difficulty_level);
        photo_url = itemView.findViewById(R.id.spotListItem_photo_url);
        surfBreak = itemView.findViewById(R.id.spotListItem_surf_break);

    }
}

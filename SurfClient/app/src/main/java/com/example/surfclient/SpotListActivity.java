package com.example.surfclient;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surfclient.adapter.SpotAdapter;
import com.example.surfclient.model.Spot;
import com.example.surfclient.retrofit.RetrofitService;
import com.example.surfclient.retrofit.SpotApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpotListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spot_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.spotList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RetrofitService retrofitService = new RetrofitService();
        SpotApi spotApi = retrofitService.getRetrofit().create(SpotApi.class);

        FloatingActionButton floatingActionButton = findViewById(R.id.spotList_fab);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, SpotForm.class);
            startActivity(intent);
        });

        loadSpotsAsync(spotApi);
    }

    private void loadSpotsAsync(SpotApi spotApi) {
        CompletableFuture.runAsync(() -> {
            try {
                Response<List<Spot>> response = spotApi.getAllSpots().execute();
                if (response.isSuccessful() && response.body() != null) {
                    List<Spot> spots = response.body();
                    mainHandler.post( () -> populateListView(spots));
                } else {
                    mainHandler.post( () ->
                            Toast.makeText(SpotListActivity.this, "Failed to load spots " + response.message(), Toast.LENGTH_SHORT).show());
                }
            } catch (Exception e) {
                mainHandler.post(() -> {
                    Toast.makeText(SpotListActivity.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Logger.getLogger(SpotListActivity.class.getName()).log(Level.SEVERE, "error occured", e);
                });
            }
        }, executorService);
    }

    private void populateListView(List<Spot> spotList) {
        SpotAdapter spotAdapter = new SpotAdapter(spotList);
        recyclerView.setAdapter(spotAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
package com.example.surfclient;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.surfclient.model.Spot;
import com.example.surfclient.model.SurfBreak;
import com.example.surfclient.retrofit.RetrofitService;
import com.example.surfclient.retrofit.SpotApi;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Response;

public class SpotForm extends AppCompatActivity {
    // Execute les tâches en arrière plan
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText inputEditDestination = findViewById(R.id.form_textFieldDestination);
        TextInputEditText inputEditAddress = findViewById(R.id.form_textFieldAddress);
        TextInputEditText inputEditDifficultyLevel = findViewById(R.id.form_textFieldDifficultyLevel);
        TextInputEditText inputEditPhotoUrl = findViewById(R.id.form_textFieldPhotoUrl);
        TextInputEditText inputEditSurfBreak = findViewById(R.id.form_textFieldSurfBreak);
        MaterialButton buttonSave = findViewById(R.id.form_buttonSave);

        RetrofitService retrofitService = new RetrofitService();
        SpotApi spotApi = retrofitService.getRetrofit().create(SpotApi.class);

        buttonSave.setOnClickListener(view -> {
            String destination = String.valueOf(inputEditDestination.getText());
            String address = String.valueOf(inputEditAddress.getText());
            String difficultyLevel = String.valueOf(inputEditDifficultyLevel.getText());
            String photoUrl = String.valueOf(inputEditPhotoUrl.getText());
            String surfBreak = String.valueOf(inputEditSurfBreak.getText());

            Spot spot = new Spot();
            spot.setDestination(destination);
            spot.setAddress(address);
            spot.setDifficulty_level(Integer.parseInt(difficultyLevel));
            spot.setPhoto_url(photoUrl);
            spot.setSurfBreak(SurfBreak.valueOf(surfBreak));

            // appel api async
            saveSpotAsync(spotApi, spot);
        });

    }

    private void saveSpotAsync(SpotApi spotApi, Spot spot) {
        CompletableFuture.runAsync(() -> {
            try {
                // Exécution fe l'appel réseau sur thread d'arrière plan
                Response<Spot> response = spotApi.save(spot).execute();
                if (response.isSuccessful()){
                    //MàJ de l'UI sur thread principal
                    mainHandler.post(() ->
                            Toast.makeText(SpotForm.this, "Save successful", Toast.LENGTH_SHORT).show()
                    );
                } else {
                    mainHandler.post(()->
                            Toast.makeText(SpotForm.this, "Save failed " + response.message(), Toast.LENGTH_SHORT).show()
                   ) ;
                }
            } catch (Exception e){
                mainHandler.post(()-> {
                    Toast.makeText(SpotForm.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Logger.getLogger(SpotForm.class.getName()).log(Level.SEVERE, "Error occurred", e);
                });
            }
        }, executorService);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //Arrêt de l'executor pour éviter fuites de mémoire
        executorService.shutdown();
    }
}
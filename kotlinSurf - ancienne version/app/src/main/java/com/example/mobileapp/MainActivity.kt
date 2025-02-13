package com.example.mobileapp

import SpotRecord
import android.os.Bundle  // Importation de la classe Bundle pour gérer l'état de l'activité
import android.util.Log
import androidx.appcompat.app.AppCompatActivity // Importation de la classe de base pour les activités Android
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.mobileapp.databinding.ActivityMainBinding // Liaison avec le fichier XML activity_main.xml
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.mobileapp.MyApi
import com.example.mobileapp.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


// Définition de l'activité principale
class MainActivity : AppCompatActivity() {

    private val TAG: String = "CHECK_RESPONSE"

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    // Méthode appelée lors de la création de l'activité
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialisation du View Binding et chargement de l'interface activity_main.xlm.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuration de la navigation
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Appel de la fonction avec coroutine
        lifecycleScope.launch {
            getAllRecords()
        }
    }

    private suspend fun getAllRecords() {
        Log.d(TAG, "get all records called")

        // Passage sur le thread IO pour éviter le blocage du thread principal
        withContext(Dispatchers.IO) {

            val api = RetrofitClient.instance.create(MyApi::class.java)

            api.getRecords().enqueue(object : Callback<SpotRecord> {

                override fun onResponse(call: Call<SpotRecord>, response: Response<SpotRecord>) {
                    if (response.isSuccessful) {
                        response.body()?.let { spotRecord ->
                            Log.d(TAG, "${spotRecord.records.size} spots récupérés !")
                            val bundle = Bundle().apply {
                                putParcelable("spotRecord", spotRecord)
                            }
                            // Passage sur le thread principal pour la navigation
                            lifecycleScope.launch(Dispatchers.Main) {
                                navController.navigate(R.id.HomeFragment, bundle)
                            }

                        } ?: run {
                            Log.e(TAG, "Response body is null")
                        }
                    } else {
                        Log.e(TAG, "Error Response: ${response.code()}")
                        Log.e(TAG, "Error Body: ${response.errorBody()?.string()}")
                        Log.e(TAG, "Message: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<SpotRecord>, t: Throwable) {
                    if (t is java.net.UnknownHostException) {
                        Log.e(TAG, "Problème de connexion Internet : ${t.message}")
                    } else {
                        Log.e(TAG, "Erreur inattendue : ${t.message}")
                    }
                }
            })
        }
    }
}


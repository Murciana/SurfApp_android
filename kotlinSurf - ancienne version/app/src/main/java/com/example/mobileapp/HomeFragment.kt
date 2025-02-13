package com.example.mobileapp

import SpotRecord
import android.annotation.SuppressLint
import android.os.Bundle // Pour gérer l'état du fragment
import android.util.Log
import android.view.LayoutInflater // Pour "gonfler" l'interface XML dans le code Kotlin
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment // Classe de base pour un fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager // Gestionnaire de mise en page verticale
import androidx.recyclerview.widget.RecyclerView // Composant pour afficher une liste

// Définition du fragment HomeFragment qui contient un RecyclerView
class HomeFragment : Fragment() {

    private var spotRecord: SpotRecord? = null

    // Méthode appelée lors de la création de la vue du fragment
    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Charger l'interface utilisateur depuis fragment_home.xml
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        Log.d("HomeFragment", "onCreateView called")
        Log.d("ceci est view", view.toString())

        // Récupérer les données depuis le bundle
        spotRecord = arguments?.getParcelable("spotRecord", SpotRecord::class.java)
        Log.d("voici le spotrecord :", spotRecord.toString())
        Log.d("HomeFragment", "spotRecord: ${spotRecord?.records?.size ?: "null"}")

        // Configuration du RecyclerView via Récupération du RecyclerView depuis le fichier XML
        val recyclerView = view.findViewById<RecyclerView>(R.id.spotRecyclerView)

        // Configuration du layoutManager pour afficher les éléments de manière verticale
        recyclerView.layoutManager = LinearLayoutManager(context)

        spotRecord?.let { data ->
        recyclerView.adapter = SpotAdapter(data) { spot ->
            Log.d("HomeFragment", "Setting adapter with ${data.records.size} spots")
            Log.d("HomeFragment", "Spot sélectionné : ${spot.surfBreak}")
            // Création d'un bundle pour passer des données au prochain fragment
            val bundle = Bundle().apply {
                putString("name", spot.destination.toString())  // Passe le nom du spot sélectionné
                putString("location", spot.address) // Passe la ville du spot sélectionné
                putString("image", spot.photos.toString()) // Passe l'image du spot sélectionné
                putString("surfBreak", spot.surfBreak.toString())  // Ajout du type de surf break
                putInt("difficultyLevel", spot.difficultyLevel) // Ajout du niveau de difficulté
            }
            Log.d("voici le bundle :", bundle.toString())
            val navController = requireActivity().findNavController(R.id.nav_host_fragment)
            navController.navigate(R.id.action_HomeFragment_to_DetailsFragment, bundle)

            // Pour naviguer vers le fragment de détails avec passage du bundle
            //findNavController().navigate(R.id.action_HomeFragment_to_DetailsFragment, bundle)
        }
        }
        // Retourne la vue chargée
        return view
    }
}

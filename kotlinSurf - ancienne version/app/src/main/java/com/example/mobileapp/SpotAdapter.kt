package com.example.mobileapp

import Spot
import SpotRecord
import android.os.Bundle
import android.view.LayoutInflater // Pour "gonfler" le layout XML
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import com.bumptech.glide.Glide

// Adaptateur pour gérer la liste des spots dans le RecyclerView
class SpotAdapter(private val spots: SpotRecord, private val onClick: (Spot) -> Unit) :
    RecyclerView.Adapter<SpotAdapter.SpotViewHolder>() {

    // ViewHolder : Gère la vue individuelle d'un élément de la liste
    //class interne qui represente chaque element individuel de la liste.
    //elle stocke les vues de chaque element pour eviter de rechercher ces vues a chaque iteration.
    //ces donnees sont recuperees dans item_spot.xml avec findViewById et stockees dans des variables.
    class SpotViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val locationTextView: TextView = view.findViewById(R.id.locationTextView)
        val surfBreakTextView: TextView = view.findViewById(R.id.surfBreakTextView)
        val difficultyTextView: TextView = view.findViewById(R.id.difficultyTextView)
        val button: Button = view.findViewById(R.id.button)
    }

    // Créer une nouvelle vue (appelée uniquement quand nécessaire)
    //cette ligne charge item_spot.xml(la mise en page) dans une vue retournee avec SpotViewHolder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_spot, parent, false)
        return SpotViewHolder(view)
    }

    //methode appelee chaque fois qu'un element de la liste doit etre affiche.
    //elle prend SpotViewHolder et l'index [position] de l'element a afficher.
    //on assigne image, name et location a leur vue correspondante
    override fun onBindViewHolder(holder: SpotViewHolder, position: Int) {
        val spot = spots.records[position]
        holder.nameTextView.text = spot.destination
        holder.locationTextView.text = spot.address
        holder.difficultyTextView.text = View.GONE.toString()
        holder.surfBreakTextView.text = View.GONE.toString()


        val firstPhotoUrl = spot.photos
        // Charger l'image avec Glide
        Glide.with(holder.itemView.context)
            .load(firstPhotoUrl)
            .placeholder(R.drawable.city) // Image par défaut
            .into(holder.imageView)

        // Gestion du clic sur le bouton pour afficher les détails
        holder.button.setOnClickListener {
            onClick(spot)
        }
    }

    //retourne le nombre d'elements dans la liste (ici la taille de la liste)
    override fun getItemCount(): Int = spots.records.size
}

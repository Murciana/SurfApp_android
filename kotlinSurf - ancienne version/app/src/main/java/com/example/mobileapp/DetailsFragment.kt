package com.example.mobileapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    // Méthode qui crée et retourne la vue du fragment.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        // Récupération des données passées via le bundle
        val name = arguments?.getString("name")
        val location = arguments?.getString("location")
        val image = arguments?.getString("image")
        val surfBreak = arguments?.getString("surfBreak")
        val difficultyLevel = arguments?.getInt("difficultyLevel")

        Glide.with(view.context)
            .load(image) // Ici, image est une URL de type String
            .placeholder(R.drawable.city)
            .into(view.findViewById(R.id.imageView))

        // Mise à jour de l'interface avec les données reçues
        view.findViewById<TextView>(R.id.nameTextView).text = name
        view.findViewById<TextView>(R.id.locationTextView).text = location
        view.findViewById<ImageView>(R.id.imageView)
        view.findViewById<TextView>(R.id.surfBreakTextView).text = surfBreak
        view.findViewById<TextView>(R.id.difficultyTextView).text = "Difficulty Level: ${ difficultyLevel.toString() }"

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
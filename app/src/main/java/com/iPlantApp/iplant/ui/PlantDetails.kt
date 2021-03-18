package com.iPlantApp.iplant.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.iPlantApp.iplant.R
import com.iPlantApp.iplant.ui.gallery.MainViewModel
import com.iPlantApp.iplant.ui.gallery.Plants
import kotlinx.android.synthetic.main.fragment_plant_details.*


class PlantDetails : Fragment() {
    private lateinit var plant: Plants
    var nameCustom = ""
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            if(it.getParcelable<Plants>("plant") != null)plant = it.getParcelable<Plants>("plant")!!
            if(it.getString("name") != null) nameCustom  = it.getString("name")!!
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plant_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(plant.foto).into(img_plant)
        nombre_planta.text = plant.nombre
        desc_plant.text = plant.duracion
        descripcionplant.text = plant.desc
        btnadd.setOnClickListener{

            val fm = requireActivity().supportFragmentManager
            val dialogFragment = dialog() // my custom FargmentDialog
            val bundle = Bundle()
            bundle.putParcelable("plant", plant)
            dialogFragment.arguments = bundle
            dialogFragment.show(fm, "Dialogo")



            //var b = dialog()
            //activity?.supportFragmentManager?.let { it1 -> b.show(it1, "si") }
            //findNavController().navigate(R.id.dialog)
            //viewModel.addFav(nameCustom, plant, FirebaseAuth.getInstance().currentUser!!.uid)
        }
    }



}
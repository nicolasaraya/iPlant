package com.iPlantApp.iplant.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.iPlantApp.iplant.R
import com.iPlantApp.iplant.ui.gallery.MainViewModel
import com.iPlantApp.iplant.ui.gallery.Plants
import kotlinx.android.synthetic.main.fragment_details_favs.*
import kotlinx.android.synthetic.main.fragment_plant_details.desc_plant
import kotlinx.android.synthetic.main.fragment_plant_details.descripcionplant
import kotlinx.android.synthetic.main.fragment_plant_details.img_plant
import kotlinx.android.synthetic.main.fragment_plant_details.nombre_planta


class detailsFavs : Fragment() {

    private lateinit var plant : Plants
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.actionBar?.title = "Test"
        requireArguments().let {
            if(it.getParcelable<Plants>("plant") != null)plant = it.getParcelable<Plants>("plant")!!

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.actionBar?.title = "Detalles";



        return inflater.inflate(R.layout.fragment_details_favs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(plant.foto).into(img_plant)
        nombre_planta.text = plant.nombre
        alias.text = plant.customname
        desc_plant.text = plant.duracion
        descripcionplant.text = plant.desc
        dateplant.text = plant.fecha.toDate().toString()
        btndelete.setOnClickListener {
            viewModel.removeFav(plant.id!!)
            findNavController().navigate(R.id.action_detailsFavs_to_nav_home)
        }

    }

}
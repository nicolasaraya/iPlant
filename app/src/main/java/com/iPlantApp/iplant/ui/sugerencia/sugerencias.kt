package com.iPlantApp.iplant.ui.sugerencia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.iPlantApp.iplant.R
import com.iPlantApp.iplant.ui.Adapter
import com.iPlantApp.iplant.ui.gallery.GalleryViewModel
import com.iPlantApp.iplant.ui.gallery.MainViewModel
import com.iPlantApp.iplant.ui.gallery.Plants
import com.iPlantApp.iplant.ui.plant_p.Preguntas
import kotlinx.android.synthetic.main.fragment_gallery.*

// TODO: Rename parameter arguments, choose names that match


/**
 * A simple [Fragment] subclass.
 * Use the [sugerencias.newInstance] factory method to
 * create an instance of this fragment.
 */
class sugerencias : Fragment() , Adapter.plantClickListener {

    private lateinit var galleryViewModel: GalleryViewModel
    private lateinit var adap : Adapter
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java)}
    private lateinit  var respuesta_pregunta1: Preguntas
    private lateinit  var respuesta_pregunta2: Preguntas
    private lateinit  var respuesta_pregunta3: Preguntas
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            if(it.getParcelable<Preguntas>("respuesta1") != null)respuesta_pregunta1 = it.getParcelable<Preguntas>("respuesta1")!!
            if(it.getParcelable<Preguntas>("respuesta2") != null)respuesta_pregunta2= it.getParcelable<Preguntas>("respuesta2")!!
            if(it.getParcelable<Preguntas>("respuesta3") != null)respuesta_pregunta3 = it.getParcelable<Preguntas>("respuesta3")!!

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sugerencias, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adap = Adapter(context, this, 0)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adap
        observeData()



    }

    override fun plantClick(plant: Plants) {
        val bundle = Bundle()
        bundle.putParcelable("plant", plant)
        findNavController().navigate(R.id.action_sugerencias_to_plantDetails, bundle)
    }

    fun observeData(){

        viewModel.fetchFavsPlantsDataSugerencias( respuesta_pregunta1, respuesta_pregunta2, respuesta_pregunta3).observe(viewLifecycleOwner, Observer{

            adap.setListData(it)
            adap.notifyDataSetChanged()
        })
    }


}
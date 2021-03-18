package com.iPlantApp.iplant.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.iPlantApp.iplant.R
import com.iPlantApp.iplant.ui.Adapter
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.fragment_gallery.recyclerView

class GalleryFragment : Fragment(), Adapter.plantClickListener {

    private lateinit var galleryViewModel: GalleryViewModel
    private lateinit var adap : Adapter
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java)}

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        galleryViewModel =
                ViewModelProvider(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        return root
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
        findNavController().navigate(R.id.action_nav_gallery_to_plantDetails, bundle)
    }

    fun observeData(){
        viewModel.fetchPlantsData().observe(viewLifecycleOwner, Observer{

            adap.setListData(it)
            adap.notifyDataSetChanged()
            if(adap.itemCount == 0 ) {
                textovacio2.text = "No hay plantas disponibles"
                textovacio2.visibility = View.VISIBLE
            }
            else {
                textovacio2.text = "Cargando"
                textovacio2.visibility = View.GONE
            }
        })
    }
}

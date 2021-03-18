package com.iPlantApp.iplant.ui.home

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
import com.iPlantApp.iplant.ui.gallery.MainViewModel
import com.iPlantApp.iplant.ui.gallery.Plants
import kotlinx.android.synthetic.main.fragment_gallery.recyclerView
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), Adapter.plantClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adap: Adapter
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adap = Adapter(context, this, 1)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adap
        observeData()
    }

    override fun plantClick(plant: Plants) {
        val bundle = Bundle()
        bundle.putParcelable("plant", plant)
        findNavController().navigate(R.id.action_nav_home_to_detailsFavs, bundle) //CORREGIR
    }

    fun observeData() {

        viewModel.fetchFavsPlantsData().observe(viewLifecycleOwner, Observer {
            adap.setListData(it)
            adap.notifyDataSetChanged()
            if(adap.itemCount == 0 ) {
                textovacio.text = "Añade plantas"
                textovacio.visibility = View.VISIBLE
            }
            else {
                textovacio.text = "Cargando"
                textovacio.visibility = View.GONE
            }
        })
    }

}

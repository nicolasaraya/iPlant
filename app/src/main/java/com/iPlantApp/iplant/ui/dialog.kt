package com.iPlantApp.iplant.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.iPlantApp.iplant.R
import com.iPlantApp.iplant.ui.gallery.MainViewModel
import com.iPlantApp.iplant.ui.gallery.Plants
import kotlinx.android.synthetic.main.fragment_dialog.*

class dialog : BottomSheetDialogFragment() {
    private lateinit var plant: Plants
    var nameCustom = ""
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            if(it.getParcelable<Plants>("plant") != null)plant = it.getParcelable<Plants>("plant")!!
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        guardar.setOnClickListener{
            val txt = write as EditText
            nameCustom = txt.text.toString()
            viewModel.addFav(nameCustom, plant, FirebaseAuth.getInstance().currentUser!!.uid)
            dismiss()

        }
    }


}
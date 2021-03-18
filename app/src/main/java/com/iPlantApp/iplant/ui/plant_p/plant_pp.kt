package com.iPlantApp.iplant.ui.plant_p

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.iPlantApp.iplant.R
import com.iPlantApp.iplant.ui.gallery.MainViewModel
import com.iPlantApp.iplant.ui.slideshow.SlideshowViewModel
import kotlinx.android.synthetic.main.plant_pp_fragment.*

class plant_pp : Fragment() {



    private lateinit var slideshowViewModel: SlideshowViewModel
    private var clicks: Int = 1
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java)}
    private lateinit var preg:  MutableLiveData<MutableList<Preguntas>>
    private var respuesta_pregunta1 = Preguntas("respuesta1","False","False","False","False","False")
    private var respuesta_pregunta2 = Preguntas("respuesta2","False","False","False","False","False")
    private var respuesta_pregunta3 = Preguntas("respuesta3","False","False","False","False","False")
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.plant_pp_fragment, container, false)
        return root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preg = viewModel.questions(clicks,p1,p2,p3,p4,p5,titulo, textView3)!!
        if(clicks == 1){
            loading.visibility = View.INVISIBLE
            ly.visibility = View.VISIBLE
            seguir.visibility = View.VISIBLE
        }
        var aux: Preguntas


        seguir.setOnClickListener{
            if(clicks ==1) aux = respuesta_pregunta1
            else if (clicks ==2) aux = respuesta_pregunta2
            else aux = respuesta_pregunta3
            if (p1.isChecked()) {
                p1.setChecked(false)
                aux.alternativa1 = "True"
            }
            if (p2.isChecked()){
                p2.setChecked(false)
                aux.alternativa2 = "True"
            }
            if (p3.isChecked()){
                p3.setChecked(false)
                aux.alternativa3 = "True"
            }
            if (p4.isChecked()){
                p4.setChecked(false)
                aux.alternativa4 = "True"
            }
            if (p5.isChecked()){
                p5.setChecked(false)
                aux.alternativa5 = "True"
            }
            if(clicks ==1) respuesta_pregunta1 = aux
            else if (clicks ==2) respuesta_pregunta2 = aux
            else respuesta_pregunta3 = aux
            Log.d("clicks",clicks.toString())
            Log.d("respuesta 1", respuesta_pregunta1.toString())
            Log.d("respuesta 2", respuesta_pregunta2.toString())
            Log.d("respuesta 3", respuesta_pregunta3.toString())
            clicks++
            if (clicks == 4) {
                var k = Bundle()
                k.putParcelable("respuesta1",respuesta_pregunta1)
                k.putParcelable("respuesta2",respuesta_pregunta2)
                k.putParcelable("respuesta3",respuesta_pregunta3)
                findNavController().navigate(R.id.action_plant_pp_to_sugerencias, k)
                clicks = 1
                respuesta_pregunta1 = Preguntas("respuesta1","False","False","False","False","False")
                respuesta_pregunta2 = Preguntas("respuesta2","False","False","False","False","False")
                respuesta_pregunta3 = Preguntas("respuesta3","False","False","False","False","False")
                loading.visibility = View.VISIBLE
                ly.visibility = View.INVISIBLE
                seguir.visibility = View.INVISIBLE

            }
            else viewModel.questions(clicks, p1, p2, p3, p4, p5, titulo, textView3)
        }
    }


}

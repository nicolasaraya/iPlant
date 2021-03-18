package com.iPlantApp.iplant.ui.gallery

import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.iPlantApp.iplant.ui.plant_p.Preguntas

class MainViewModel : ViewModel () {
    private val repo = Repo()
    fun fetchPlantsData() : LiveData<MutableList<Plants>>{
        val mutableData = MutableLiveData<MutableList<Plants>>()
        repo.getPlantsData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }
    fun fetchFavsPlantsData() : LiveData<MutableList<Plants>>{
        val mutableData = MutableLiveData<MutableList<Plants>>()
        repo.getFavsPlants(FirebaseAuth.getInstance().currentUser!!.uid).observeForever{
            mutableData.value = it
        }
        return mutableData
    }
    fun addFav(string: String, plant : Plants, i : String){
        repo.insertFavs(string, plant, i)
    }
    fun removeFav(id : String){
        repo.deleteFavs(id)
    }
    fun questions(
        click: Int,
        p1: CheckBox,
        p2: CheckBox,
        p3: CheckBox,
        p4: CheckBox,
        p5: CheckBox,
        titulo: TextView,
        t2: TextView
    ) : MutableLiveData<MutableList<Preguntas>> {
        return repo.getPreguntas(click,p1,p2,p3,p4,p5,titulo,t2)
    }
    fun fetchFavsPlantsDataSugerencias(
        respuesta_pregunta1: Preguntas,
        respuesta_pregunta2: Preguntas,
        respuesta_pregunta3: Preguntas
    ): LiveData<MutableList<Plants>>{
        val mutableData = MutableLiveData<MutableList<Plants>>()
        repo.getFavsPlantsSugerencias(respuesta_pregunta1,respuesta_pregunta2,respuesta_pregunta3).observeForever{
            mutableData.value = it
        }
        return mutableData
    }
}
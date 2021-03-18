package com.iPlantApp.iplant.ui.gallery

import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.iPlantApp.iplant.ui.plant_p.Preguntas

class Repo {

    fun getPlantsData():LiveData<MutableList<Plants>>{
        val mutableData = MutableLiveData<MutableList<Plants>>()
        var listData = mutableListOf<Plants>()
        FirebaseFirestore.getInstance().collection("Plantas").get().addOnSuccessListener { result ->

            for(document in result){
                var fot = ""
                fot = document.getString("foto")!!
                var nom = ""
                nom = document.getString("nombre")!!
                var dur = ""
                dur = document.getString("duracion")!!
                var des = ""
                des = document.getString("descripcion")!!
                val plant = Plants(nom, dur, fot, des)
                listData.add(plant)
            }
            mutableData.value = listData
        }
        FirebaseFirestore.getInstance().collection("Plantas_Interior").get().addOnSuccessListener { result ->
            for(document in result){
                var fot = ""
                fot = document.getString("foto")!!
                var nom = ""
                nom = document.getString("nombre")!!
                var dur = ""
                dur = document.getString("duracion")!!
                var des = ""
                des = document.getString("info")!!
                val plant = Plants(nom, dur, fot, des)
                listData.add(plant)
            }
            mutableData.value = listData
        }


        return mutableData
    }
    fun insertFavs(name : String, plant: Plants, id: String){
        FirebaseFirestore.getInstance().collection("users").document(id).collection("MyFavs").document().set(//.document(id).set(
            hashMapOf("customnombre" to name, "nombre" to plant.nombre, "foto" to plant.foto, "duracion" to plant.duracion, "fecha" to Timestamp.now(), "descripcion" to plant.desc)
        )
    }
    fun deleteFavs(idfav: String){
        FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().uid.toString()).collection("MyFavs").document(idfav).delete()
    }
    fun getFavsPlants(id : String):LiveData<MutableList<Plants>>{
        val mutableData = MutableLiveData<MutableList<Plants>>()
        FirebaseFirestore.getInstance().collection("users").document(id).collection("MyFavs").get().addOnSuccessListener { result ->
            val listData = mutableListOf<Plants>()
            for(document in result){
                val fot = document.getString("foto")
                val nom = document.getString("nombre")
                val dur = document.getString("duracion")
                val date = document.getTimestamp("fecha")
                val customnom = document.getString("customnombre")
                val des = document.getString("descripcion")
                val id = document.id
                val plant = Plants(nom!!, dur!!, fot!!, des!!, date!!, customnom!!, id!!)
                listData.add(plant)
            }
            mutableData.value = listData
        }
        return mutableData
    }
    fun getPreguntas(
        click: Int,
        p1: CheckBox,
        p2: CheckBox,
        p3: CheckBox,
        p4: CheckBox,
        p5: CheckBox,
        titulo: TextView,
        t2 : TextView
    ): MutableLiveData<MutableList<Preguntas>> {
        var k: Preguntas = Preguntas()
        val mutableData = MutableLiveData<MutableList<Preguntas>>()

        FirebaseFirestore.getInstance().collection("Preguntas").get().addOnSuccessListener { result ->
            val listData = mutableListOf<Preguntas>()
            for(document in result){
                var id = ""
                id = document.getString("id")!!
                if(click.toString().equals(id)){
                    t2.visibility = View.VISIBLE
                    p2.visibility = View.VISIBLE
                    p1.visibility = View.VISIBLE
                    p3.visibility = View.VISIBLE
                    p1.text = document.getString("p1")!!
                    p2.text  = document.getString("p2")!!
                    p3.text  = document.getString("p3")!!
                    p4.visibility = View.VISIBLE
                    p5.visibility = View.VISIBLE
                    p4.text  = document.getString("p4")!!
                    p5.text  = document.getString("p5")!!
                    if(p4.text == "no"){
                        p4.visibility = View.INVISIBLE
                        p5.visibility = View.INVISIBLE
                    }
                    titulo.visibility = View.VISIBLE
                    titulo.text  = document.getString("titulo")!!
                }
            }
        }
        Log.d("mira esto", mutableData.value?.size.toString())
        return mutableData


    }
    fun getFavsPlantsSugerencias(
        respuesta_pregunta1: Preguntas,
        respuesta_pregunta2: Preguntas,
        respuesta_pregunta3: Preguntas
    ):LiveData<MutableList<Plants>>{
        val mutableData = MutableLiveData<MutableList<Plants>>()
        var cantidad_total = 0
        val array= arrayOf(respuesta_pregunta1, respuesta_pregunta2, respuesta_pregunta3)

        for (i in 0..2) {
            var aux = array[i]
            if(aux.alternativa1 == "True") cantidad_total++
            if(aux.alternativa2 == "True") cantidad_total++
            if(aux.alternativa3 == "True") cantidad_total++
            if(aux.alternativa4 == "True") cantidad_total++
            if(aux.alternativa5 == "True") cantidad_total++
        }
        FirebaseFirestore.getInstance().collection("Plantas_Interior").get().addOnSuccessListener { result ->
            val listData = mutableListOf<Plants>()
            for(document in result){

                var corresponde = 0
                var fot = ""
                fot = document.getString("foto")!!
                var nom = ""
                nom = document.getString("nombre")!!
                var  des  = ""
                des = document.getString("info")!!
                var dur = ""
                dur = document.getString("duracion")!!


                var bano = ""
                bano = document.getString("bano")!!
                var cocina= ""
                cocina = document.getString("cocina")!!
                var  pieza= ""
                pieza = document.getString("pieza")!!
                var  living= ""
                living = document.getString("living")!!
                var terraza= ""
                terraza = document.getString("terraza")!!
                var semi= ""
                semi = document.getString("semi")!!
                var sol= ""
                sol = document.getString("sol")!!
                var sombra= ""
                sombra = document.getString("sombra")!!
                var hg= ""
                hg = document.getString("hg")!!
                var hp= ""
                hp = document.getString("hp")!!
                var aire= ""
                aire = document.getString("aire")!!
                var palmera = ""
                palmera = document.getString("palmera")!!
                var resis = ""
                resis = document.getString("resis")!!
                val plant = Plants(nom, dur, fot, des)
                if(respuesta_pregunta1.alternativa1 == "True" && semi =="1")corresponde++
                if(respuesta_pregunta1.alternativa2 == "True" && sombra =="1")corresponde++
                if(respuesta_pregunta1.alternativa3 == "True" && sol =="1")corresponde++
                if(respuesta_pregunta2.alternativa1 == "True" && pieza =="1")corresponde++
                if(respuesta_pregunta2.alternativa2 == "True" && living =="1")corresponde++
                if(respuesta_pregunta2.alternativa3 == "True" && cocina =="1")corresponde++
                if(respuesta_pregunta2.alternativa4 == "True" && terraza =="1")corresponde++
                if(respuesta_pregunta2.alternativa5 == "True" && bano =="1")corresponde++

                if(respuesta_pregunta3.alternativa1 == "True" && resis =="1")corresponde++
                if(respuesta_pregunta3.alternativa2 == "True" && palmera =="1")corresponde++
                if(respuesta_pregunta3.alternativa3 == "True" && hg =="1")corresponde++
                if(respuesta_pregunta3.alternativa4 == "True" && hp =="1")corresponde++
                if(respuesta_pregunta3.alternativa5 == "True" && aire =="1")corresponde++
                if(corresponde == cantidad_total ) listData.add(plant)





            }
            mutableData.value = listData
        }


        return mutableData
    }
}
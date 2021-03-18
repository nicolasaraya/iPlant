package com.iPlantApp.iplant.ui.plant_p


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Preguntas(
    val titulo : String = "",

    var alternativa1: String = "",
    var alternativa2: String = "",
    var alternativa3: String = "",
    var alternativa4: String = "",
    var alternativa5: String = ""

):Parcelable



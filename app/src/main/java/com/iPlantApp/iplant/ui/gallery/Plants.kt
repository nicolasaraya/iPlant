package com.iPlantApp.iplant.ui.gallery

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Plants(
    val nombre : String = "",
    val duracion: String = "",
    val foto : String,
    val desc : String,
    val fecha: com.google.firebase.Timestamp = com.google.firebase.Timestamp.now(),
    val customname : String = "",
    val id : String = ""
):Parcelable



@Entity
data class PlantsEntity(
    @PrimaryKey
    val nombre: String,
    @ColumnInfo(name ="imagenes")
    val img : String = "",
    @ColumnInfo(name ="duraciones")
    val duracion : String = ""
)

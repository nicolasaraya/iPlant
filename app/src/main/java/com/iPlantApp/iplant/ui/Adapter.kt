package com.iPlantApp.iplant.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iPlantApp.iplant.R
import com.iPlantApp.iplant.ui.gallery.Plants
import kotlinx.android.synthetic.main.item_row.view.*


class Adapter(private val context: Context?, private val itemClickListener: plantClickListener, select : Int): RecyclerView.Adapter<Adapter.RviewHolder>(){
    interface plantClickListener{
        fun plantClick(plant: Plants){

        }
    }
    private var dataList = mutableListOf<Plants>()
    private var s = select
    fun setListData(data:MutableList<Plants>){
        dataList = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RviewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)
        return RviewHolder(view)
    }

    override fun getItemCount(): Int {
        if(dataList.size > 0) return dataList.size
        else return 0
    }

    override fun onBindViewHolder(holder: RviewHolder, position: Int) {
        val p = dataList[position]
        if(s == 0) holder.bindView(p)
        else holder.bindViewFavs(p)

    }
    inner class RviewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindView(plant : Plants){
            if(context != null) Glide.with(context).load(plant.foto).into(this.itemView.circleImage)
            itemView.nombre.text = plant.nombre
            itemView.txt_desc.text = plant.duracion
            itemView.setOnClickListener{itemClickListener.plantClick(plant)}
        }
        fun bindViewFavs(plant : Plants){
            if(context != null) Glide.with(context).load(plant.foto).into(this.itemView.circleImage)
            itemView.nombre.text = plant.customname
            itemView.txt_desc.text = plant.duracion
            itemView.setOnClickListener{itemClickListener.plantClick(plant)}
        }
    }

}
package com.example.fragments_v2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adapter.R

class AdaptadorTitulares(private val datos: Array<Image>,
                         private val clickListener : (Image) -> Unit) :
    RecyclerView.Adapter<AdaptadorTitulares.TitularesViewHolder>() {

    inner class TitularesViewHolder(val item: View) : RecyclerView.ViewHolder(item) {
        val image = item.findViewById(R.id.imageView3) as ImageView
        val lblAño = item.findViewById(R.id.fragmentTextView) as TextView

        fun bindTitular(image: Image){
            .setImageResource(image.image)
            fragmentText.text = image.texto
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitularesViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_imagen, parent, false) as LinearLayout
        return TitularesViewHolder(item)
    }

    override fun onBindViewHolder(holder: TitularesViewHolder, position: Int) {
        val titular = datos[position]
        holder.bindTitular(titular)

        holder.item.setOnClickListener { clickListener(titular) };
    }

    override fun getItemCount() = datos.size
}
// El .Adapter necesita que le especifiquemos el view holder a usar <>.
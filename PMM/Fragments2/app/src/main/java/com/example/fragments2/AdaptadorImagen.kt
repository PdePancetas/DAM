package com.example.fragments_v2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fragments2.Image
import com.example.fragments2.R

class AdaptadorImagen(private val datos: Array<Image>,
                         private val clickListener : (Image) -> Unit) :
    RecyclerView.Adapter<AdaptadorImagen.ImagenesViewHolder>() {

    inner class ImagenesViewHolder(val item: View) : RecyclerView.ViewHolder(item) {
        val image = item.findViewById(R.id.imageView3) as ImageView
        val lblAño = item.findViewById(R.id.fragmentTextView) as TextView

        fun bindTitular(image: Image){
            //.setImageResource(image.imagen)
            //fragmentText.text = image.texto
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagenesViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_imagen, parent, false) as LinearLayout
        return ImagenesViewHolder(item)
    }

    override fun onBindViewHolder(holder: ImagenesViewHolder, position: Int) {
        val titular = datos[position]
        holder.bindTitular(titular)

        holder.item.setOnClickListener { clickListener(titular) };
    }

    override fun getItemCount() = datos.size
}
// El .Adapter necesita que le especifiquemos el view holder a usar <>.
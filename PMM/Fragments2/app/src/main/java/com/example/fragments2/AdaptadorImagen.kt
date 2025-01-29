package com.example.fragments2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorImagen(private val datos: Array<Image>,
                         private val clickListener : (Image) -> Unit) :
    RecyclerView.Adapter<AdaptadorImagen.ImagenesViewHolder>() {

    inner class ImagenesViewHolder(val item: View) : RecyclerView.ViewHolder(item) {
        val image = item.findViewById(R.id.imageView3) as ImageView
        val lblAño = item.findViewById(R.id.fragmentTextView) as TextView

        fun bindImage(imagen: Image) {
            // Aquí asignas la imagen y la descripción a las vistas del ítem
            itemView.findViewById<ImageView>(R.id.imageView).setImageResource(imagen.imagen)
            itemView.findViewById<TextView>(R.id.fragmentTextView).text = imagen.texto
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagenesViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_imagen, parent, false) as LinearLayout
        return ImagenesViewHolder(item)
    }

    override fun onBindViewHolder(holder: ImagenesViewHolder, position: Int) {
        val image = datos[position]
        holder.bindImage(image)

        holder.item.setOnClickListener { clickListener(image) };
    }

    override fun getItemCount() = datos.size
}
// El .Adapter necesita que le especifiquemos el view holder a usar <>.
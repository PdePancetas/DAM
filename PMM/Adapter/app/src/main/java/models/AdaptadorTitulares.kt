package models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adapter.R

class AdaptadorTitulares(private val datos: Array<Film>,
                         private val clickListener : (Film) -> Unit) :
    RecyclerView.Adapter<AdaptadorTitulares.TitularesViewHolder>() {

        inner class TitularesViewHolder(val item: View) : RecyclerView.ViewHolder(item) {
            val image = item.findViewById(R.id.testImage) as ImageView
            val lblTitulo = item.findViewById(R.id.lblTitulo) as TextView
            val lblAño = item.findViewById(R.id.lblAño) as TextView

            fun bindTitular(film: Film){
                image.setImageResource(film.image)
                lblTitulo.text = film.titulo
                lblAño.text = film.año.toString()
            }
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitularesViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_titular, parent, false) as LinearLayout
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
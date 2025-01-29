package com.example.fragments2


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragments_v2.AdaptadorImagen


class FragmentImagenes : androidx.fragment.app.Fragment() {

    private lateinit var lstListado : RecyclerView

    private val imagenes = arrayOf(Image(R.drawable.marvel_infinity_war,"Esta pelicula es de ciencia ficción"),
        Image(R.drawable.spiderman_homecoming,"Spider-Man: Homecoming"))

    /* Vamos a asignar un evento desde fuera del fragment.*/
    var listener : ImageListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_image_v2, container, false)
    }

    override fun onViewCreated(view:View,savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

        lstListado = view?.findViewById<View>(R.id.lstListado) as RecyclerView

        val adaptador = AdaptadorImagen(imagenes) {
            listener?.onImageClick(it)
        }

        lstListado.layoutManager =
                //GridLayoutManager(this.context, 3)
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        lstListado.addItemDecoration(
            DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        )

        //lstListado.adapter = adaptador
    }

    fun setImageListener(l: () -> Unit) {
        listener = l
    }

    /*
    * Defino una función que establece un listener que se activa cuando se seleccione un Correo
    */
    fun setCorreosListener(seleccion: (Image) -> Unit) {

        /*
        * object:CorreosListener crea una implementación de la interface CorreosListener
        * sin necesidad de crear una clase separada.
        * */
        listener = object: ImageListener {
            override fun onImageClick(image: Image) {
                seleccion(image)
            }
        }
    }



}

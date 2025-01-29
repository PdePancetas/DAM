package com.example.adapter

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class FragmentListado : Fragment() {

    private lateinit var lstListado : RecyclerView

    private val datos =
        MutableList(5) { i -> Correo("Persona $i", "Asunto del correo $i", "Texto del correo $i") }

    /* Vamos a asignar un evento desde fuera del fragment.*/
    var listener : CorreosListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_listado, container, false)
    }

    override fun onViewCreated(view:View,savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

        lstListado = view?.findViewById<View>(R.id.lstListado) as RecyclerView

        val adaptador = AdaptadorCorreos(datos) {
            listener?.onCorreoSelecionado(it)
        }

        lstListado.layoutManager =
                //GridLayoutManager(this.context, 3)
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        lstListado.addItemDecoration(
            DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        )

        lstListado.adapter = adaptador
    }

    fun setCorreosListener(l: CorreosListener) {
        listener = l
    }

    /*
    * Defino una función que establece un listener que se activa cuando se seleccione un Correo
    */
    fun setCorreosListener(seleccion: (Correo) -> Unit) {

        /*
        * object:CorreosListener crea una implementación de la interface CorreosListener
        * sin necesidad de crear una clase separada.
        * */
        listener = object:CorreosListener {
            override fun onCorreoSelecionado(correo: Correo) {
                seleccion(correo)
            }
        }
    }



}

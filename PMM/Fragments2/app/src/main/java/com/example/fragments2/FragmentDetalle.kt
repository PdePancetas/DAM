package com.example.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fragments2.R

class FragmentDetalle : Fragment() {

    lateinit var txtDetalle:TextView
    private var datosPendientes: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_detalle, container, false)
    }

    override fun onViewCreated(view:View,savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)


        //Log.d("FragmentDetalle", "onViewCreated ejecutado")
        txtDetalle = view.findViewById(R.id.txtDetalle)
        // Aplica los datos pendientes, si existen
        datosPendientes?.let {
            txtDetalle.text = it
            datosPendientes = null
        }
    }

    fun mostrarDetalle(datos: String) {
        if (::txtDetalle.isInitialized) {
            txtDetalle.text = datos
        } else {
            Log.e("FragmentDetalle", "La vista no está inicializada.")
            datosPendientes = datos
        }
    }
    /*fun mostrarDetalle(texto: String) {
        txtDetalle = requireView().findViewById(R.id.txtDetalle) as TextView

        txtDetalle.text = texto
    }*/
}

package com.example.fragments2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class FragmentA : Fragment() {

    lateinit var layoutFragmentA: LinearLayout
    lateinit var imagen1: ImageView
    lateinit var imagen2: ImageView

    var listener : ImageListener? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imagen1 = view.findViewById<ImageView>(R.id.img1)
        imagen2 = view.findViewById<ImageView>(R.id.img2)
        layoutFragmentA = view.findViewById<LinearLayout>(R.id.layout_fragment_a)

        imagen1.setOnClickListener {
            listener?.onImageClick(imagen1);

        }

        imagen2.setOnClickListener {
            listener?.onImageClick(imagen2);
        }

    }

    fun setImageListener(seleccion: (ImageView) -> Unit) {

        /*
         * object:ImageListener crea una implementación de la interface ImageListener
         * sin necesidad de crear una clase separada.
         */
        listener = object : ImageListener {
            override fun onImageClick(imagen: ImageView) {
                seleccion(imagen)
            }
        }
    }

    fun cambiarOrientacionVertical() {
        layoutFragmentA.orientation = LinearLayout.VERTICAL
    }

    // Método para cambiar a horizontal
    fun cambiarOrientacionHorizontal() {
        layoutFragmentA.orientation = LinearLayout.HORIZONTAL
    }


}
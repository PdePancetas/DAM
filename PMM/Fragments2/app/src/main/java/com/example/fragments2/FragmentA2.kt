package com.example.fragments2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class FragmentA2 : Fragment() {

    lateinit var layoutFragmentA: LinearLayout
    lateinit var imagen11: ImageView
    lateinit var imagen21: ImageView

    var listener : ImageListener? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imagen11 = view.findViewById<ImageView>(R.id.img11)
        imagen21 = view.findViewById<ImageView>(R.id.img21)
        layoutFragmentA = view.findViewById<LinearLayout>(R.id.layout_fragment_a2)

        imagen11.setOnClickListener {
            listener?.onImageClick(imagen11);

        }

        imagen21.setOnClickListener {
            listener?.onImageClick(imagen21);
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


}
package com.example.fragments2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class FragmentC : Fragment() {

    lateinit var img3: ImageView
    lateinit var img4: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_c, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        img3 = view.findViewById<ImageView>(R.id.img3)
        img4 = view.findViewById<ImageView>(R.id.img4)

    }

    fun intercambiarImagenes() {
        val temp = img3.drawable
        img3.setImageDrawable(img4.drawable)
        img4.setImageDrawable(temp)
    }
}
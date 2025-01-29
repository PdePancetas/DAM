package com.example.fragments2

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.adapter.FragmentDetalle

class MainActivityV2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_v2)
        //enableEdgeToEdge()

        val frgImagenes = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as FragmentImagenes
        frgImagenes.setImageListener {
            var frgDetalle: Fragment? =
                supportFragmentManager.findFragmentById(R.id.fragmentContainerViewHorz) as? FragmentTexto

            if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                (frgDetalle as FragmentDetalle).mostrarDetalle(it.texto)

            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

                (frgDetalle as FragmentDetalle).mostrarDetalle(it.texto)
            }


        }

    }
}

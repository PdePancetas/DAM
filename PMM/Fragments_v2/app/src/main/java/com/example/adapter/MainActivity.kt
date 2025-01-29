package com.example.adapter

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        //enableEdgeToEdge()

        val frgListado = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as FragmentListado
        frgListado.setCorreosListener {
            var frgDetalle: Fragment? =
                supportFragmentManager.findFragmentById(R.id.fragmentContainerViewHorz) as? FragmentDetalle

            if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {

                val i = Intent(this@MainActivity, DetalleActivity::class.java)
                i.putExtra("DETALLE", it.texto)
                startActivity(i)

            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

                (frgDetalle as FragmentDetalle).mostrarDetalle(it.texto)
            }


        }

    }
}

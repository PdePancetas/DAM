package com.example.fragments2

import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val frgA = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewA) as FragmentA

        Handler(Looper.getMainLooper()).post {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                frgA.cambiarOrientacionHorizontal()
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                frgA.cambiarOrientacionVertical()
            }
        }


        frgA.setImageListener {
            val frgB =
                supportFragmentManager.findFragmentById(R.id.fragmentContainerViewB) as FragmentB
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                frgB.mostrarTexto(it.contentDescription.toString())
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

                frgB.mostrarTexto(it.contentDescription.toString())
                val frgC =
                    supportFragmentManager.findFragmentById(R.id.fragmentContainerViewC) as FragmentC

                frgC.intercambiarImagenes()
            }


        }

    }







}


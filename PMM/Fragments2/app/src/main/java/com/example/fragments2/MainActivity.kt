package com.example.fragments2

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        //enableEdgeToEdge()

        val frgA = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewA) as FragmentA
        frgA?.setImageListener {
            var frgB: FragmentB = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewB) as FragmentB

            frgB.mostrarTexto(it.contentDescription.toString());

            if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                // En Kotlin
                val layoutFrgA: LinearLayout = findViewById<LinearLayout>(R.id.layout_fragment_a)
                layoutFrgA.orientation = LinearLayout.VERTICAL
                var frgC: FragmentC = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewHorzC) as FragmentC

                var imagen3Source = frgC.img3.drawable
                var imagen4Source = frgC.img4.drawable

                frgC.img3.setImageDrawable(imagen4Source)
                frgC.img4.setImageDrawable(imagen3Source)


            }

        }

    }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val frgA = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewA) as FragmentA

        frgA.setImageListener {


            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                val frgB =
                    supportFragmentManager.findFragmentById(R.id.fragmentContainerViewB) as FragmentB
                frgB.mostrarTexto(it.contentDescription.toString())
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                val frgB =
                    supportFragmentManager.findFragmentById(R.id.fragmentContainerView5) as FragmentB

                frgB.mostrarTexto(it.contentDescription.toString())
                val frgC =
                    supportFragmentManager.findFragmentById(R.id.fragmentContainerView6) as FragmentC
                frgC.intercambiarImagenes()
            }



        }

    }







}


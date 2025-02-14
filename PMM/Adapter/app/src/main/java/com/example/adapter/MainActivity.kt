package com.example.adapter

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import models.AdaptadorTitulares
import models.Film

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.v_recycler)
        var recView:RecyclerView = findViewById(R.id.vRecycler)

        val titulares = arrayOf(Film(R.drawable.marvel_infinity_war,"Avengers: Infinity War",2018,getString(R.string.genreAvengers),getString(R.string.sinopsisAvengers), 156),
                                Film(R.drawable.spiderman_homecoming,"Spider-Man: Homecoming",2018,getString(R.string.genreSpiderman),getString(R.string.sinopsisSpiderman), 133),
                                Film(R.drawable.padmaawat,"Padmaawat",2017,getString(R.string.genrePadmaawat),getString(R.string.sinopsisPadmaawat), 164),
                                Film(R.drawable.black_panther,"Black Panther",2018,getString(R.string.genreBlackPanther),getString(R.string.sinopsisBlackPanther), 134),
                                Film(R.drawable.rampage,"Rampage",2018,getString(R.string.genreRampage),getString(R.string.sinopsisRampage), 107))

        val datosRec =
            Array(titulares.size) { i -> titulares[i] }
        val adaptadorRec = AdaptadorTitulares(datosRec) {
            //Log.i("DemoRecView", "Pulsado el elemento: ${it.titulo}")
            val intent = Intent(this@MainActivity, DataActivity::class.java)
            intent.putExtra("imagen",it.image)
            intent.putExtra("genre",it.genre)
            intent.putExtra("sinopsis",it.sinopsis)
            intent.putExtra("duration",it.duration)
            startActivity(intent)
        }



        recView.setHasFixedSize(true)
        recView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            //GridLayoutManager(this, 3)
        recView.adapter = adaptadorRec



    }
}
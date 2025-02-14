package com.example.myapplication

import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.example.layouts.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        //setContentView(R.layout.activity_main)
        setContentView(R.layout.main_activity3_constraint)
        setContentView(R.layout.main_activity2_controls)

        val lblMensaje = findViewById<TextView>(R.id.lblMensaje)
        val buttonControls = findViewById<Button>(R.id.buttonControls)
        val toggleButton = findViewById<ToggleButton>(R.id.toggleButton)
        val switchButton = findViewById<Switch>(R.id.switchButton)
        val imageButton = findViewById<ImageButton>(R.id.imageButton)
        val buttonIcon = findViewById<Button>(R.id.buttonIcon)

        buttonControls.setOnClickListener(){
            lblMensaje.text = "Has pulsado un botón"
        }

        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked)
                lblMensaje.text = "Boton toggle: On"
            else
                lblMensaje.text = "Boton toggle: Off"
        }

        switchButton.setOnCheckedChangeListener{_,isChecked ->
            if(isChecked)
                lblMensaje.text = "Boton switch: On"
            else
                lblMensaje.text = "Boton switch: Off"
        }

        imageButton.setOnClickListener { lblMensaje.text = "Has pulsado en el boton imagen" }
        buttonIcon.setOnClickListener { lblMensaje.text = "Has pulsado en el boton con icono"}
    }
}
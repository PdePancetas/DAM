package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.layouts.R
import com.google.android.material.textfield.TextInputEditText

class ControlesEntrada : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.main_activity_controles_entrada)


        var genero = ""
        val hobbies:ArrayList<String> = arrayListOf()

        val opcionesCheckBox: ArrayList<CheckBox> = arrayListOf(findViewById(R.id.cbOp1),
                                                                findViewById(R.id.cbOp2),
                                                                findViewById(R.id.cbOp3),
                                                                findViewById(R.id.cbOp4))


        val paises = arrayOf("España","Francia","Alemania","Italia","Portugal")
        val cmbOpciones: Spinner = findViewById(R.id.spinnerPaises)
        val adaptador =
            ArrayAdapter(this,
                android.R.layout.simple_spinner_item, paises)
        adaptador.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)
        cmbOpciones.adapter = adaptador

        val generos:RadioGroup = findViewById(R.id.rdGgeneros)
        generos.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbtnHombre -> genero = "Hombre"
                R.id.rbtnMujer -> genero = "Mujer"
                R.id.rbtnApache -> genero = "En serio has escogido un helicóptero apache??"
                R.id.rbtnCroi -> genero = "Genial, un croissant"
            }

        }

        val opcionesCheckBoxEvent = View.OnClickListener { view ->
            if(view is CheckBox){

                when(view.getId()){
                    R.id.cbOp1 ->
                        if(findViewById<CheckBox>(R.id.cbOp1).isChecked)
                            hobbies.add("Música")
                        else
                            hobbies.remove("Música")
                    R.id.cbOp2 ->
                        if(findViewById<CheckBox>(R.id.cbOp2).isChecked)
                            hobbies.add("Deporte")
                        else
                            hobbies.remove("Deporte")
                    R.id.cbOp3 ->
                        if(findViewById<CheckBox>(R.id.cbOp3).isChecked)
                            hobbies.add("Medicina")
                        else
                            hobbies.remove("Medicina")
                    R.id.cbOp4 ->
                        if(findViewById<CheckBox>(R.id.cbOp4).isChecked)
                            hobbies.add("Márketing")
                        else
                            hobbies.remove("Márketing")

                }

            }

        }

        for (i in 0 until opcionesCheckBox.count()) {
            val op = opcionesCheckBox[i]
            op.setOnClickListener(opcionesCheckBoxEvent)
        }

        findViewById<Button>(R.id.btnSubmit).setOnClickListener{
            val intent = Intent(this@ControlesEntrada, ControlesEntrada2::class.java)
            intent.putExtra("nombre",findViewById<TextInputEditText>(R.id.txtInputNombre).text.toString())
            intent.putExtra("pais",findViewById<Spinner>(R.id.spinnerPaises).selectedItem.toString())
            intent.putExtra("genero",genero)

            var textHobbies = ""
            for (hobby in hobbies){
                textHobbies+= "$hobby "
            }
            intent.putExtra("hobbies",textHobbies)
            startActivity(intent)
        }

    }
}
package com.dev.allannascimento.gasolinaoualcool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var editGasolina: EditText
    private lateinit var editAlcool: EditText
    private lateinit var btnCalcular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editGasolina = findViewById( R.id.edit_gasolina )
        editAlcool = findViewById( R.id.edit_alcool )
        btnCalcular = findViewById( R.id.btn_calcular )

        btnCalcular.setOnClickListener {

            val intent = Intent(this, DetalhesActivity::class.java)

            val gasolina = editGasolina.text.toString()
            val alcool = editAlcool.text.toString()

            if (gasolina.isNotEmpty() && alcool.isNotEmpty()) {

                intent.putExtra("gasolina", gasolina.toDouble())
                intent.putExtra("alcool", alcool.toDouble())

            }

            startActivity( intent )

        }


    }
}
package com.dev.allannascimento.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultadoActivity : AppCompatActivity() {

    private lateinit var textPeso: TextView
    private lateinit var textAltura: TextView
    private lateinit var textResultado: TextView

    private lateinit var btnNovo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        textPeso = findViewById(R.id.textPeso)
        textAltura = findViewById(R.id.textAltura)
        textResultado = findViewById(R.id.textResultado)

        btnNovo = findViewById(R.id.btnNovo)

        val bundle = intent.extras

        if (bundle != null) {

            val peso = bundle.getDouble("peso")
            val altura = bundle.getDouble("altura")

            textPeso.text = "Peso informado é de ${peso}kg"
            textAltura.text = "Altura informado é de ${altura}m"

            val imc = peso / (altura * altura)

            if (imc < 18.5) {
                textResultado.text = "Nível de peso: BAIXO"
            } else if (imc in 18.5..24.9) {
                textResultado.text = "Nível de peso: NORMAL"
            } else if (imc in 25.0..29.9) {
                textResultado.text = "Nível de peso: SOBREPESO"
            } else if (imc > 30.0) {
                textResultado.text = "Nível de peso: OBESIDADE"
            }

        }

        btnNovo.setOnClickListener {
            finish()
        }

    }
}
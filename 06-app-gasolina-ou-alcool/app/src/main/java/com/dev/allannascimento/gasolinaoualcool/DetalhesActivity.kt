package com.dev.allannascimento.gasolinaoualcool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DetalhesActivity : AppCompatActivity() {

    private lateinit var textGasolina: TextView
    private lateinit var textAlcool: TextView
    private lateinit var textResultado: TextView
    private lateinit var btnNovo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        textGasolina = findViewById( R.id.text_gasolina )
        textAlcool = findViewById( R.id.text_alcool )
        textResultado = findViewById( R.id.text_resultado )
        btnNovo = findViewById( R.id.btn_novo )

        btnNovo.setOnClickListener {
            finish()
        }

        val bundle = intent.extras

        if (bundle != null) {

            val gasolina = bundle.getDouble("gasolina")
            val alcool = bundle.getDouble("alcool")

            textGasolina.text = "Preço da Gasolina: $gasolina"
            textAlcool.text = "Preço do Álcool: $alcool"

            val resultado = alcool / gasolina

            if ( resultado > 0.7 ) {
                textResultado.text = "Gasolina"
            } else {
                textResultado.text = "Álcool"
            }
        }

    }
}
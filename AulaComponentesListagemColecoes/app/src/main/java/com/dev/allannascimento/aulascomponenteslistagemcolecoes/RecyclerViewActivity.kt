package com.dev.allannascimento.aulascomponenteslistagemcolecoes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var rvLista: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val lista = listOf(
            Mensagem("Maicon", "e aí man kk", "10:28"),
            Mensagem("Douglas", "opa, mano", "10:23"),
            Mensagem("Solador", "eu solo kk", "10:11"),
            Mensagem("Gustavo", "tá tranquilo", "09:55")
        )

        rvLista = findViewById( R.id.rv_lista )
        rvLista.adapter = MensagemAdapter(lista)
        rvLista.layoutManager = LinearLayoutManager(this)

    }
}
package com.dev.allannascimento.aulafragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.dev.allannascimento.aulafragment.fragments.ChamadasFragment
import com.dev.allannascimento.aulafragment.fragments.ConversasFragment

class MainActivity : AppCompatActivity() {

    private lateinit var btnConversas: Button
    private lateinit var btnChamadas: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* val fragmentManager = supportFragmentManager.beginTransaction()

        fragmentManager.add( R.id.fragment_conteudo, ConversasFragment() )

        fragmentManager.commit() */

        btnConversas = findViewById( R.id.btn_conversas )
        btnChamadas = findViewById( R.id.btn_chamadas )

        btnConversas.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace( R.id.fragment_conteudo, ConversasFragment() )
                .commit()
        }

        btnChamadas.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace( R.id.fragment_conteudo, ChamadasFragment() )
                // .remove( conversasFragment )
                .commit()
        }

    }
}
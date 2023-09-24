package com.allannascimento.realm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.allannascimento.realm.database.DatabaseRealm
import com.allannascimento.realm.databinding.ActivityMainBinding
import com.allannascimento.realm.model.Usuario
import org.mongodb.kbson.ObjectId

class MainActivity : AppCompatActivity() {

    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    private val realm by lazy {DatabaseRealm()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener {
            val nomeRecuperado = binding.editNome.text.toString()

            val usuario = Usuario().apply {
                nome = nomeRecuperado
                idade = 10
            }

            realm.salvar(usuario)

        }

        binding.btnAtualizar.setOnClickListener {

            val nomeRecuperado = binding.editNome.text.toString()
            val idSelecionado = ObjectId("650e585dbbc5835df1bf0900")
            val usuario = Usuario().apply {
                id = idSelecionado
                nome = nomeRecuperado
                idade = 40
            }
            realm.atualizar(usuario)
        }

        binding.btnDeletar.setOnClickListener {

            // 650e5868bbc5835df1bf0903

            val id = ObjectId("650e5868bbc5835df1bf0903")
            realm.deletar(id)

        }

        binding.btnListar.setOnClickListener {
            val lista = realm.listar()

            var textoLista = ""
            lista.forEach{usuario ->
                textoLista += "${usuario.nome} - ${usuario.idade} \n"
                Log.i("info_realm", "id: ${usuario.id} - ${usuario.nome}")
            }
            binding.textResultado.text = textoLista
        }

    }
}
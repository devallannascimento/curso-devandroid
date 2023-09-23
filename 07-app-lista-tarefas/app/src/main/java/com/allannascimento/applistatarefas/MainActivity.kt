package com.allannascimento.applistatarefas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.allannascimento.applistatarefas.adapter.TarefaAdapter
import com.allannascimento.applistatarefas.database.TarefaDAO
import com.allannascimento.applistatarefas.databinding.ActivityMainBinding
import com.allannascimento.applistatarefas.model.Tarefa

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var listaTarefas = emptyList<Tarefa>()

    private var tarefaAdapter: TarefaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fabAdicionar.setOnClickListener {
            val intent = Intent(this, AdicionarTarefaActivity::class.java)
            startActivity(intent)
        }

        tarefaAdapter = TarefaAdapter(
            {id -> confirmarEsclusao(id)},
            {tarefa -> editar(tarefa) }
        )
        binding.rvTarefas.adapter = tarefaAdapter

        binding.rvTarefas.layoutManager = LinearLayoutManager(this)

    }

    private fun editar(tarefa: Tarefa) {
        val intent = Intent(this, AdicionarTarefaActivity::class.java)
        intent.putExtra("tarefa", tarefa)
        startActivity(intent)
    }

    private fun confirmarEsclusao(id: Int) {

        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Confirmar exclusão")
        alertBuilder.setMessage("Deseja realmente explicar a tarefas?")
        alertBuilder.setPositiveButton("Sim"){_,_ ->
            val tarefaDAO = TarefaDAO(this)
            if (tarefaDAO.deletar(id)){
                atualizarListaTarefas()
                Toast.makeText(
                    this,
                    "Tarefa excluída com sucesso",
                    Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(
                    this,
                    "Erro ao excluir tarefa",
                    Toast.LENGTH_SHORT).show()
            }
        }
        alertBuilder.setNegativeButton("Não"){_,_ -> }
        alertBuilder.create().show()

    }

    private fun atualizarListaTarefas(){
        listaTarefas = TarefaDAO(this).listar()
        tarefaAdapter?.adicionarLista(listaTarefas)
    }

    override fun onStart() {
        super.onStart()
        atualizarListaTarefas()
    }
}
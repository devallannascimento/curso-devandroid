package com.allannascimento.applistatarefas.database

import com.allannascimento.applistatarefas.model.Tarefa

interface ITarefaDAO {
    fun salvar( tarefa: Tarefa ): Boolean
    fun atualizar( tarefa: Tarefa ): Boolean
    fun deletar( id: Int ): Boolean
    fun listar(): List<Tarefa>
}
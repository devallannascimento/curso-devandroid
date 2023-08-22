package com.dev.allannascimento.aulascomponenteslistagemcolecoes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MensagemAdapter(
    private val lista: List<Mensagem>
) : Adapter<MensagemAdapter.MensagemViewHoulder>() {

    inner class MensagemViewHoulder(
        val itemView: View
    ) : ViewHolder(itemView) {

        val textNome: TextView = itemView.findViewById(R.id.text_nome)
        val textPrevia: TextView = itemView.findViewById(R.id.text_previa)
        val textHora: TextView = itemView.findViewById(R.id.text_hora)

    }

    // Ao criar o ViewHolder -> Criar a vizualizaçãp
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensagemViewHoulder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val itemView = layoutInflater.inflate(R.layout.item_lista, parent, false)

        return MensagemViewHoulder(itemView)

    }

    // Recuperar a quantidade de itens
    override fun getItemCount(): Int {
        return lista.size
    }

    // Ao vincular o ViewHolder
    override fun onBindViewHolder(holder: MensagemViewHoulder, position: Int) {

        val mensagem = lista[position]
        holder.textNome.text = mensagem.nome
        holder.textPrevia.text = mensagem.previa
        holder.textHora.text = mensagem.hora

    }

}
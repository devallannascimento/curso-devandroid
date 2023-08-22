package com.dev.allannascimento.exercicioadapter

interface Adaptador{

    fun quantidadeItens() : Int

    fun montarLayoutParaItem(posicao: Int) : String

}

class MeuAdaptador(lista: List<String>): Adaptador {

    private val listaItens = lista

    override fun quantidadeItens(): Int {
        return listaItens.size
    }

    override fun montarLayoutParaItem(posicao: Int): String {
        return "$posicao) ${listaItens[posicao]} -"
    }

}

class ComponenteListagem(var adaptador: Adaptador? = null){

    fun executar(){

        if (adaptador != null) {
            val quantidadeItens = adaptador!!.quantidadeItens()
            for (posicao in 0 until quantidadeItens){
                val item = adaptador!!.montarLayoutParaItem(posicao)
                println( item )
            }
        } else {
            println("Configurar um adaptador para proseguir")
        }

    }

}

fun main() {

    val listaItens = listOf("Jamilton", "Ana", "Maria", "Pedro", "João")

    val componesteListagem = ComponenteListagem()
    componesteListagem.adaptador = MeuAdaptador(listaItens)
    componesteListagem.executar()

}
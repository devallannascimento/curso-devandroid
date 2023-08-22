package com.dev.allannascimento.aulascomponenteslistagemcolecoes.testes

class Pessoa {

    val nome: String = ""

}

var nome = "Maicon Douglas"


fun executar(nomeNovo :String){
    nome = "mas agora é $nomeNovo"
}

fun main() {

    println(nome)

    println("")

    executar("Ana")
    println(nome)

    println("")

    nome = "voltou a ser MD"
    println(nome)

}
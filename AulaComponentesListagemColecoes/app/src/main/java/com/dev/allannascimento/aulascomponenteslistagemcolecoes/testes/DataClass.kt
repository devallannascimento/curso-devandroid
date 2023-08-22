package com.dev.allannascimento.aulascomponenteslistagemcolecoes.testes

data class Pergunta(val pergunta: String, val respostaCerta: Int)

fun main() {

    var pergunta1 = Pergunta("Qual a pergunta?", 1)
    val pergunta2 = Pergunta("Qual a pergunta?", 1)

    var (pergunta, resposta) = pergunta1

    println(pergunta1)
    println(pergunta2)
    println(pergunta1 == pergunta2)
    println("")

    println(pergunta)
    println(resposta)
    println("")

    pergunta = "Qual o nome humano do Superman?"
    resposta = 2

    println(pergunta)
    println(resposta)

}
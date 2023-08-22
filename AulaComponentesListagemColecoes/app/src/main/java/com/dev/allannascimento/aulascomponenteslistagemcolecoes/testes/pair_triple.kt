package com.dev.allannascimento.aulascomponenteslistagemcolecoes.testes



fun main() {


    // Pair
    val localizacao1 = Pair(10, 20)

    println(localizacao1.first)
    println(localizacao1.second)

    println("")

    val localizacao2 = "maicon" to "douglas"

    println(localizacao2.first)
    println(localizacao2.second)

    println("")

    // Triple

    val localizacao3 = Triple("maicon", "douglas", "md")

    println(localizacao3.first)
    println(localizacao3.second)
    println(localizacao3.third)

}
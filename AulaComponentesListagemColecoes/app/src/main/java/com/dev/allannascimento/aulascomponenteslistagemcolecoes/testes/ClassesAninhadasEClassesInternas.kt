package com.dev.allannascimento.aulascomponenteslistagemcolecoes.testes

class Motorista(private val nome: String ) {
    fun exibirDadosMotorista() = println("Motorista: $nome")

    inner class Caminhao(private val nomeCaminhao: String, private val placaCaminhao: String) {
        fun exibirDadosCaminhao() =
            println("Caminhão: $nomeCaminhao\nPlaca: $placaCaminhao")
    }

    /*class Caminhao(private val nomeCaminhao: String, private val placaCaminhao: String) {
        fun exibirDadosCaminhao() =
            println("Caminhão: $nomeCaminhao\nPlaca: $placaCaminhao")
    }*/


}

fun main() {

    val motorista = Motorista("Douglas Barbosa")
    motorista.exibirDadosMotorista()
    val caminhao = motorista.Caminhao("Scania R440","KCM-3978")
    caminhao.exibirDadosCaminhao()

    /*val caminhao = Motorista.Caminhao("Scania R440","KCM-3978")
    caminhao.exibirDadosCaminhao()*/

}
package com.dev.allannascimento.exercicioenumcompanion

enum class Status{
    FUNCIONAMENTO,
    MANUTENCAO,
    QUEBRADO
}

abstract open class Veiculo (
    var nome: String,
    var qtnRodas: Int,
    var status: Status = Status.FUNCIONAMENTO
) {

    abstract fun acelerar()
    abstract fun acelerar( intensidade: Int)
    abstract fun recuperarStatus()

}

class Carro: Veiculo("Gol",4) {
    override fun acelerar() {
        println("Acelerar $nome com $qtnRodas rodas")
    }

    override fun acelerar(intensidade: Int) {
        println("Acelerar com uma intensidade de: $intensidade")
    }

    override fun recuperarStatus() {
        println("O status do veículo é: “${status}”")
    }

    companion object{
        fun exibeMensagemVelocidadeMaximaLei(){
            println("A velocidade mãxima permitida por lei é de: 120 KM/h")
        }
    }
}

class Moto: Veiculo("Titan 160",2), Eletrificado {
    override fun acelerar() {
        println("Acelerar $nome com $qtnRodas rodas")
        motoEletrico()
    }

    override fun acelerar(intensidade: Int) {
        println("Acelerar com uma intensidade de: $intensidade")
    }

    override fun recuperarStatus() {
        println("O status do veículo é: “${status}”")
    }

    companion object{
        fun exibeMensagemVelocidadeMaximaLei(){
            println("A velocidade mãxima permitida por lei é de: 120 KM/h")
        }
    }

}

interface Eletrificado {
    fun motoEletrico(){
        print("Rodando com motor elétrico: ")
    }
}


fun main() {

    Carro.exibeMensagemVelocidadeMaximaLei()
    var carro = Carro()
    carro.acelerar()
    carro.acelerar(140)
    carro.recuperarStatus()

    println("")

    Moto.exibeMensagemVelocidadeMaximaLei()
    var moto = Moto()
    moto.acelerar()
    moto.acelerar(90)
    moto.recuperarStatus()

}
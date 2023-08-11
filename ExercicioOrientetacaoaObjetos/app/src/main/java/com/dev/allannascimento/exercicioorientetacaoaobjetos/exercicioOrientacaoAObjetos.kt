package com.dev.allannascimento.exercicioorientetacaoaobjetos

fun main() {

    val contaBancaria = ContaBancaria("775","10071","13256")

    contaBancaria.usuarioAutenticado = false

    if ( contaBancaria.usuarioAutenticado ) {
        contaBancaria.saldo = 1000.0
        contaBancaria.recuperarSaldo()
        contaBancaria.depositar(200.0)
        contaBancaria.sacar(500.0)
        contaBancaria.recuperarSaldo()
        contaBancaria.extrato(5)
        contaBancaria.extrato("10/05/2022", "10/06/2022")
    } else {
        println("ACESSO NEGADO: Usuário não autenticado")
    }

}

class ContaBancaria( agencia: String, conta: String, senha: String ) {

    var agencia: String = ""
    var conta: String = ""
    var senha: String = ""
    var usuarioAutenticado: Boolean = false
    var saldo: Double = 0.0

    init {
        usuarioAutenticado = true
    }

    fun recuperarSaldo() {
        println("Seu saldo atual é de: R$ $saldo")
    }

    fun depositar( deposito: Double ): Double {

        saldo = this.saldo + deposito
        println("Você acabou de depositar: R$ $deposito")
        recuperarSaldo()

        return this.saldo

    }

    fun sacar( saque: Double ): Double {

        saldo = this.saldo - saque
        println("Você acabou de sacar: R$ $saque")
        recuperarSaldo()
        return this.saldo

    }

    fun extrato( dias: Int ) {
        println("Extrato dos últimos $dias dias")
    }

    fun extrato( dataInicial: String, dataFinal: String ){
        println("Extrato intervalo $dataInicial e $dataFinal")
    }

}
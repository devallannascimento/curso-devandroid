package com.dev.allannascimento.aulascomponenteslistagemcolecoes.testes

data class Produto(
    var nome: String,
    var preco: Double

) {

    fun desativar(){
        println("Produto: $nome com preço de: $preco desativado.")
    }

}

fun salvarProduto( produto: Produto ){

}

fun main() {

    var produto: Produto? = null

    // Usuário pode escolher ou não um produto
    produto = Produto("Notebook", 1200.00)

    /*if ( produto != null ) {
        salvarProduto(produto)
    }*/

    /*produto?.run { desativar() }*/

    /*val novoObjeto = with( produto ) {
        desativar()
        this
    }*/

    /*produto?.let { item ->
        salvarProduto(produto)
    }*/

    println( "${produto?.nome} - R$${produto?.preco}" )

}
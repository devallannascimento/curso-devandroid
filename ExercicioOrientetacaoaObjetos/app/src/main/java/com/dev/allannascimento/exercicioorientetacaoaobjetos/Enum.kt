package com.dev.allannascimento.exercicioorientetacaoaobjetos

// pedido_realizado
// pagamento_confirmado
// pedido_enviado, pedido_entregue


class Pedido(
    var total: Double = 0.0,
    var itens: String = "",
    var statusPedido: StatusPedido = StatusPedido.PEDIDO_REALIZADO
) {

}

enum class StatusPedido {
    PEDIDO_REALIZADO,
    PAGAMENTO_CONFIRMADO,
    PEDIDO_ENVIADO,
    PEDIDO_ENTREGUE
}

fun main() {

    // Tela de compras
    val pedido = Pedido(125.9,"camiseta, caneca")

    // Pagamento com cartão
    pedido.statusPedido = StatusPedido.PEDIDO_ENTREGUE

    println( "StatusPedido:  ${StatusPedido.PEDIDO_REALIZADO.ordinal}" )

    // Histrico de compra
    if ( pedido.statusPedido == StatusPedido.PEDIDO_REALIZADO ) {
        println("O seu pedido foi realizado: analisando pagamento")
    } else if ( pedido.statusPedido == StatusPedido.PAGAMENTO_CONFIRMADO ) {
        println( "O seu pedido foi confirmado: pedido em separação" )
    } else if ( pedido.statusPedido == StatusPedido.PEDIDO_ENVIADO ) {
        println("O seu pedido foi enviado: prazo de 5 dias úteis pra ser entregue")
    } else if ( pedido.statusPedido == StatusPedido.PEDIDO_ENTREGUE ) {
        println("O seu pedido foi entregue: aproveite a sua compra")
    }

}
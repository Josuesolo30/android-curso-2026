package com.pina.holaandroid

/**
 * Práctica - Funciones de Orden Superior y Ámbito (Pedidos)
 * Josue Piña
 */

// (a) Data class PedidoItem (renombrado para evitar conflictos)
data class PedidoItem(val id: Int, var producto: String, var precio: Double, var activo: Boolean = true)

// (b) Función de orden superior
fun filtrarPedidosItems(pedidos: List<PedidoItem>, criterio: (PedidoItem) -> Boolean): List<PedidoItem> {
    return pedidos.filter(criterio)
}

fun mainPedidos() {
    println("=== SISTEMA DE PROCESAMIENTO DE PEDIDOS ===\n")

    // (c) Crear al menos 4 pedidos usando apply para configurar campos
    val pedidos = listOf(
        PedidoItem(1, "", 0.0).apply {
            producto = "Laptop Gamer"
            precio = 55000.0
            activo = true
        },
        PedidoItem(2, "", 0.0).apply {
            producto = "Mouse Inalámbrico"
            precio = 1200.0
            activo = true
        },
        PedidoItem(3, "", 0.0).apply {
            producto = "Monitor 4K"
            precio = 15000.0
            activo = false // Inactivo
        },
        PedidoItem(4, "", 0.0).apply {
            producto = "Teclado Mecánico"
            precio = 4500.0
            activo = true
        }
    )

    // Filtrar pedidos activos
    val pedidosActivos = filtrarPedidosItems(pedidos) { it.activo }
    println("Pedidos Activos (${pedidosActivos.size}): ${pedidosActivos.map { it.producto }}")

    // Filtrar pedidos caros (> 5000)
    val pedidosCaros = filtrarPedidosItems(pedidos) { it.precio > 5000.0 }
    println("Pedidos Caros (> 5000): ${pedidosCaros.map { "${it.producto} ($${it.precio})" }}")

    // Usar let para imprimir de forma segura el pedido más caro
    println("\nBuscando el pedido más caro...")
    pedidos.maxByOrNull { it.precio }?.let { pedidoCaro ->
        println("Resultado: El producto más costoso es '${pedidoCaro.producto}' con un precio de $${pedidoCaro.precio}")
    }
}

package com.pina.holaandroid

/**
 * Practica 04 - Programación Móvil I
 * Josue Piña
 */

// === Ej. 1 Parámetros nombrados y valores por defecto ===
fun generarRecibo(
    nombreCliente: String,
    monto: Double,
    descuento: Double = 0.0, // opcional
    impuesto: Double = 0.18, // ITBIS 18% por defecto
    moneda: String = "DOP"
): String {
    val subtotal = monto - (monto * descuento)
    val impuestoVal = subtotal * impuesto
    val total = subtotal + impuestoVal
    
    // TODO: Completar el recibo con descuento (%), impuesto y total
    return """
 ===========================
 RECIBO — $moneda
 Cliente   : $nombreCliente
 Subtotal  : ${"%.2f".format(subtotal)}
 Descuento : ${"%.0f%%".format(descuento * 100)}
 Impuesto  : ${"%.2f".format(impuestoVal)}
 TOTAL     : ${"%.2f".format(total)}
 ===========================
 """.trimIndent()
}

// === Ej. 2 Funciones de extensión ===

// -- Extensiones de String --
fun String.esEmail(): Boolean = contains('@') && contains('.')

fun String.aTitulo(): String = split(' ').joinToString(" ") {
    it.replaceFirstChar { c -> c.uppercase() }
}

// TODO: Extensión mascararTarjeta() que oculta los primeros 12 dígitos
fun String.mascararTarjeta(): String {
    return if (length == 16) {
        "*" .repeat(12) + substring(12)
    } else {
        this // Retorna original si no tiene 16 dígitos
    }
}

// -- Extensiones de List<Int> --
fun List<Int>.promedio(): Double = if (isEmpty()) 0.0 else sum().toDouble() / size

// TODO: Extensión aprobados() que retorna lista de notas >= 70
fun List<Int>.aprobados(): List<Int> = filter { it >= 70 }

// TODO: Extensión estadisticas() que imprime min, max y promedio
fun List<Int>.estadisticas() {
    if (isEmpty()) {
        println("Lista vacía")
    } else {
        println("Estadísticas -> Min: ${minOrNull()}, Max: ${maxOrNull()}, Promedio: ${"%.2f".format(promedio())}")
    }
}

// === Ej. 3 Funciones de orden superior y ámbito ===
data class Pedido(val id: Int, var producto: String, var precio: Double, var activo: Boolean = true)

fun filtrarPedidos(pedidos: List<Pedido>, criterio: (Pedido) -> Boolean): List<Pedido> {
    return pedidos.filter(criterio)
}

fun main() {
    println("=== EJERCICIO 1: Parámetros Nombrados ===")
    println(generarRecibo(nombreCliente = "Ana López", monto = 1500.0, descuento = 0.10))
    println(generarRecibo("Pedro Ruiz", 2800.0))
    println(generarRecibo(monto = 500.0, nombreCliente = "Empresa ABC", moneda = "USD", impuesto = 0.0))

    println("\n=== EJERCICIO 2: Funciones de Extensión ===")
    println("Email válido: ${"test@mail.com".esEmail()}")
    println("Título: ${"programacion movil".aTitulo()}")
    println("Tarjeta Mascarada: ${"1234567890123456".mascararTarjeta()}")
    
    val notas = listOf(85, 92, 61, 78, 45, 90)
    println("Promedio de notas: ${notas.promedio()}")
    println("Notas aprobadas: ${notas.aprobados()}")
    notas.estadisticas()

    println("\n=== EJERCICIO 3: Funciones de Ámbito y Orden Superior ===")
    val p1 = Pedido(1, "", 0.0).apply {
        producto = "Smartphone"
        precio = 25000.0
    }
    
    val pedidos = listOf(
        p1,
        Pedido(2, "Audífonos", 1800.0),
        Pedido(3, "Tablet", 18000.0, activo = false),
        Pedido(4, "Cargador", 850.0),
    )

    val activos = filtrarPedidos(pedidos) { it.activo }
    val caros = filtrarPedidos(pedidos) { it.precio > 5000.0 && it.activo }
    
    println("Pedidos activos: ${activos.size}")
    println("Pedidos caros (activos): ${caros.map { it.producto }}")

    val resumen = pedidos
        .filter { it.activo }
        .map { it.precio }
        .also { println("Precios activos: $it") }
        .sum()
    
    println("Total activos: $%.2f".format(resumen))

    // TODO: Usar let para imprimir el producto más caro de forma segura
    pedidos.maxByOrNull { it.precio }?.let { 
        println("Producto más caro detectado: ${it.producto} con un precio de ${it.precio}")
    }
}

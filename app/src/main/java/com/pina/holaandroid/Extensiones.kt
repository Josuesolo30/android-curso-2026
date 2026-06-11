package com.pina.holaandroid

/**
 * Práctica 04 - Funciones de Extensión
 * Josue Piña
 */

// (1) String.esEmail(): Boolean — verifica que contenga '@' y '.'.
fun String.esEmail(): Boolean = this.contains('@') && this.contains('.')

// (2) String.aTitulo(): String — convierte "hola mundo" en "Hola Mundo".
fun String.aTitulo(): String {
    if (this.isEmpty()) return this
    return this.split(' ').joinToString(" ") { palabra ->
        palabra.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    }
}

// (3) String.mascararTarjeta(): String — oculta los primeros 12 digitos de un numero de tarjeta de 16 caracteres.
fun String.mascararTarjeta(): String {
    return if (this.length == 16) {
        "*" .repeat(12) + this.substring(12)
    } else {
        "Número de tarjeta inválido (debe tener 16 dígitos)"
    }
}

// (4) List<Int>.aprobados(): List<Int> — retorna las notas mayores o iguales a 70.
fun List<Int>.aprobados(): List<Int> = this.filter { it >= 70 }

fun main() {
    println("=== PRUEBAS DE FUNCIONES DE EXTENSIÓN ===\n")

    // Prueba 1: esEmail
    val email1 = "josue@gmail.com"
    val email2 = "correo_invalido"
    println("¿'$email1' es email?: ${email1.esEmail()}")
    println("¿'$email2' es email?: ${email2.esEmail()}")

    // Prueba 2: aTitulo
    val frase = "bienvenido al curso de android con kotlin"
    println("\nOriginal: '$frase'")
    println("Título:   '${frase.aTitulo()}'")

    // Prueba 3: mascararTarjeta
    val tarjeta = "1234567890123456"
    println("\nTarjeta original: $tarjeta")
    println("Tarjeta oculta:   ${tarjeta.mascararTarjeta()}")

    // Prueba 4: aprobados
    val notas = listOf(85, 45, 90, 69, 70, 100, 32)
    println("\nTodas las notas: $notas")
    println("Notas aprobadas: ${notas.aprobados()}")
}

package com.pina.holaandroid

/**
 * Practica 02 - Versión Alternativa (Clasificador y FizzBuzz)
 * Josue Piña
 */

/**
 * (a) Función clasificarNota usando 'when' como expresión
 * Cubre todos los rangos solicitados incluyendo validación de límites.
 */
fun clasificarNota(nota: Int): String = when {
    nota < 0 || nota > 100 -> "Nota inválida"
    nota >= 90 -> "Sobresaliente (A)"
    nota >= 80 -> "Muy Bueno (B)"
    nota >= 70 -> "Bueno (C)"
    nota >= 60 -> "Aprobado (D)"
    else -> "Reprobado (F)"
}

/**
 * (b) Función fizzBuzz para el rango 1 al 30
 * Imprime números, reemplazando múltiplos de 3, 5 y 15 según reglas clásicas.
 */
fun fizzBuzz() {
    println("--- Ejecutando FizzBuzz (1-30) ---")
    for (i in 1..30) {
        val output = when {
            i % 15 == 0 -> "FizzBuzz"
            i % 3 == 0 -> "Fizz"
            i % 5 == 0 -> "Buzz"
            else -> i.toString()
        }
        print("$output ")
        // Salto de línea estético cada 10 elementos
        if (i % 10 == 0) println()
    }
    println("\n----------------------------------")
}

/**
 * (c) Función main para demostración con casos de prueba
 */
fun main() {
    println("=== DEMOSTRACIÓN PRÁCTICA 02 ===\n")

    // Pruebas para clasificarNota (mínimo 6 valores)
    val casosPrueba = listOf(105, 95, 82, 70, 64, 50, -10)
    
    println("Resultados de clasificación de notas:")
    casosPrueba.forEach { nota ->
        println(" - Entrada: $nota -> Resultado: ${clasificarNota(nota)}")
    }
    
    println()

    // Ejecución de FizzBuzz
    fizzBuzz()
}

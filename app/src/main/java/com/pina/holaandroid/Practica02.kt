package com.pina.holaandroid

/**
 * Practica 02 - Programación Móvil I
 * Josue Piña
 */

fun main() {
    // === EJERCICIO 1: val, var e inferencia ===
    val nombreCurso = "Programación Móvil I"
    val añoInicio: Int = 2026
    var calificacion: Double = 95.5
    calificacion = 88.0

    println("Curso: $nombreCurso, Año: $añoInicio")
    println("Calificación actual: ${calificacion * 0.3} (30%)")

    val nombreCompleto = "Josue Piña"
    var edad = 20 
    println("Me llamo $nombreCompleto y tengo $edad años")

    println("\n--- Clasificador de Notas ---")
    // === EJERCICIO: Clasificador de notas ===
    val notas = listOf(100.0, 92.5, 85.0, 73.2, 61.0, 45.0, -5.0) 
    for (nota in notas) {
        println("Nota $nota -> ${clasificarNota(nota)}")
    }

    println("\n--- FizzBuzz (1 al 30) ---")
    fizzBuzz()

    println("\n--- Tabla de Multiplicar ---")
    println("Introduce un número para ver su tabla (o presiona Enter para usar el 7):")
    val num = readLine()?.toIntOrNull() ?: 7
    tablaMultiplicar(num)
}

// Parte A — FizzBuzz clásico (1 hasta 30)
fun fizzBuzz() {
    for (i in 1..30) {
        val resultado = when {
            i % 15 == 0 -> "FizzBuzz"
            i % 3 == 0 -> "Fizz"
            i % 5 == 0 -> "Buzz"
            else -> "$i"
        }
        print("$resultado ")
        if (i % 10 == 0) println() 
    }
}

// Parte B — Tabla de multiplicar de un número
fun tablaMultiplicar(n: Int) {
    println("\n=== Tabla del $n ===")
    for (i in 1..10) {
        println("$n x $i = ${n * i}")
    }
}

// Reto adicional (+0.5 pts): Aceptar Double
fun clasificarNota(nota: Double): String = when {
    nota < 0 || nota > 100 -> "Nota inválida"
    nota >= 90 -> "Sobresaliente (A)"
    nota >= 80 -> "Muy Bueno (B)"
    nota >= 70 -> "Bueno (C)"
    nota >= 60 -> "Aprobado (D)"
    else -> "Reprobado (F)"
}

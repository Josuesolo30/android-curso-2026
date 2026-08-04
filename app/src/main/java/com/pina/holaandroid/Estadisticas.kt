package com.pina.holaandroid

/**
 * Práctica - Estadísticas de Calificaciones
 * Josue Piña
 */

fun producirEstadisticas(notas: List<Int>) {
    if (notas.isEmpty()) {
        println("No hay notas para procesar.")
        return
    }

    // 1. Total de estudiantes
    val totalEstudiantes = notas.size

    // 2. Promedio (uso de average)
    val promedio = notas.average()

    // 3. Cantidad de aprobados (uso de filter) y reprobados
    val aprobados = notas.filter { it >= 70 }
    val reprobados = notas.filter { it < 70 }

    // 4. Lista de aprobados ordenada de mayor a menor (uso de sortedByDescending)
    val aprobadosOrdenados = aprobados.sortedByDescending { it }

    // 5. Nota más alta y más baja (uso de first y last sobre la lista ordenada)
    val ordenadasTotal = notas.sortedByDescending { it }
    val notaMasAlta = ordenadasTotal.first()
    val notaMasBaja = ordenadasTotal.last()

    // 6. Porcentaje de aprobados
    val porcentajeAprobados = (aprobados.size.toDouble() / totalEstudiantes) * 100

    // Impresión de resultados
    println("=== REPORTE DE ESTADÍSTICAS ===")
    println("Total estudiantes           : $totalEstudiantes")
    println("Promedio                    : ${"%.2f".format(promedio)}")
    println("Cantidad de aprobados (>=70): ${aprobados.size}")
    println("Cantidad de reprobados      : ${reprobados.size}")
    println("Nota más alta               : $notaMasAlta")
    println("Nota más baja               : $notaMasBaja")
    println("Porcentaje de aprobados     : ${"%.2f".format(porcentajeAprobados)}%")
    println("Lista de aprobados (Desc)   : $aprobadosOrdenados")
    println("===============================")
}

fun mainEstadisticas() {
    val listaNotas = listOf(85, 92, 61, 78, 45, 90, 73, 55, 88, 67)
    producirEstadisticas(listaNotas)
}

/*
SALIDA ESPERADA EN CONSOLA:
=== REPORTE DE ESTADÍSTICAS ===
Total estudiantes           : 10
Promedio                    : 73.40
Cantidad de aprobados (>=70): 6
Cantidad de reprobados      : 4
Nota más alta               : 92
Nota más baja               : 45
Porcentaje de aprobados     : 60.00%
Lista de aprobados (Desc)   : [92, 90, 88, 85, 78, 73]
===============================
*/

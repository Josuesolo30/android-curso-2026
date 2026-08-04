package com.pina.holaandroid

/**
 * Practica 06 - Programación Móvil I
 * Data Classes, Sealed Classes y Companion Objects
 * Josue Piña
 */

// === Ej. 1 data class — Gestor de tareas ===
enum class Prioridad { BAJA, MEDIA, ALTA, CRITICA }

data class Tarea(
    val id: Int,
    val titulo: String,
    val descripcion: String = "",
    val prioridad: Prioridad = Prioridad.MEDIA,
    val completada: Boolean = false,
) {
    // Formato legible
    fun resumen() = "[${if (completada) "X" else " "}] #$id $titulo (${prioridad.name})"
}

// === Ej. 2 sealed class — Resultado de operaciones de red ===
sealed class Resultado<out T> {
    data class Exito<T>(val datos: T) : Resultado<T>()
    data class Error(val mensaje: String, val codigo: Int = 0) : Resultado<Nothing>()
    object Cargando : Resultado<Nothing>()
}

// TODO: Crear una sealed class EstadoConexion
sealed class EstadoConexion {
    object Conectado : EstadoConexion()
    object Desconectado : EstadoConexion()
    data class Reconectando(val intentos: Int) : EstadoConexion()
}

fun mostrarEstadoConexion(estado: EstadoConexion) {
    when (estado) {
        EstadoConexion.Conectado -> println("Conexión establecida con éxito.")
        EstadoConexion.Desconectado -> println("Se ha perdido la conexión a internet.")
        is EstadoConexion.Reconectando -> println("Intentando reconectar... Intento número: ${estado.intentos}")
    }
}

fun obtenerEstudiante(id: Int): Resultado<String> = when (id) {
    1 -> Resultado.Exito("Ana López — Ing. Software")
    2 -> Resultado.Exito("Carlos Ruiz — Ing. Software")
    0 -> Resultado.Cargando
    else -> Resultado.Error("Estudiante $id no encontrado", 404)
}

fun manejarResultado(resultado: Resultado<String>) {
    when (resultado) {
        is Resultado.Exito -> println("Éxito: ${resultado.datos}")
        is Resultado.Error -> println("Error ${resultado.codigo}: ${resultado.mensaje}")
        Resultado.Cargando -> println("Cargando...")
    }
}

// === Ej. 3 companion object — Fábrica de objetos ===
class Producto private constructor(
    val codigo: String,
    val nombre: String,
    val precio: Double,
    val categoria: String,
) {
    companion object {
        const val IMPUESTO = 0.18
        private var contador = 0

        // Factory method
        fun crear(nombre: String, precio: Double, categoria: String): Producto {
            contador++
            val codigo = "PROD-${contador.toString().padStart(4, '0')}"
            return Producto(codigo, nombre, precio, categoria)
        }

        fun totalProductosCreados() = contador

        // TODO: Agregar un método companion buscarPorCategoria
        fun buscarPorCategoria(productos: List<Producto>, cat: String): List<Producto> {
            return productos.filter { it.categoria.equals(cat, ignoreCase = true) }
        }
    }

    val precioConImpuesto: Double get() = precio * (1 + IMPUESTO)

    override fun toString() =
        "[$codigo] $nombre | RD$ %.2f (+ IVA: RD$ %.2f)".format(precio, precioConImpuesto)
}

fun mainPractica06() {
    // --- Pruebas Ejercicio 1 ---
    println("=== EJERCICIO 1: DATA CLASSES ===")
    var tarea1 = Tarea(1, "Instalar Android Studio", prioridad = Prioridad.ALTA)
    val tarea2 = Tarea(2, "Crear repositorio GitHub")
    var tarea3 = Tarea(3, "Completar Práctica 1", prioridad = Prioridad.CRITICA)

    tarea1 = tarea1.copy(completada = true)
    tarea3 = tarea3.copy(completada = true)

    val tareas = listOf(tarea1, tarea2, tarea3)
    println("=== LISTA DE TAREAS ===")
    tareas.forEach { println(it.resumen()) }

    val (id, titulo, _, prioridad, completada) = tarea2
    println("\nTarea $id: '$titulo' - Prioridad: $prioridad - Completada: $completada")

    // TODO: Filtrar y mostrar solo las tareas pendientes ordenadas por prioridad (CRITICA primero)
    println("\n--- Tareas Pendientes (Ordenadas por Prioridad) ---")
    tareas.filter { !it.completada }
        .sortedByDescending { it.prioridad.ordinal }
        .forEach { println(it.resumen()) }

    // --- Pruebas Ejercicio 2 ---
    println("\n=== EJERCICIO 2: SEALED CLASSES ===")
    listOf(1, 2, 0, 99).forEach { id ->
        print("ID $id → ")
        manejarResultado(obtenerEstudiante(id))
    }

    println("\n--- Estado de Conexión ---")
    mostrarEstadoConexion(EstadoConexion.Conectado)
    mostrarEstadoConexion(EstadoConexion.Reconectando(3))
    mostrarEstadoConexion(EstadoConexion.Desconectado)

    // --- Pruebas Ejercicio 3 ---
    println("\n=== EJERCICIO 3: COMPANION OBJECT ===")
    val p1 = Producto.crear("Samsung Galaxy A55", 22000.0, "Smartphones")
    val p2 = Producto.crear("Funda protectora", 850.0, "Accesorios")
    val p3 = Producto.crear("Audífonos Bluetooth", 3200.0, "Accesorios")
    
    val listaProductos = listOf(p1, p2, p3)
    listaProductos.forEach { println(it) }
    
    println("\nTotal de productos creados: ${Producto.totalProductosCreados()}")

    // TODO: Probar buscarPorCategoria
    println("\n--- Búsqueda por Categoría (Accesorios) ---")
    Producto.buscarPorCategoria(listaProductos, "Accesorios").forEach { println(it) }
}

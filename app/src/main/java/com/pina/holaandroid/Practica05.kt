package com.pina.holaandroid

/**
 * Practica 05 - Programación Móvil I
 * Jerarquía de clases y Polimorfismo
 * Josue Piña
 */

// Interface con comportamiento por defecto
interface Evaluable {
    val notaMinima: Int get() = 70
    fun estaAprobado(nota: Int): Boolean = nota >= notaMinima
    fun descripcion(): String
}

// Clase base (open = puede ser heredada)
open class Persona(
    val nombre: String,
    val apellido: String,
    val cedula: String,
) {
    val nombreCompleto: String
        get() = "$nombre $apellido"

    init {
        require(cedula.length == 11) { "Cédula debe tener 11 dígitos" }
    }

    open fun presentarse(): String = "Soy $nombreCompleto, cédula $cedula"

    override fun toString(): String = nombreCompleto
}

// Clase derivada
class Estudiante(
    nombre: String,
    apellido: String,
    cedula: String,
    val matricula: String,
    val carrera: String,
) : Persona(nombre, apellido, cedula), Evaluable {
    private val _notas = mutableListOf<Int>()
    val notas: List<Int> get() = _notas

    fun agregarNota(nota: Int) {
        require(nota in 0..100) { "Nota fuera de rango" }
        _notas.add(nota)
    }

    val promedio: Double
        get() = if (_notas.isEmpty()) 0.0 else _notas.average()

    override fun descripcion() = "Estudiante: $nombreCompleto | Promedio: %.1f".format(promedio)

    override fun presentarse(): String =
        super.presentarse() + " | Matrícula: $matricula | Carrera: $carrera"
}

// TODO: Crear clase Docente que extienda Persona e implemente Evaluable
class Docente(
    nombre: String,
    apellido: String,
    cedula: String,
    val departamento: String,
    val añosExperiencia: Int
) : Persona(nombre, apellido, cedula), Evaluable {
    
    // - Override de presentarse() incluyendo departamento
    override fun presentarse(): String = 
        super.presentarse() + " | Departamento: $departamento | Experiencia: $añosExperiencia años"

    // - descripcion() que muestre nombre y departamento
    override fun descripcion(): String = "Docente: $nombreCompleto | Departamento: $departamento"
}

fun imprimirReporte(personas: List<Persona>) {
    println("\n===== REPORTE DEL SISTEMA =====")
    personas.forEach { persona ->
        // Polimorfismo: cada persona se presenta a su manera
        println(persona.presentarse())
        // Verificar si implementa Evaluable (smart cast)
        if (persona is Evaluable) {
            println(" → ${persona.descripcion()}")
        }
        println("-".repeat(40))
    }
}

fun main() {
    val est1 = Estudiante("Ana", "López", "00112345678", "2024-001", "Ing. Software")
    est1.agregarNota(85)
    est1.agregarNota(92)
    est1.agregarNota(78)

    val est2 = Estudiante("Carlos", "Ruiz", "00198765432", "2024-002", "Ing. Software")
    est2.agregarNota(55)
    est2.agregarNota(68)

    // TODO: Crear 2 instancias de Docente y agregarlas a la lista
    val docente1 = Docente("María", "Díaz", "00145678901", "Ciencias", 12)
    val docente2 = Docente("Pedro", "Soto", "00111223344", "Matemáticas", 8)

    val personas: List<Persona> = listOf(est1, est2, docente1, docente2)
    imprimirReporte(personas)

    // Filtrar solo estudiantes y mostrar los aprobados
    val estudiantesAprobados = personas
        .filterIsInstance<Estudiante>()
        .filter { it.estaAprobado(it.promedio.toInt()) }
    
    println("\nEstudiantes aprobados: ${estudiantesAprobados.map { it.nombre }}")
}

package com.pina.holaandroid

/**
 * Practica 03 - Null Safety (Versión con operadores específicos)
 * Josue Piña
 */

data class ResultadoValidacionSafety(val esValido: Boolean, val mensaje: String)

fun validarUsuarioSafety(nombre: String?, email: String?): ResultadoValidacionSafety {
    // 1. Uso de Safe-call (?.): Limpia el nombre si no es nulo
    val nombreProcesado = nombre?.trim()

    // 2. Uso de Elvis (?:): Si el nombre procesado es nulo, asigna un valor por defecto
    val nombreFinal = nombreProcesado ?: ""

    if (nombreFinal.length < 3) {
        return ResultadoValidacionSafety(false, "Nombre muy corto o inexistente")
    }

    // Validación manual de email para justificar el uso de !!
    if (email == null) {
        return ResultadoValidacionSafety(false, "Email es obligatorio (null)")
    }

    // 3. Uso de Not-null assertion (!!): 
    // Justificación: Se usa después de la validación manual arriba (if email == null),
    // por lo tanto, tenemos la seguridad técnica de que no causará un crash.
    if (!email!!.contains('@')) {
        return ResultadoValidacionSafety(false, "Email inválido: falta el símbolo @")
    }

    return ResultadoValidacionSafety(true, "Usuario '$nombreFinal' con email '${email}' validado correctamente")
}

fun mainPractica03NullSafety() {
    println("=== PRUEBAS DE VALIDACIÓN (Null-Safety) ===\n")

    val casosPrueba = listOf(
        Pair(null, "josue@mail.com"),      // Caso 1: Nombre null
        Pair("Ana", null),                 // Caso 2: Email null
        Pair("Carlos", "carlosmail.com"),  // Caso 3: Email sin @
        Pair("Josue Piña", "pina@mail.com") // Caso 4: Válido
    )

    casosPrueba.forEach { (n, e) ->
        val resultado = validarUsuarioSafety(n, e)
        val estado = if (resultado.esValido) "EXITO" else "ERROR"
        println("[$estado] Input: ($n, $e) -> ${resultado.mensaje}")
    }
}

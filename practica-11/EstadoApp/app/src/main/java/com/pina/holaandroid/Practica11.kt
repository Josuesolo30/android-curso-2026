package com.pina.holaandroid

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

/**
 * Practica 11 - Gestión de Estado en Compose
 * Josue Piña
 */

@Composable
fun ContadorApp() {
    // TODO 3: Cambiar remember por rememberSaveable para persistir tras rotación
    var contador by rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "$contador",
            style = MaterialTheme.typography.displayLarge,
            color = when {
                contador > 0 -> MaterialTheme.colorScheme.primary
                contador < 0 -> MaterialTheme.colorScheme.error
                else -> MaterialTheme.colorScheme.onBackground
            }
        )
        
        // TODO 1: Mostrar un mensaje diferente según el valor del contador
        val mensajeContador = when {
            contador < 0 -> "En números rojos"
            contador == 0 -> "En cero"
            else -> "En positivo"
        }
        Text(text = mensajeContador, style = MaterialTheme.typography.bodyLarge)

        Spacer(Modifier.height(32.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            FilledTonalButton(onClick = { contador-- }) { Text("-") }
            Button(onClick = { contador = 0 }) { Text("Reiniciar") }
            FilledTonalButton(onClick = { contador++ }) { Text("+") }
        }

        Spacer(Modifier.height(16.dp))

        // TODO 2: Agregar un botón Duplicar (x2)
        Button(
            onClick = { contador *= 2 },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Duplicar (x2)")
        }
    }
}

@Composable
fun CampoNombre(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Nombre completo") },
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
    )
}

@Composable
fun FormularioRegistro() {
    var nombre by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var aceptado by rememberSaveable { mutableStateOf(false) }
    
    // Snackbar host state
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Validaciones reactivas
    val nombreValido = nombre.trim().length >= 3
    val emailValido = email.contains('@') && email.contains('.')
    val puedeEnviar = nombreValido && emailValido && aceptado

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                "Registro de Usuario", 
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            CampoNombre(
                value = nombre,
                onValueChange = { nombre = it },
            )

            // TODO 2.1: OutlinedTextField para email con validaciones
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo Electrónico") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = !emailValido && email.isNotEmpty(),
                supportingText = {
                    if (!emailValido && email.isNotEmpty()) {
                        Text("Email inválido", color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = aceptado, onCheckedChange = { aceptado = it })
                Text("Acepto los términos y condiciones")
            }

            Button(
                onClick = { 
                    // TODO 2.2: Mostrar un Snackbar de confirmación
                    scope.launch {
                        snackbarHostState.showSnackbar("Usuario $nombre registrado con éxito")
                    }
                },
                enabled = puedeEnviar,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Registrarse")
            }

            // Indicadores de validación en tiempo real
            if (nombre.isNotEmpty()) {
                Text(
                    text = if (nombreValido) "✓ Nombre válido" else "✗ Mínimo 3 caracteres",
                    color = if (nombreValido) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.error,
                    fontSize = 12.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContador() {
    ContadorApp()
}

@Preview(showBackground = true)
@Composable
fun PreviewFormulario() {
    FormularioRegistro()
}

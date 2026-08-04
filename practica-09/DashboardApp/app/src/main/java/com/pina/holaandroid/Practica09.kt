package com.pina.holaandroid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pina.holaandroid.ui.theme.HolaAndroidTheme

/**
 * Practica 09 - Layouts Avanzados y Listas
 * Josue Piña
 */

data class Afirmacion(val id: Int, val texto: String, val categoria: String)

val afirmaciones = listOf(
    Afirmacion(1, "El código limpio no se escribe, se refactoriza.", "Código"),
    Afirmacion(2, "Un bug encontrado es un bug que ya no puede fallar en producción.", "Debug"),
    Afirmacion(3, "Git commit temprano, git commit seguido.", "Versionamiento"),
    Afirmacion(4, "La documentación es una carta para tu yo futuro.", "Buenas prácticas"),
    Afirmacion(5, "Divide el problema y conquista.", "Algoritmos"),
    Afirmacion(6, "null es un valor, no un error: manéjalo.", "Kotlin"),
    Afirmacion(7, "El emulador es tu laboratorio: experimenta sin miedo.", "Android"),
    Afirmacion(8, "Cada práctica te acerca a tu primer app en producción.", "Motivación"),
    // TODO: Agregar 4 afirmaciones propias
    Afirmacion(9, "El éxito en Android es 1% inspiración y 99% depuración.", "Depuración"),
    Afirmacion(10, "Un buen desarrollador sabe qué copiar y cuándo refactorizar.", "Sabiduría"),
    Afirmacion(11, "Tu mejor app es la que terminas, no la que imaginas.", "Productividad"),
    Afirmacion(12, "La perseverancia vence al stack overflow.", "Mentalidad")
)

@Composable
fun PantallaDashboard() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Encabezado que ocupa el 15% de la altura
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.15f)
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text("Mi Dashboard", color = Color.White,
                fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(16.dp))

        // Fila de estadísticas (20% de la altura)
        Row(modifier = Modifier.fillMaxWidth().weight(0.20f),
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TarjetaStat("Tareas", "12", Icons.Default.List, Modifier.weight(1f))
            TarjetaStat("Completadas", "8", Icons.Default.Check, Modifier.weight(1f))
            TarjetaStat("Pendientes", "4", Icons.Default.Pending, Modifier.weight(1f))
        }

        Spacer(Modifier.height(16.dp))

        Text("Categorías", fontWeight = FontWeight.Bold,
            fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))

        // TODO: LazyRow con chips de categorías
        val categorias = afirmaciones.map { it.categoria }.distinct()
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 8.dp)
        ) {
            items(categorias) { categoria ->
                SuggestionChip(
                    onClick = { },
                    label = { Text(categoria) }
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Text("Actividad reciente", fontWeight = FontWeight.Bold,
            fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))

        // LazyColumn (Ejercicio 2) ocupa el resto del espacio
        ListaAfirmaciones(modifier = Modifier.weight(0.65f))
    }
}

@Composable
fun TarjetaStat(titulo: String, valor: String, icono: ImageVector, modifier: Modifier) {
    Card(modifier = modifier, elevation = CardDefaults.cardElevation(4.dp)) {
        Column(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(icono, contentDescription = titulo,
                tint = MaterialTheme.colorScheme.primary)
            Text(valor, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text(titulo, fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
fun ListaAfirmaciones(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = 16.dp),
    ) {
        items(afirmaciones) { afirmacion ->
            ItemAfirmacion(afirmacion)
        }
    }
}

@Composable
fun ItemAfirmacion(afirmacion: Afirmacion) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp),
    ) {
        Row(modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center,
            ) {
                Text("${afirmacion.id}", fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(afirmacion.categoria, fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold)
                Text(afirmacion.texto, fontSize = 14.sp)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDashboard() {
    HolaAndroidTheme {
        PantallaDashboard()
    }
}

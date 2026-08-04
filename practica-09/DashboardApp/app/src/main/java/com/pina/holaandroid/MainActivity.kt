package com.pina.holaandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HolaAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        PantallaDashboard()
                    }
                }
            }
        }
    }
}

@Composable
fun TarjetaContacto(
    nombre: String,
    cargo: String,
    email: String,
    telefono: String,
    github: String,
) {
    var mostrarInfo by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = nombre.split(' ').map { it.first() }.take(2).joinToString(""),
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Spacer(Modifier.height(16.dp))
                Text(nombre, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text(cargo, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }

        Spacer(Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                FilaContacto(Icons.Default.Email, "Email", email)
                FilaContacto(Icons.Default.Phone, "Teléfono", telefono)
                FilaContacto(Icons.Default.Code, "GitHub", github)
            }
        }

        Spacer(Modifier.height(24.dp))

        Button(onClick = { mostrarInfo = !mostrarInfo }) {
            Text(if (mostrarInfo) "Ocultar info" else "Más información")
        }

        if (mostrarInfo) {
            Spacer(Modifier.height(16.dp))
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Carrera:", fontWeight = FontWeight.Bold)
                    Text("Ingeniería de Software")
                    Spacer(Modifier.height(8.dp))
                    Text("Universidad:", fontWeight = FontWeight.Bold)
                    Text("Universidad Dominico-Americano")
                    Spacer(Modifier.height(8.dp))
                    Text("Semestre:", fontWeight = FontWeight.Bold)
                    Text("Primer Semestre 2026")
                    Spacer(Modifier.height(8.dp))
                    Text("Año de ingreso:", fontWeight = FontWeight.Bold)
                    Text("2026")
                }
            }
        }
    }
}

@Composable
fun FilaContacto(icono: ImageVector, etiqueta: String, valor: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icono,
            contentDescription = etiqueta,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp),
        )
        Spacer(Modifier.width(16.dp))
        Column {
            Text(etiqueta, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(valor, fontSize = 15.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewTarjeta() {
    HolaAndroidTheme {
        TarjetaContacto(
            nombre = "Josue Piña",
            cargo = "Estudiante — Ing. Software",
            email = "josue.pina@tuemail.com",
            telefono = "+1 (809) 000-0000",
            github = "github.com/Josuesolo30"
        )
    }
}

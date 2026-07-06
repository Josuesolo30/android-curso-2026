package com.pina.primerapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pina.primerapp.ui.theme.PrimerAppTheme

private const val TAG = "PrimerApp"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrimerAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaInicio(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun PantallaInicio(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
<<<<<<< HEAD
        // Icono (Ej. 3.24)
=======
>>>>>>> main
        Icon(
            imageVector = Icons.Default.Android,
            contentDescription = "Logo Android",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(64.dp),
        )
        
        Spacer(modifier = Modifier.height(16.dp))

<<<<<<< HEAD
        // Texto con estilo y color (Ej. 3.23)
=======
>>>>>>> main
        Text(
            text = "¡Mi primera app Android!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Desarrollado con Kotlin y Jetpack Compose",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.height(32.dp))

<<<<<<< HEAD
        // Botón 1 (Ej. 2)
=======
>>>>>>> main
        Button(
            onClick = {
                Log.d(TAG, "Botón presionado")
            },
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text("Presióname")
        }

        Spacer(modifier = Modifier.height(12.dp))

<<<<<<< HEAD
        // Botón 2 - Nombre (Ej. 3.22)
=======
>>>>>>> main
        Button(
            onClick = {
                Log.i(TAG, "Nombre del desarrollador: Josue Piña")
            },
            modifier = Modifier.fillMaxWidth(0.7f),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Ver desarrollador")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrevisualizacionInicio() {
    PrimerAppTheme {
        PantallaInicio()
    }
}

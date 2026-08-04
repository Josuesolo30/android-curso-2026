package com.pina.holaandroid

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.pina.holaandroid.ui.theme.HolaAndroidTheme

/**
 * Practica 10 - Navegación y Temas
 * Josue Piña
 */

// Modelo para los ítems del menú inferior
data class ItemNav(val ruta: String, val etiqueta: String, val icono: ImageVector)

val itemsNav = listOf(
    ItemNav("inicio", "Inicio", Icons.Default.Home),
    ItemNav("lista", "Lista", Icons.Default.List),
    ItemNav("perfil", "Perfil", Icons.Default.Person),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppPrincipal() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = backStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi App Android") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
                // TODO: Agrega un IconButton con Icons.Default.Settings a la derecha
                actions = {
                    IconButton(onClick = { /* Acción de configuración */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Configuración")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                itemsNav.forEach { item ->
                    NavigationBarItem(
                        selected = rutaActual == item.ruta,
                        onClick = {
                            navController.navigate(item.ruta) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icono, contentDescription = item.etiqueta) },
                        label = { Text(item.etiqueta) },
                    )
                }
            }
        }
    ) { paddingValues ->
        // El NavHost vive dentro del Scaffold
        NavHost(
            navController = navController,
            startDestination = "inicio",
            modifier = Modifier.padding(paddingValues),
        ) {
            composable("inicio") { PantallaInicioPr10(navController) }
            composable("lista") { PantallaListaPr10(navController) }
            composable("perfil") { PantallaPerfilPr10() }
            // Ej. 3 Navegación con argumentos
            composable(
                route = "detalle/{itemId}",
                arguments = listOf(navArgument("itemId") { type = NavType.IntType })
            ) { backStack ->
                val id = backStack.arguments?.getInt("itemId") ?: 0
                PantallaDetalle(id, navController)
            }
        }
    }
}

@Composable
fun PantallaInicioPr10(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bienvenido a la App", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.navigate("lista") }) {
            Text("Ir a la Lista")
        }
    }
}

@Composable
fun PantallaListaPr10(navController: NavController) {
    // Usamos las afirmaciones de la Practica 09
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Lista de Afirmaciones", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(Modifier.height(8.dp))
        
        // Versión simplificada de la lista para demostrar navegación
        afirmaciones.forEach { afirmacion ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                onClick = { 
                    // TODO: Implementar el click en ItemAfirmacion para navegar al detalle
                    navController.navigate("detalle/${afirmacion.id}") 
                }
            ) {
                ListItem(
                    headlineContent = { Text(afirmacion.texto) },
                    supportingContent = { Text(afirmacion.categoria) },
                    leadingContent = {
                        Badge { Text("${afirmacion.id}") }
                    }
                )
            }
        }
    }
}

@Composable
fun PantallaPerfilPr10() {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Perfil de Usuario", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Text("Nombre: Josue Piña", fontSize = 18.sp)
        Text("Carrera: Ing. Software", color = MaterialTheme.colorScheme.secondary)
    }
}

@Composable
fun PantallaDetalle(itemId: Int, navController: NavController) {
    val afirmacion = afirmaciones.find { it.id == itemId }
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (afirmacion != null) {
            Text(afirmacion.categoria, style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(16.dp))
            Text(afirmacion.texto,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center)
        } else {
            Text("Afirmación no encontrada")
        }
        Spacer(Modifier.height(32.dp))
        OutlinedButton(onClick = { navController.popBackStack() }) {
            Text("Regresar")
        }
    }
}

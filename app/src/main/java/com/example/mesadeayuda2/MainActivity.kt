package com.example.mesadeayuda2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.MesaAyudaFinal.ui.theme.screens.AdminLoginSuccessScreen
import com.example.MesaAyudaFinal.ui.theme.screens.LoginScreen
import com.example.MesaAyudaFinal.ui.theme.screens.LoginSuccessScreen
import com.example.mesadeayuda2.screens.TechnicianCasesScreen
import com.example.mesadeayuda2.ui.theme.MesaDeAyuda2Theme
import com.example.mesadeayuda2.ui.theme.screens.AdminLoginScreen
import com.example.mesadeayuda2.ui.theme.screens.CheckStatusScreen
import com.example.mesadeayuda2.ui.theme.screens.LoginTechnicianScreen
import com.example.mesadeayuda2.ui.theme.screens.NewCaseScreen
import com.example.mesadeayuda2.ui.theme.screens.RegisterScreen
import com.example.mesadeayuda2.ui.theme.screens.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MesaDeAyuda2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigation() // Agregamos la navegación
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        // Pantallas de inicio
        composable("welcome") { WelcomeScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("admin_login") { AdminLoginScreen(navController) }
        composable("admin_login_success") { AdminLoginSuccessScreen() }

        // Pantalla de Login con verificación de Email
        composable(
            "login_success/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            LoginSuccessScreen(
                username = email,
                onRegisterCaseClick = { navController.navigate("new_case") },
                onCheckStatusClick = { navController.navigate("check_status/$email") }
            )
        }

        // Pantalla de Nuevo Caso
        composable("new_case") { NewCaseScreen() }

        // Pantalla de Verificación de Estado
        composable(
            "check_status/{nombreUsuario}",
            arguments = listOf(navArgument("nombreUsuario") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombreUsuario = backStackEntry.arguments?.getString("nombreUsuario") ?: ""
            CheckStatusScreen(navController = navController, nombreUsuario = nombreUsuario)
        }

        // Pantalla de inicio de sesión para Técnicos de Soporte
        composable("login_technician") { LoginTechnicianScreen(navController) }

        // Pantalla de casos técnicos
        composable("technician_cases") { TechnicianCasesScreen(navController) }
    }
}

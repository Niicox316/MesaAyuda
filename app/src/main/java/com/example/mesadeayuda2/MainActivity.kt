package com.example.mesadeayuda2

import WelcomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mesadeayuda2.ui.theme.MesaDeAyuda2Theme
import com.example.mesadeayuda2.ui.theme.screens.*  // Asegúrate de importar todas tus pantallas

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MesaDeAyuda2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("admin_login") { AdminLoginScreen(navController) }
        composable("admin_login_success") { AdminLoginSuccessScreen() }

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

        composable("new_case") { NewCaseScreen() }

        composable("check_status/{nombreUsuario}",
            arguments = listOf(navArgument("nombreUsuario") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombreUsuario = backStackEntry.arguments?.getString("nombreUsuario") ?: ""
            CheckStatusScreen(navController = navController, nombreUsuario = nombreUsuario)
        }

        // Nueva pantalla para los casos del técnico de soporte
        composable("technician_cases") { TechnicianCasesScreen() }
    }
}
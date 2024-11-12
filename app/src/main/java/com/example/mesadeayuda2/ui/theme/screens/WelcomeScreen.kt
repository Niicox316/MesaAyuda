import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.scale
import androidx.navigation.NavController
import com.example.mesadeayuda2.R // Importar la clase R para acceder a los recursos

@Composable
fun WelcomeScreen(navController: NavController) {
    // Cargar la imagen desde el drawable
    val backgroundImage = painterResource(id = R.drawable.fondo_menu)

    // Animación de escala para el título con mayor duración
    val scale by animateFloatAsState(
        targetValue = 1f, // Valor final (escala normal)
        animationSpec = tween(
            durationMillis = 3000, // Duración de la animación (3 segundos)
            delayMillis = 500 // Tiempo de retraso antes de que la animación empiece (0.5 segundos)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Asegúrate de que el fondo no interfiera con la imagen
    ) {
        // Imagen de fondo en orientación vertical
        Image(
            painter = backgroundImage,
            contentDescription = "Fondo de menú",
            modifier = Modifier
                .fillMaxSize(), // Ocupa todo el espacio disponible
            contentScale = ContentScale.FillHeight // Ajuste de imagen vertical
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título de Bienvenida con animación de escala
            Text(
                text = "Bienvenido a Mesa de Ayuda IT",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = Color.Black,
                    fontSize = 30.sp // Aquí se establece un tamaño fijo de la fuente
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .scale(scale), // Aplicar la escala animada
                textAlign = androidx.compose.ui.text.style.TextAlign.Center // Centrar el título
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón Registrarse
            Button(
                onClick = { navController.navigate("register") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp), // Bordes más redondeados
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Icon(Icons.Filled.PersonAdd, contentDescription = "Registrar", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Registrarse", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón Ingresar como usuario
            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp), // Bordes más redondeados
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
            ) {
                Icon(Icons.Filled.Login, contentDescription = "Ingresar como usuario", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Ingresar como usuario", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón Ingresar como técnico de soporte
            Button(
                onClick = { navController.navigate("technician_cases") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp), // Bordes más redondeados
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF673AB7))
            ) {
                Icon(Icons.Filled.Engineering, contentDescription = "Ingresar como técnico", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Ingresar como técnico de soporte", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón Administrador
            Button(
                onClick = { navController.navigate("admin_login") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp), // Bordes más redondeados
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9C27B0))
            ) {
                Icon(Icons.Filled.AdminPanelSettings, contentDescription = "Administrador", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Administrador", color = Color.White)
            }
        }
    }
}

package com.example.mesadeayuda2.ui.theme.screens

import android.app.DatePickerDialog
import android.net.Uri
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

@Composable
fun NewCaseScreen() {
    var date by remember { mutableStateOf("") }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var sedeSolicitante by remember { mutableStateOf("") }
    var tipoSolicitud by remember { mutableStateOf("") }
    var tipoSolicitudOtro by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") }
    var imageUri: Uri? by remember { mutableStateOf(null) }
    var nombreUsuario by remember { mutableStateOf("") }
    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance()

    val sedes = listOf("Calera", "Guasca", "Sopó", "Planta de Producción", "Cajicá", "Chía", "Multiparque", "Calle 80", "Tenjo")
    val tiposDeSolicitud = listOf(
        "Acceso", "Cambio", "Nuevas Instalaciones", "Actualización de Software",
        "Recuperación de Datos", "Capacitación o Formación", "Incidencias de Seguridad",
        "Creación de Informes", "Aumento de Recursos", "Nuevas Herramientas",
        "Eliminación de Software", "Migración de Datos", "Otro"
    )

    var expandedSede by remember { mutableStateOf(false) }
    var expandedSolicitud by remember { mutableStateOf(false) }

    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        },
        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
    ).apply {
        datePicker.minDate = System.currentTimeMillis()
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> imageUri = uri }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White) // Fondo blanco claro
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Registrar Nuevo Caso",
            style = MaterialTheme.typography.h5.copy(color = Color.Black),
            modifier = Modifier.padding(bottom = 20.dp)
        )

        OutlinedTextField(
            value = nombreUsuario,
            onValueChange = { nombreUsuario = it },
            label = { Text("Nombre de Usuario") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            trailingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Ícono de Usuario")
            }
        )

        // Date Picker
        DatePickerInput(date = date, onClick = { datePickerDialog.show() })

        // Sede Solicitud Dropdown
        DropdownInput(
            value = sedeSolicitante,
            onValueChange = { sedeSolicitante = it },
            label = "Sede Solicitante",
            options = sedes,
            expanded = expandedSede,
            onExpandedChange = { expandedSede = it }
        )

        // Tipo de Solicitud Dropdown
        DropdownInput(
            value = tipoSolicitud,
            onValueChange = { tipoSolicitud = it },
            label = "Tipo de Solicitud",
            options = tiposDeSolicitud,
            expanded = expandedSolicitud,
            onExpandedChange = { expandedSolicitud = it }
        )

        // Otro tipo de solicitud (if selected)
        if (tipoSolicitud == "Otro") {
            OtherRequestInput(tipoSolicitudOtro = tipoSolicitudOtro) { tipoSolicitudOtro = it }
        }

        // Description
        OutlinedTextField(
            value = description,
            onValueChange = { description = it.copy(text = it.text.take(200)) },
            label = { Text("Descripción Detallada") },
            modifier = Modifier.fillMaxWidth().padding(4.dp),
            maxLines = 5,
            trailingIcon = {
                Icon(imageVector = Icons.Default.Description, contentDescription = "Descripción Detallada")
            }
        )
        TextRemainingCharacters(description.text.length)

        Spacer(modifier = Modifier.height(16.dp))

        // Image Button
        SelectImageButton(galleryLauncher)

        // Display selected image
        imageUri?.let { Image(painter = rememberImagePainter(it), contentDescription = null, modifier = Modifier.size(200.dp)) }

        Spacer(modifier = Modifier.height(16.dp))

        // Submit Button
        SubmitButton(
            onClick = {
                val caseData = hashMapOf(
                    "nombreUsuario" to nombreUsuario,
                    "fecha" to date,
                    "descripcion" to description.text,
                    "sedeSolicitante" to sedeSolicitante,
                    "tipoSolicitud" to tipoSolicitud,
                    "tipoSolicitudOtro" to tipoSolicitudOtro.text,
                    "imageUri" to imageUri.toString(),
                    "estado" to "Abierto"
                )

                db.collection("cases").add(caseData)
                    .addOnSuccessListener {
                        Log.d("NewCase", "Caso registrado correctamente")
                        // Reset fields
                        nombreUsuario = ""
                        date = ""
                        description = TextFieldValue("")
                        sedeSolicitante = ""
                        tipoSolicitud = ""
                        tipoSolicitudOtro = TextFieldValue("")
                        imageUri = null
                    }
                    .addOnFailureListener { e ->
                        errorMessage = "Error al registrar el caso: ${e.message}"
                    }
            }
        )

        // Error message
        errorMessage.takeIf { it.isNotEmpty() }?.let {
            Text(text = it, color = Color.Red, style = MaterialTheme.typography.body2, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@Composable
fun DatePickerInput(date: String, onClick: () -> Unit) {
    OutlinedTextField(
        value = date,
        onValueChange = { },
        label = { Text("Fecha") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onClick() },
        readOnly = true,
        trailingIcon = {
            Icon(imageVector = Icons.Default.Event, contentDescription = "Seleccionar Fecha")
        }
    )
}

@Composable
fun DropdownInput(value: String, onValueChange: (String) -> Unit, label: String, options: List<String>, expanded: Boolean, onExpandedChange: (Boolean) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = value,
            onValueChange = { },
            label = { Text(label) },
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
                .clickable { onExpandedChange(true) },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { onExpandedChange(!expanded) }) {
                    Icon(imageVector = Icons.Default.ListAlt, contentDescription = "Seleccionar")
                }
            }
        )

        DropdownMenu(expanded = expanded, onDismissRequest = { onExpandedChange(false) }) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onValueChange(option)
                    onExpandedChange(false)
                }) {
                    Text(text = option)
                }
            }
        }
    }
}

@Composable
fun OtherRequestInput(tipoSolicitudOtro: TextFieldValue, onValueChange: (TextFieldValue) -> Unit) {
    OutlinedTextField(
        value = tipoSolicitudOtro,
        onValueChange = { onValueChange(it.copy(text = it.text.take(50))) },
        label = { Text("Especifique Otro Tipo de Solicitud") },
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        maxLines = 2
    )
    Text(
        text = "${50 - tipoSolicitudOtro.text.length} caracteres restantes",
        style = MaterialTheme.typography.body2,
        color = if (tipoSolicitudOtro.text.length > 50) Color.Red else Color.Gray,
        modifier = Modifier.padding(start = 8.dp)
    )
}

@Composable
fun TextRemainingCharacters(charCount: Int) {
    Text(
        text = "${200 - charCount} caracteres restantes",
        style = MaterialTheme.typography.body2,
        color = if (charCount > 200) Color.Red else Color.Gray,
        modifier = Modifier.padding(start = 8.dp)
    )
}

@Composable
fun SelectImageButton(galleryLauncher: ManagedActivityResultLauncher<String, Uri?>) {
    Button(
        onClick = { galleryLauncher.launch("image/*") },
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = "Seleccionar Imagen")
    }
}

@Composable
fun SubmitButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6200EE))
    ) {
        Text(text = "Registrar Caso", color = Color.White)
    }
}

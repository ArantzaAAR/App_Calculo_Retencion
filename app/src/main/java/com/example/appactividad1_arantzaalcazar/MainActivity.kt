package com.example.appactividad1_arantzaalcazar

import android.R
import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appactividad1_arantzaalcazar.ui.theme.AppActividad1_ArantzaAlcazarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppActividad1_ArantzaAlcazarTheme {
                MyApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MyApp() {
    var edad by rememberSaveable { mutableStateOf("") }
    var grupoProfesional by rememberSaveable { mutableStateOf("") }
    var numeroPagas by rememberSaveable { mutableStateOf("") }
    var salarioBrutoAnual by rememberSaveable { mutableStateOf("") }
    var gradoDiscapacidad by rememberSaveable { mutableStateOf("") }
    var estadoCivil by rememberSaveable { mutableStateOf("") }
    var numeroHijos by rememberSaveable { mutableStateOf("") }
    var mensaje by rememberSaveable {mutableStateOf<String?>(null)}

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Cálculo de retención de impuestos")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Calculadora de impuestos",
                )
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            shape = MaterialTheme.shapes.large,
            color = Color(0xFF8AA867),
            shadowElevation = 15.dp,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Título
                Text(
                    modifier = Modifier.padding(bottom = 24.dp),
                    text = "Introduce tus datos",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 26.sp
                    )
                )

                TextField(
                    value = edad,
                    onValueChange = { edad = it.trim() },
                    label = { Text("Edad:") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                TextField(
                    value = grupoProfesional,
                    onValueChange = { grupoProfesional = it.trim() },
                    label = { Text("Grupo Profesional:") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                TextField(
                    value = numeroPagas,
                    onValueChange = { numeroPagas = it.trim() },
                    label = { Text("Número de Pagas:") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                TextField(
                    value = salarioBrutoAnual,
                    onValueChange = { salarioBrutoAnual = it.trim() },
                    label = { Text("Salario Bruto Anual:") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                )

                TextField(
                    value = gradoDiscapacidad,
                    onValueChange = { gradoDiscapacidad = it.trim() },
                    label = { Text("Grado de Discapacidad:") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                )
                TextField(
                    value = estadoCivil,
                    onValueChange = { estadoCivil = it.trim() },
                    label = { Text("Estado Civil:") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                )
                TextField(
                    value = numeroHijos,
                    onValueChange = { numeroHijos = it.trim() },
                    label = { Text("Número de hijos:") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                )

                Spacer(Modifier.height(32.dp))

                Button(
                    onClick ={
                        val edad = edad.toIntOrNull()
                        mensaje = when {
                            edad == null ->"Edad no válida"
                            edad < 18 -> "Eres menor de edad. No pudeo calcular la retención"
                            else ->"Edad correcta"
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray
                    )


                ){
                    Text("Comprobación de datos")
                }
                Spacer(Modifier.height(24.dp))
                if (mensaje != null) {
                    Text(
                        text = mensaje!!,
                        style = TextStyle(
                            color= Color.Black,
                            fontSize = 30.sp
                        )
                    )
                }
            }
        }
    }
}
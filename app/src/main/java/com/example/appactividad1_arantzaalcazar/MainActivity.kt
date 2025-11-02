package com.example.appactividad1_arantzaalcazar

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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "formulario"
    ) {
        composable("formulario") {
            PantallaFormulario(
                onNavegar = {
                    navController.navigate("resultados")
                }
            )
        }

        composable("resultados") {
            val resultados = resultadosGlobales ?: ResultadoCalculo(
                salarioBruto = 0.0,
                retencionIRPF = 0.0,
                deducciones = 0.0,
                salarioNeto = 0.0
            )
            PantallaResultados(
                onVolver = {
                    navController.popBackStack()
                },
                resultados = resultados
            )
        }
    }
}

//PRIMERA PANTALLA PARA RECOGER LOS DATOS
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaFormulario(onNavegar: () -> Unit) {
    //valores de cada campo del formulario
    var edad by remember { mutableStateOf("") }//uso remember, para que borre el contenido cuando vuelva de la pantalla2
    var grupoProfesional by remember { mutableStateOf("") }
    var numeroPagas by remember { mutableStateOf("") }
    var salarioBrutoAnual by remember { mutableStateOf("") }
    var gradoDiscapacidad by remember { mutableStateOf("") }
    var estadoCivil by remember { mutableStateOf("") }
    var numeroHijos by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text("Cálculo de retención de impuestos")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = Color.Black,
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
                        .padding(bottom = 16.dp)
                )

                TextField(
                    value = gradoDiscapacidad,
                    onValueChange = { gradoDiscapacidad = it.trim() },
                    label = { Text("Grado de Discapacidad:") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                TextField(
                    value = estadoCivil,
                    onValueChange = { estadoCivil = it.trim() },
                    label = { Text("Estado Civil:") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                TextField(
                    value = numeroHijos,
                    onValueChange = { numeroHijos = it.trim() },
                    label = { Text("Número de hijos:") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                Spacer(Modifier.height(28.dp))

                Button(
                    onClick = {
                        //VALIDACIÓN Y CÁLCULO
                        //validamos los datos
                        val datosUsuario = validacionEntrada(
                            edad,
                            grupoProfesional,
                            numeroPagas,
                            salarioBrutoAnual,
                            gradoDiscapacidad,
                            estadoCivil,
                            numeroHijos
                        )

                        if (datosUsuario != null) {
                            //utilizo la funcion calcularSalarioNeto
                            val resultados = calcularSalarioNeto(datosUsuario)

                            //guardo los resultados globalmente
                            resultadosGlobales = resultados
                            onNavegar()
                        } else {
                            mensaje = "Por favor, introduce un salario válido"
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                ) {
                    Text("Ir a cálculo de Salario Neto")
                }

                Spacer(Modifier.height(22.dp))

                if (mensaje != null) {
                    Text(
                        text = mensaje!!,
                        style = TextStyle(
                            color = Color.Red,
                            fontSize = 16.sp
                        )
                    )
                }
            }
        }
    }
}

//SEGUNDA PANTALLA PARA MOSTRAR LOS RESULTADOS
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaResultados(
    onVolver: () -> Unit,
    resultados: ResultadoCalculo
) {
    //para calcular salario neto mensual
    val salarioNetoMensual = resultados.salarioNeto / 12

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text("Resultado del cálculo")
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = Color(0xFF8AA867)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 32.dp),
                    text = "Resultado del Salario",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 26.sp,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = "Salario Bruto: ${String.format("%.2f", resultados.salarioBruto)}€",
                    style = TextStyle(fontSize = 18.sp)
                )
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = "Retención IRPF: ${String.format("%.2f", resultados.retencionIRPF)}€",
                    style = TextStyle(fontSize = 18.sp)
                )
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = "Deducciones: ${String.format("%.2f", resultados.deducciones)}€",
                    style = TextStyle(fontSize = 18.sp)
                )
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = "Salario neto anual: ${String.format("%.2f", resultados.salarioNeto)}€",
                    style = TextStyle(fontSize = 18.sp)
                )
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = "Salario neto mensual: ${String.format("%.2f", salarioNetoMensual)}€",
                    style = TextStyle(fontSize = 18.sp)
                )
                Button(
                    onClick = {
                        onVolver()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                ) {
                    Text("Volver")
                }
            }
        }
    }
}

//clase para almacenar los datos del usuario
data class DatosUsuario(
    val edad: Int,
    val grupoProfesional: String,
    val numeroPagas: Int,
    val salarioBrutoAnual: Double,
    val gradoDiscapacidad: String,
    val estadoCivil: String,
    val numeroHijos: Int
)

//clase para almacenar los resultados del cálculo
data class ResultadoCalculo(
    val salarioBruto: Double,
    val retencionIRPF: Double,
    val deducciones: Double,
    val salarioNeto: Double
)

var resultadosGlobales: ResultadoCalculo? = null

//FUNCION DE VALIDACIÓN DE DATOS
private fun validacionEntrada(
    edad: String,
    grupoProfesional: String,
    numeroPagas: String,
    salarioBrutoAnual: String,
    gradoDiscapacidad: String,
    estadoCivil: String,
    numeroHijos: String
): DatosUsuario? {
    return try {
        DatosUsuario(
            edad = edad.toInt(),
            grupoProfesional = grupoProfesional,
            numeroPagas = numeroPagas.toInt(),
            salarioBrutoAnual = salarioBrutoAnual.toDouble(),
            gradoDiscapacidad = gradoDiscapacidad,
            estadoCivil = estadoCivil,
            numeroHijos = numeroHijos.toIntOrNull() ?: 0
        )
    } catch (e: Exception) {
        null
    }
}

private fun calcularSalarioNeto(datos: DatosUsuario): ResultadoCalculo {
    //cálculo del salario bruto mensual
    val salarioBruto = datos.salarioBrutoAnual

    //cálculo de retencion IRPF
    var retencionIRPF = salarioBruto * 0.17
    retencionIRPF -= datos.numeroHijos * 500.0

    //calculo de deducciones
    var deducciones = 0.0
    deducciones += datos.numeroHijos * 200.0

    val salarioNeto = salarioBruto - (retencionIRPF + deducciones)

    return ResultadoCalculo(
        salarioBruto = salarioBruto,
        retencionIRPF = retencionIRPF,
        deducciones = deducciones,
        salarioNeto = salarioNeto
    )
}


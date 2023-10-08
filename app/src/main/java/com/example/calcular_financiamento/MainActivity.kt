package com.example.calcular_financiamento

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calcular_financiamento.ui.theme.Calcular_financiamentoTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Calcular_financiamentoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Layout()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Input (
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
){
    TextField(
        value = value,
        onValueChange = onValueChange
    )
}

@Composable
fun Layout(){

    var infoValorProdutoFinal by remember { mutableStateOf("") }
    var infoValorEntrada by remember { mutableStateOf("") }
    var infoTaxa by remember { mutableStateOf("") }
    var infoPrestacoes by remember { mutableStateOf("") }
    var ValPrestacoes by remember { mutableStateOf("") }

    val ValorProdutoFinal = infoValorProdutoFinal.toDouble()
    val ValorEntrada = infoValorEntrada.toDouble()
    val Taxa = infoTaxa.toDouble()
    val Prestacoes = infoPrestacoes.toDouble()


    val onclick = { ValPrestacoes = FinancingCalc(ValorProdutoFinal = ValorProdutoFinal, ValorEntrada = ValorEntrada , Taxa = Taxa, Prestacoes = Prestacoes)}

    Column {
        Input(
            value = infoValorProdutoFinal,
            onValueChange = { infoValorProdutoFinal = it },
            label = "Digite o valor do produto final",
        )

        Input(
            value = infoValorEntrada,
            onValueChange = { infoValorEntrada = it },
            label = "Digite o valor de entrada",
        )

        Input(
            value = infoTaxa,
            onValueChange = { infoTaxa = it },
            label = "Digite o valor do referente a taxa",
        )

        Input(
            value = infoPrestacoes,
            onValueChange = { infoPrestacoes = it },
            label = "Digite a quantidade de prestações",
        )

        Button(onClick = { onclick }) {

        }

    }
}

fun FinancingCalc(
    ValorProdutoFinal: Double,
    ValorEntrada: Double,
    Taxa: Double,
    Prestacoes: Double
): String{
    val ValPrestacoes = ((Math.pow((1+Taxa), Prestacoes)) * Prestacoes) / ((Math.pow((1+Taxa), Prestacoes)) -1)
    val result = NumberFormat.getNumberInstance().format(ValPrestacoes)
    return result

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Calcular_financiamentoTheme {
        Layout()
    }
}
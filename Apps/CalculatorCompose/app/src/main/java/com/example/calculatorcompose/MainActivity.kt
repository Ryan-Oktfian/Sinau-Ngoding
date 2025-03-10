package com.example.calculatorcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorcompose.ui.theme.CalculatorComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorCompose()
        }
    }
}

@Composable
fun CalculatorCompose() {
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

        Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = result.ifEmpty { input.ifEmpty { "0" } },
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )

        val buttons = listOf(
            listOf("1", "2", "3", "/"),
            listOf("4", "5", "6", "*"),
            listOf("7", "8", "9", "-"),
            listOf("C", "0", "=", "+")
        )

        buttons.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { label ->
                    Button(
                        onClick = {
                            when (label) {
                                "C" -> {
                                    input = ""
                                    result = ""
                                }
                                "=" -> {
                                    result = try {
                                        val res = eval(input)
                                        if (res % 1.0 == 0.0) res.toInt().toString() else res.toString()
                                    } catch (e: Exception) {
                                        "Error"
                                    }
                                }
                                else -> input += label
                            }
                        },
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color.Magenta, shape = RoundedCornerShape(50.dp))
                    ) {
                        Text(text = label, fontSize = 24.sp, color = Color.White)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

fun eval(expression: String): Double {
    return try {
        val operators = listOf('+', '-', '*', '/')
        var num1 = ""
        var num2 = ""
        var operator: Char? = null

        for (char in expression) {
            if (char in operators) {
                operator = char
            } else {
                if (operator == null) {
                    num1 += char
                } else {
                    num2 += char
                }
            }
        }

        val number1 = num1.toDoubleOrNull() ?: return Double.NaN
        val number2 = num2.toDoubleOrNull() ?: return Double.NaN

        when (operator) {
            '+' -> number1 + number2
            '-' -> number1 - number2
            '*' -> number1 * number2
            '/' -> if (number2 != 0.0) number1 / number2 else Double.NaN
            else -> Double.NaN
        }
    } catch (e: Exception) {
        Double.NaN
    }
}

@Preview(showBackground = true)
@Composable
fun previewCalculator() {
    CalculatorCompose()
}
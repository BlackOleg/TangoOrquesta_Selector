package com.ivoleg.tangoorquesta_selector
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposerApp()
        }
    }
}

@Composable
fun ComposerApp() {
    val composers = listOf("Típica Victor",
        "Juan D'Arienzo",
        "Osvaldo Pugliese",
        "Aníbal Troilo",
        "Carlos Di Sarli",
        "Miguel Caló",
        "Alfredo De Angelis",
        "Julio De Caro",
        "Francisco Canaro",
        "Osvaldo Fresedo",
        "Lucio Demare",
        "Rodolfo Biagi",
        "Ángel D'Agostino",
        "Ricardo Tanturi",
        "Pedro Laurenz",
        "Enrique Rodríguez",
        "Florindo Sassone",
        "Héctor Varela",
        "Edgardo Donato",
        "Alberto Castillo",
        "José Basso",
        "Alfredo Gobbi",
        "Astor Piazzolla",
        "Color Tango",
        "Los Solistas de D'Arienzo",
        "Don Panch0",
        "Osvaldo Requena",
        "Emilio Balcarce",
        "Francini-Pontier",
        "Experemental Mix")
    var selectedComposer by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        if (selectedComposer.isNotEmpty()) {
            RunningText(text = selectedComposer)
        }

        Button(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {
            Text("Choose Composer")
        }

        if (showDialog) {
            ComposerDialog(
                composers = composers,
                onDismiss = { showDialog = false },
                onComposerSelected = {
                    selectedComposer = it
                    showDialog = false
                }
            )
        }
    }
}

@Composable
fun RunningText(text: String) {
    val infiniteTransition = rememberInfiniteTransition()
    val offsetX by infiniteTransition.animateFloat(
        initialValue = 1000f,
        targetValue = -1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 98.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.offset(x = offsetX.dp)
        )
    }
}

@Composable
fun ComposerDialog(
    composers: List<String>,
    onDismiss: () -> Unit,
    onComposerSelected: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Choose a Composer") },
        text = {
            Column {
                composers.forEach { composer ->
                    TextButton(
                        onClick = { onComposerSelected(composer) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(composer)
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
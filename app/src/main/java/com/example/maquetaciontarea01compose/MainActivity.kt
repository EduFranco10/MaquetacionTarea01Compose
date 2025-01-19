package com.example.maquetaciontarea01compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.maquetaciontarea01compose.ui.theme.MaquetacionTarea01ComposeTheme
import com.example.maquetaciontarea01compose.ui.view.RegisterScreen
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaquetacionTarea01ComposeTheme {
                RegisterScreen()
            }
        }
    }
}






package com.example.lazycolumnv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lazycolumnv2.navigation.AppNavigation
import com.example.lazycolumnv2.ui.theme.LazyColumnV2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyColumnV2Theme {
                AppNavigation()
            }
        }
    }
}
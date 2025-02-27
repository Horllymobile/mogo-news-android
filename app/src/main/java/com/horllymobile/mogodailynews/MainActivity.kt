package com.horllymobile.mogodailynews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.horllymobile.mogodailynews.ui.MainUi
import com.horllymobile.mogodailynews.ui.theme.MogoDailyNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MogoDailyNewsTheme {
                MainUi()
            }
        }
    }
}

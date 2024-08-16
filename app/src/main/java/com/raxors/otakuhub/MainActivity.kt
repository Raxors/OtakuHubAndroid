package com.raxors.otakuhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raxors.otakuhub.ui.navigation.RootNav
import com.raxors.otakuhub.ui.screens.main.MainScreen
import com.raxors.otakuhub.ui.theme.OtakuHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OtakuHubTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RootNav(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
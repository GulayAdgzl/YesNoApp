package com.glyadgzl.random

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.glyadgzl.random.di.RandomYesNoModel
import com.glyadgzl.random.model.YesNoResponse
import com.glyadgzl.random.ui.theme.RandomTheme
import com.glyadgzl.random.viewmodels.YesNoViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.glyadgzl.random.screens.YesNoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomTheme {
                Scaffold() {
                    Box(modifier = Modifier.padding(it).background(Color.White)){
                        // Provide ViewModel
                        val viewModel: YesNoViewModel = hiltViewModel()
                        callAPI(viewModel)
                        val randomData: State<YesNoResponse?> = viewModel.randomData.collectAsState()



                        YesNoScreen(randomData)
                    }
                }
            }
        }
    }
}

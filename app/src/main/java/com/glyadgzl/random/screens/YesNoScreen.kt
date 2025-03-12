package com.glyadgzl.random.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun YesNoScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "YesNoScreen")
    }
}

@Preview(name = "YesNoScreen")
@Composable
private fun PreviewYesNoScreen() {
    YesNoScreen()
}
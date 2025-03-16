package com.glyadgzl.random.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Yes(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "Yes")
    }
}

@Preview(name = "Yes")
@Composable
private fun PreviewYes() {
    Yes()
}
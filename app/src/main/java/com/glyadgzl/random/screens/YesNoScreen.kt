package com.glyadgzl.random.screens

import android.content.Context
import android.net.Uri
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import com.glyadgzl.random.model.YesNoResponse
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
import com.google.android.exoplayer2.ui.StyledPlayerView
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.glyadgzl.random.viewmodels.YesNoViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

/*
@Composable
fun YesNoScreen(videoUri: Uri,randomData:State<YesNoResponse?>) {
    val context = LocalContext.current
    val exoPlayer = remember { context.buildExoPlayer(videoUri) }

    DisposableEffect(
        AndroidView(
            factory = { it.buildPlayerView(exoPlayer) },
            modifier = Modifier.fillMaxSize()
        )
    ) {
        onDispose {
            exoPlayer.release()
        }
    }

    if(randomData.value?.results!=null&& randomUserData.value!!.results.size>0){
        val length= randomUserData.value?.results?.size
        println("LENGTH:>>>>>>> ${length}")
        LazyColumn {
            items(randomUserData.value!!.results) { result ->
                UserListItem(result = result)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }else{
        println("DATA NULL:>>>>>>>")
    }
}

}


private fun Context.buildExoPlayer(uri: Uri) =
    ExoPlayer.Builder(this).build().apply {
        setMediaItem(MediaItem.fromUri(uri))
        repeatMode = Player.REPEAT_MODE_ALL
        playWhenReady = true
        prepare()
    }

private fun Context.buildPlayerView(exoPlayer: ExoPlayer) =
    StyledPlayerView(this).apply {
        player = exoPlayer
        layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        useController = false
        resizeMode = RESIZE_MODE_ZOOM
    }*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YesNoScreen(videoUri: Uri,viewModel: YesNoViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val exoPlayer = remember { context.buildExoPlayer(videoUri) }
    val randomData by viewModel.randomData.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    var userInput by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        // Video arkaplanı
        DisposableEffect(
            AndroidView(
                factory = { it
                    .buildPlayerView(exoPlayer) },
                modifier = Modifier.fillMaxSize()
            )
        ) {
            onDispose {
                exoPlayer.release()
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                text = "Bugün ne soracaksın?",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = { showDialog = true }, // Butona tıklayınca dialog açılır
                modifier = Modifier.size(80.dp)
            ) {
                Icon(Icons.Default.Search, contentDescription = "Ask AI", tint = Color.White)
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = { showBottomSheet = true }) { // "Use Keyboard" tıklanınca açılır
                Text("Use Keyboard", color = Color.White)
            }
        }
    }

    // AI yanıtını gösteren Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("AI Response") },
            text = {
                Column {
                    randomData?.let {
                        Text(it.answer) // Yanıt metni
                        Image(painter = rememberImagePainter(it.image), contentDescription = null)

                    }
                }
            },
            confirmButton = {
                Button(onClick = { showDialog = false }) { Text("OK") }

            }
        )
    }

    // Klavyeyle giriş için BottomSheet
    if (showBottomSheet) {
        ModalBottomSheet(onDismissRequest = { showBottomSheet = false }) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16. dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = userInput,
                    onValueChange = { userInput = it },
                    label = { Text("Ask something") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    showDialog = true
                    showBottomSheet = false
                    viewModel.viewModelScope.launch {
                        viewModel.getRandomData()
                    }
                    //getRandomData() // API çağrısı yapılır
                }) {
                    Text("Send")
                }
            }
        }
    }



}

private fun Context.buildExoPlayer(uri: Uri) =
    ExoPlayer.Builder(this).build().apply {
        setMediaItem(MediaItem.fromUri(uri))
        repeatMode = Player.REPEAT_MODE_ALL
        playWhenReady = true
        prepare()
    }

private fun Context.buildPlayerView(exoPlayer: ExoPlayer) =
    StyledPlayerView(this).apply {
        player = exoPlayer
        layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        useController = false
        resizeMode = RESIZE_MODE_ZOOM
    }

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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YesNoScreen(videoUri: Uri,viewModel: YesNoViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val exoPlayer = remember { context.buildExoPlayer(videoUri) }
    val randomData by viewModel.randomData.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    var userInput by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

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
                text = "What Can I Answer For You Today",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.weight(1f))

            // To remove the delay when clicking the button, remove the delay() call:
            IconButton(
                onClick = {
                    scope.launch {
                        // Remove this line to make the dialog appear immediately:
                        // delay(5000)
                        viewModel.getRandomData() // Fetch new data from API
                        showDialog = true // Show dialog immediately
                    }
                },
                modifier = Modifier.size(80.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.voice), contentDescription = "Voice Icon")
            }
            // Add more space at the bottom to push the button up
            Spacer(modifier = Modifier.weight(0.3f))
            Spacer(modifier = Modifier.height(16.dp))

        }
    }

    // AI yanıtını gösteren Dialog
// AI yanıtını gösteren Dialog
    // AI yanıtını gösteren Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { },
            text = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                ) {
                    // Close (X) icon in the top-left corner
                    IconButton(
                        onClick = { showDialog = false },
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(4.dp)
                            .size(32.dp)
                            .background(Color.Black.copy(alpha = 0.3f), shape = CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.White
                        )
                    }

                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(8.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        randomData?.let {
                            // Container for image and overlaid text
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                // Image first (in background)
                                val imageLoader = ImageLoader.Builder(LocalContext.current)
                                    .components {
                                        if (Build.VERSION.SDK_INT >= 28) {
                                            add(ImageDecoderDecoder.Factory())
                                        } else {
                                            add(GifDecoder.Factory())
                                        }
                                    }
                                    .build()

                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(it.image)
                                        .build(),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(320.dp) // Increased height for larger image
                                        .scale(1.1f) // Slightly scale up the image
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop,
                                    imageLoader = imageLoader
                                )

                                // Text at the bottom center of the image
                                Text(
                                    text = it.answer,
                                    style = TextStyle(
                                        fontSize = 30.sp,
                                        textAlign = TextAlign.Center,
                                        color = Color.White,
                                        shadow = Shadow(
                                            color = Color.Black,
                                            offset = Offset(1f, 1f),
                                            blurRadius = 3f
                                        )
                                    ),
                                    modifier = Modifier
                                        .align(Alignment.BottomCenter)
                                        .padding(16.dp)
                                        .background(
                                            Color.Black.copy(alpha = 0.5f),
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .padding(8.dp)
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = { /* No confirm button */ },
            containerColor = Color.Transparent
        )
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

package com.enesselcuk.moviesui.ui.screen.player

import android.os.Build
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.enesselcuk.moviesui.ui.screen.movie.SharedViewModel
import androidx.media3.common.MimeTypes




@Composable
fun MoviesOrTvScreenPlayer(
    sharedViewModel: SharedViewModel
) {
    val playerViewModel = hiltViewModel<PlayerViewModel>()
    val playerId = sharedViewModel.playerId.value

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current
        val exoPlayer = ExoPlayer.Builder(context).build()
     //   val mediaItem  = MediaItem.fromUri()

    }
}


/*@Composable
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
fun VideoView(playerUrl: String) {
    val context = LocalContext.current
    val exoPlayer = remember { ExoPlayer.Builder(context).build() }

    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                player = exoPlayer
            }
        },
        modifier = Modifier.fillMaxSize()
    )

    LaunchedEffect(Unit) {
        val mediaItem = createMediaItem("https://firebasestorage.googleapis.com/v0/b/jetcalories.appspot.com/o/FROM%20EARTH%20TO%20SPACE%20_%20Free%20HD%20VIDEO%20-%20NO%20COPYRIGHT.mp4?alt=media&token=68bce5a0-7ca7-4538-9fea-98d0dc2d3646")
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

}*/


private fun createMediaItem(videoUrl: String): MediaItem {
    return MediaItem.Builder()
        .setUri(videoUrl)
        .setMimeType(MimeTypes.APPLICATION_MP4)
        .build()
}
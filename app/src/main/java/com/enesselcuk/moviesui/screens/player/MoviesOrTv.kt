package com.enesselcuk.moviesui.screens.player

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer


@Composable
fun MoviesOrTvScreen(playerId: Long, playerViewModel: PlayerViewModel = hiltViewModel()) {

//    VideoView(viewModel = playerViewModel, playerUrl = "https://www.youtube.com/watch?v=qd1arecDhXk")

}

@Composable
fun VideoView(viewModel: PlayerViewModel, playerUrl: String) {

    var player : Player? = null
    val context = LocalContext.current
    val mediaItem = MediaItem.fromUri(playerUrl)
    val secondMediaItem = MediaItem.fromUri(playerUrl)

    player = ExoPlayer.Builder(context)
        .build()
        .also { exoPlayer ->
            exoPlayer.setMediaItems(listOf(mediaItem),  player!!.currentMediaItemIndex, player!!.currentPosition)
            exoPlayer.playWhenReady = player!!.playWhenReady
            exoPlayer.prepare()
        }



    val exoPlayer = rememberSaveable { player }

    exoPlayer.addListener(
        object : Player.Listener {
            override fun onIsPlayingChanged(isPlayingValue: Boolean) {
                viewModel.isPlaying.value = isPlayingValue
            }
        }
    )

    DisposableEffect(
        Box(modifier = Modifier.fillMaxSize()) {
           /* PlayerView(context).apply {

            }*/

            VideoLayout(
                isPlaying = viewModel.isPlaying.value,
                onPlay = {
                    exoPlayer.play()
                },
                onPause = {
                    exoPlayer.pause()
                }
            )
        }
    ) {
        onDispose { exoPlayer.release() }
    }
}


@Composable
fun VideoLayout(
    isPlaying: Boolean,
    onPlay: () -> Unit,
    onPause: () -> Unit,

    ) {}
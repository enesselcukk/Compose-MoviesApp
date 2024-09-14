package com.enesselcuk.moviesui.ui.screen.tv.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.enesselcuk.moviesui.util.Constant.IMAGE_BASE_W500

@Composable
fun TvImagePoster(image: String) {
    AsyncImage(
        model = "$IMAGE_BASE_W500$image",
        contentDescription = "",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
            .clip(RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp))
            .height(400.dp)

    )
}
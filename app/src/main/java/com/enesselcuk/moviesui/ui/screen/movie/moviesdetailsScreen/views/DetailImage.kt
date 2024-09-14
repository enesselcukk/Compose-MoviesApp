package com.enesselcuk.moviesui.ui.screen.movie.moviesdetailsScreen.views

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
fun DetailImage(details: String? = "") {
    AsyncImage(
        model = "$IMAGE_BASE_W500$details",
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
            .clip(RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp))
            .height(400.dp)
    )
}

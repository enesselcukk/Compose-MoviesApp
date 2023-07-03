package com.enesselcuk.moviesui.screens.movie.moviesdetailsScreen.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.enesselcuk.moviesui.source.model.response.DetailResponse
import com.enesselcuk.moviesui.util.dateCurrent

@Composable
fun DetailGeneral(item: DetailResponse? = null) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(14.dp, alignment = Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item?.title.orEmpty())
        Text(text = item?.release_date?.dateCurrent().orEmpty())
    }
    Column(
        modifier = Modifier.padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = item?.overview.orEmpty())
    }
}

package com.enesselcuk.moviesui.ui.screen.tv.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.enesselcuk.moviesui.data.model.response.TvDetailResponse

@Composable
fun TvOverView(item: TvDetailResponse?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item?.name.orEmpty())
        Text(text = item?.first_air_date.orEmpty())
    }
    Spacer(modifier = Modifier.height(5.dp))
    Column(
        modifier = Modifier.padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = item?.overview.orEmpty())
    }
}
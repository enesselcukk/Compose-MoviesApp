package com.enesselcuk.moviesui.screens.movie.moviesdetailsScreen.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.enesselcuk.moviesui.screens.movie.moviesdetailsScreen.DetailsViewModel
import com.enesselcuk.moviesui.data.model.response.MoviesResponse
import com.enesselcuk.moviesui.util.Constant.IMAGE_BASE_W500
import java.math.RoundingMode

@Composable
private fun DetailRecommendedView(
    image: String,
    movieName: String,
    vote: Double,
    onClick: () -> Unit
) {
    ConstraintLayout(modifier = Modifier.clickable { onClick.invoke() }) {
        val (imageCons, voteCons, nameCons) = createRefs()

        AsyncImage(
            model = "$IMAGE_BASE_W500$image",
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(height = 150.dp, width = 120.dp)
                .constrainAs(imageCons) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = "IMDb ${vote.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()}",
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(topStart = 8.dp, bottomEnd = 8.dp)
                )
                .wrapContentWidth()
                .padding(horizontal = 5.dp)
                .constrainAs(voteCons) {
                    start.linkTo(parent.start)
                    top.linkTo(imageCons.top)
                },
            style = TextStyle(fontWeight = FontWeight.W800)
        )

        Text(
            text = movieName,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.constrainAs(nameCons) {
                top.linkTo(imageCons.bottom, 8.dp)
                start.linkTo(imageCons.start)
                end.linkTo(imageCons.end)
            }
        )
    }
}


@Composable
fun DetailRecommendedRow(item: MoviesResponse? = null, onClick: (id:Int) -> Unit) {
    val viewModel = hiltViewModel<DetailsViewModel>()
    if (!item?.results.isNullOrEmpty()) viewModel.isVisible.value = true

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(item?.results ?: emptyList()) { items ->
            DetailRecommendedView(
                image = items.poster_path,
                movieName = items.title.let {
                    if (it.count() >= 13) it.substring(0, 13).plus("...")
                    else it
                },
                vote = items.vote_average,
                onClick = {onClick.invoke(items.id)}
            )
        }
    }
}
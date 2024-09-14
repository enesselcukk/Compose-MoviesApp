package com.enesselcuk.moviesui.ui.screen.movie.homeSceen.views

import androidx.compose.animation.*
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
import coil.compose.AsyncImage
import com.enesselcuk.moviesui.data.model.response.MoviesResponse
import com.enesselcuk.moviesui.util.Constant
import java.math.RoundingMode


@Composable
fun PopularMovies(
    movieImage: String,
    movieName: String,
    movieVoteAverage: Double,
    onClick: () -> Unit) {

    ConstraintLayout(
        modifier = Modifier.clickable { onClick.invoke() }
    ) {
        val (imageCons, voteCons, nameCons) = createRefs()

        AsyncImage(
            model = "${Constant.IMAGE_BASE_W500}$movieImage",
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
            text = "IMDb ${
                movieVoteAverage.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
            }",
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
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(nameCons) {
                top.linkTo(imageCons.bottom, 8.dp)
                start.linkTo(imageCons.start)
                end.linkTo(imageCons.end)
            }
        )
    }
}

@Composable
fun PopularMoviesItems(items: MoviesResponse?, click: (id: Int) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        contentPadding = PaddingValues(horizontal = 5.dp),
    ) {
        items(items?.results ?: emptyList()) { recommended ->
            AnimatedVisibility(visible = true) {
                recommended.title?.let {
                    if (it.count() >= 13) it.substring(0, 13).plus("...")
                    else it
                }?.let {
                    recommended.vote_average?.let { it1 ->
                        PopularMovies(
                            recommended.poster_path.orEmpty(),
                            it,
                            it1,
                            onClick = { recommended.id?.let { it2 -> click.invoke(it2) } })
                    }
                }
            }
        }
    }

}



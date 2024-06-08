package com.enesselcuk.moviesui.screens.movie.homeSceen.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.enesselcuk.moviesui.screens.movie.homeSceen.HomeViewModel
import com.enesselcuk.moviesui.util.Constant.IMAGE_BASE_W500
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

// Most View
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeTopMoviesPager(
    modifier: Modifier = Modifier,
    clickIdToMovie: (id: Int) -> Unit,
    clickIdToTv: (id: Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getMoviesTrending()
    }
    val data = viewModel.getMoviesTrendingFlow.collectAsStateWithLifecycle()

    val pagerState = rememberPagerState()
    val result = data.value?.results?.take(8)
    val dataMedia = data.value?.results ?: emptyList()

    if (viewModel.isVisible.value) {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            HorizontalPager(
                count = result?.size ?: 0,
                state = pagerState,
            ) { page ->
                AsyncImage(
                    model = "$IMAGE_BASE_W500${result?.get(page)?.backdrop_path}",
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable {
                            if (dataMedia[page].media_type == "tv") {
                                clickIdToTv.invoke(result?.get(page)?.id ?: 0)
                            } else if (dataMedia[page].media_type == "movie") {
                                clickIdToMovie.invoke(result?.get(page)?.id ?: 0)
                            }

                        }
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                            lerp(
                                start = 0.25f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                        }
                )
            }

            Row(
                modifier = modifier
                    .padding(top = 30.dp)
                    .height(30.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center
            ) {

                repeat(result?.size ?: 0) {
                    val color =
                        if (pagerState.currentPage == it) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    Box(
                        modifier = modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(15.dp)
                    )
                }
            }
        }
    }
}

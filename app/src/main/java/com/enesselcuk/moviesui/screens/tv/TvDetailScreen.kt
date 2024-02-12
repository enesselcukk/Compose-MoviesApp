package com.enesselcuk.moviesui.screens.tv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.enesselcuk.moviesui.R
import com.enesselcuk.moviesui.screens.movie.SharedViewModel
import com.enesselcuk.moviesui.screens.tv.views.LikedAndWatch
import com.enesselcuk.moviesui.screens.tv.views.TvImagePoster
import com.enesselcuk.moviesui.screens.tv.views.TvOverView
import com.enesselcuk.moviesui.screens.tv.views.TvRecommendedItems

@Composable
fun TvDetailScreen(
    tvId: Int,
    isActionInTopBar: (isVisible: Boolean) -> Unit,
    isVisibleBottom: (Boolean) -> Unit,
    isVisibleTopBar: (Boolean) -> Unit,
    isVisibleTopBarBack: (Boolean) -> Unit,
    screenName: (name: String) -> Unit,
    clickTvId: () -> Unit,
    sharedViewModel: SharedViewModel,
    tvViewModel: TvDetailViewModel = hiltViewModel(),
) {

    val isLiked = rememberSaveable { mutableStateOf(false) }

    isActionInTopBar.invoke(false)
    isVisibleBottom.invoke(false)
    isVisibleTopBar.invoke(true)
    isVisibleTopBarBack.invoke(true)
    screenName.invoke("Tv Detail")

    if (!tvViewModel.isClickRecommended.value) {
        tvViewModel.getTvDetail(id = tvId, language = "tr")
        tvViewModel.getRecommendationsTv(id = tvId, language = "tr", page = 1)
        tvViewModel.getFavorite()
    }

    val data = tvViewModel.tvDetailFlow.collectAsStateWithLifecycle()
    val getTvLikedResponse = tvViewModel.getTvFlowFavorite.collectAsStateWithLifecycle()
    val recommendData = tvViewModel.tvRecommendationsFlow.collectAsStateWithLifecycle()

    val likedItem = getTvLikedResponse.value?.find { it.name == data.value?.name }
    isLiked.value = likedItem != null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TvImagePoster(image = data.value?.poster_path.orEmpty())
        LikedAndWatch(
            data.value, isLiked.value,
            onClick = {
                clickTvId.invoke()
                sharedViewModel.playerId.value = it
            },
            tvViewModel
        )
        TvOverView(item = data.value)
        DetailRecommendedHome(
            titleName = stringResource(id = R.string.recommend),
            viewModel = tvViewModel,
            detailItem = {
                TvRecommendedItems(items = recommendData.value, viewModel = tvViewModel,
                    onClick = { id ->
                        tvViewModel.isClickRecommended.value = true
                        tvViewModel.getTvDetail(id = id, language = "tr")
                        tvViewModel.getRecommendationsTv(id = id, language = "tr", page = 1)
                        tvViewModel.getFavorite()
                    })
            })
    }
}

@Composable
fun DetailRecommendedHome(
    titleName: String,
    viewModel: TvDetailViewModel,
    detailItem: @Composable () -> Unit,
) {
    if (viewModel.isVisible.value) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp, horizontal = 5.dp)
        ) {
            val (name, seeAll) = createRefs()
            Text(text = titleName, modifier = Modifier.constrainAs(name) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            }, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))

            Text(text = stringResource(id = R.string.see_all), Modifier.constrainAs(seeAll) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
        }
    }
    detailItem()

}

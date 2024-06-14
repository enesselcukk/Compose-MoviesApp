package com.enesselcuk.moviesui.screens.movie.moviesdetailsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.enesselcuk.moviesui.screens.movie.moviesdetailsScreen.views.DetailGeneral
import com.enesselcuk.moviesui.screens.movie.moviesdetailsScreen.views.DetailImage
import com.enesselcuk.moviesui.screens.movie.moviesdetailsScreen.views.DetailRecommendedRow
import com.enesselcuk.moviesui.screens.movie.moviesdetailsScreen.views.DetailTop

@Composable
fun DetailsScreen(
    moviesId: Int,
    isVisibleBottom: (visible: Boolean) -> Unit,
    isVisibleTopBar: (visible: Boolean) -> Unit,
    isVisibleTopBarBack: (visible: Boolean) -> Unit,
    isActionInTopBar: (isVisible: Boolean) -> Unit,
    screeName: (name: String) -> Unit,
    sharedViewModel: SharedViewModel,
    onClick: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel()
) {

    val isLiked = rememberSaveable { mutableStateOf(false) }
    isActionInTopBar.invoke(false)
    isVisibleBottom.invoke(false)
    isVisibleTopBar.invoke(true)
    isVisibleTopBarBack.invoke(true)
    screeName.invoke("Details")


    if (!viewModel.isClickRecommended.value) {
        LaunchedEffect(Unit) {
            viewModel.getDetails(movie_id = moviesId, language = "en")
            viewModel.getRecommended(id = moviesId, 1, "en")
            viewModel.getFavorite()
        }
    }


    val data = viewModel.detailsFlow.collectAsStateWithLifecycle()
    val dataRecommended = viewModel.detailRecommended.collectAsStateWithLifecycle()
    val getLikedResponse = viewModel.getFlowFavorite.collectAsStateWithLifecycle()

    val likedItem = getLikedResponse.value?.find { it.id == data.value?.id }
    isLiked.value = likedItem != null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        DetailImage(data.value?.backdrop_path)
        DetailTop(data.value, isLiked.value, onClick = {
            onClick.invoke()
            sharedViewModel.playerId.value = it
        }, viewModel)
        DetailGeneral(item = data.value)
        DetailRecommendedHome(stringResource(id = R.string.recommend), viewModel)
        {
            DetailRecommendedRow(
                dataRecommended.value,
                onClick = { clickId ->
                    viewModel.isClickRecommended.value = true
                    viewModel.getDetails(movie_id = clickId, language = "en")
                    viewModel.getRecommended(id = clickId, 1, "en")
                    viewModel.getFavorite()
                })
        }
    }
}

@Composable
fun DetailRecommendedHome(
    titleName: String,
    viewModel: DetailsViewModel,
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
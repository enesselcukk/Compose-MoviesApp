package com.enesselcuk.moviesui.ui.screen.movie.homeSceen


import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.enesselcuk.moviesui.ui.screen.movie.homeSceen.views.HomeTopMoviesPager
import com.enesselcuk.moviesui.ui.screen.movie.homeSceen.views.PopularMoviesItems
import com.enesselcuk.moviesui.ui.screen.movie.homeSceen.views.UpComingItems

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    isVisibleBottom: (Boolean) -> Unit,
    isVisibleTopBar: (Boolean) -> Unit,
    isVisibleTopBarBack: (Boolean) -> Unit,
    screenName: (name: String) -> Unit,
    isActionInTopBar: (isVisible: Boolean) -> Unit,
    click: (name: String) -> Unit,
    clickID: (id: Int) -> Unit,
    clickIdToMovie: (id: Int) -> Unit,
    clickIdToTv: (id: Int) -> Unit
) {

    val titlePopular = "popular"
    val uoComingTitle = "upcoming"
    val language = "en-US"
    val page = 1

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.surface)
    ) {

        viewModel.getMovies(title = titlePopular, language = language, page = page)
        viewModel.getMoviesUpComing(title = uoComingTitle, language = language, page = page)

        val popularMovie by viewModel.getMoviesFlow.collectAsStateWithLifecycle()
        val upComingMovie by viewModel.getUpComingFlow.collectAsStateWithLifecycle()

        HomeTopMoviesPager(
            modifier = Modifier,
            clickIdToMovie = { clickIdToMovie.invoke(it) },
            clickIdToTv = { clickIdToTv.invoke(it) })
        Spacer(modifier = Modifier.height(5.dp))
        val namePop = stringResource(id = R.string.popular)
        val nameUp = stringResource(id = R.string.upcoming)

        HomeSections(
            title = R.string.popular,
            titleSeeAll = R.string.see_all,
            click = { click.invoke(namePop) },
        ) {
            PopularMoviesItems(items = popularMovie, click = { clickID.invoke(it) })
        }
        Spacer(modifier = Modifier.height(5.dp))
        HomeSections(
            title = R.string.upcoming,
            titleSeeAll = R.string.see_all,
            click = { click.invoke(nameUp) },
        ) {
            UpComingItems(items = upComingMovie, click = { clickID.invoke(it) })
        }
        Spacer(modifier = Modifier.height(5.dp))
        isVisibleBottom.invoke(true)
        isVisibleTopBar.invoke(true)
        isVisibleTopBarBack.invoke(false)
        screenName.invoke(stringResource(id = R.string.home_title))
        isActionInTopBar.invoke(false)
    }
}

@Composable
fun HomeSections(
    @StringRes title: Int,
    @StringRes titleSeeAll: Int,
    modifier: Modifier = Modifier,
    click: () -> Unit,
    content: @Composable () -> Unit

) {
    ConstraintLayout(
        modifier
            .fillMaxSize()
            .padding(bottom = 5.dp)
    ) {
        val (recommended, seeAll) = createRefs()
        Text(
            text = stringResource(id = title), modifier.constrainAs(recommended) {
                top.linkTo(parent.top, 8.dp)
                start.linkTo(parent.start, 8.dp)
                end.linkTo(seeAll.start, 8.dp)
                width = Dimension.fillToConstraints
            }, style = TextStyle(
                fontSize = 18.sp, fontWeight = FontWeight.Bold
            )
        )

        Text(
            text = stringResource(id = titleSeeAll),
            modifier
                .clickable {
                    click.invoke()
                }
                .constrainAs(seeAll) {
                    top.linkTo(parent.top, 8.dp)
                    end.linkTo(parent.end, 8.dp)
                }, style = TextStyle(
                fontSize = 14.sp, fontWeight = FontWeight.Bold
            )
        )
    }
    content()

}


@Composable
fun Indicator(isDisplayed: Boolean? = false) {
    if (isDisplayed == true) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }
}


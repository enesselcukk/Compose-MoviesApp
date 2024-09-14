package com.enesselcuk.moviesui.ui.screen.searchScreen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.enesselcuk.moviesui.R
import com.enesselcuk.moviesui.ui.screen.searchScreen.views.Movies
import com.enesselcuk.moviesui.ui.screen.searchScreen.views.MoviesViewRow
import com.enesselcuk.moviesui.ui.screen.searchScreen.views.PeopleRow
import com.enesselcuk.moviesui.ui.screen.searchScreen.views.Search
import com.enesselcuk.moviesui.ui.screen.searchScreen.views.SearchView


@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel(),
    peopleClick: (id: Int) -> Unit,
    isVisibleBottom:(Boolean) -> Unit,
    isVisibleTopBar:(Boolean) -> Unit,
    isVisibleTopBarBack:(Boolean) -> Unit,
    isActionInTopBar : (isVisible:Boolean) -> Unit,
    screenName:(name:String) -> Unit
) {

    isVisibleBottom.invoke(true)
    isVisibleTopBar.invoke(true)
    isVisibleTopBarBack.invoke(false)
    screenName.invoke(stringResource(id = R.string.search_title))
    isActionInTopBar.invoke(false)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
    ) {
        SearchView(modifier, viewModel)
        Spacer(modifier = modifier.height(50.dp))
        Movies(movies = R.string.movies, icons = R.drawable.arrow_forward, viewModel = viewModel) {
            MoviesViewRow(navController = navController, searchViewModel = viewModel)
        }
        Search(titles = R.string.actors, viewModel = viewModel) {
            PeopleRow(modifier = modifier, viewModel, onCLick = { peopleClick.invoke(it) })
        }

    }
}

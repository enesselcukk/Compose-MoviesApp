package com.enesselcuk.moviesui.screens.likedScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.enesselcuk.moviesui.R
import com.enesselcuk.moviesui.screens.movie.SharedViewModel
import com.enesselcuk.moviesui.data.model.response.DetailResponse
import com.enesselcuk.moviesui.data.model.response.TvDetailResponse
import com.enesselcuk.moviesui.util.Constant
import java.math.RoundingMode


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun LikedScreen(
    isVisibleTopBar: (Boolean) -> Unit,
    isVisibleTopBarBack: (Boolean) -> Unit,
    isVisibleBottom: (Boolean) -> Unit,
    screenName: (name: String) -> Unit,
    isChooseTopBar: (isLiked: Boolean) -> Unit,
    isActionInTopBar: (isVisible: Boolean) -> Unit,
    toDetail: (id: Int) -> Unit,
    toDetailTv: (id: Int) -> Unit,
    sharedViewModel: SharedViewModel,
    viewModel: LikedViewModel = hiltViewModel(),
) {

    val data = viewModel.getFlowFavorite.collectAsStateWithLifecycle().value
    val dataTv = viewModel.getFlowTvFavorite.collectAsStateWithLifecycle().value

    screenName.invoke("Likeds")
    isVisibleTopBar.invoke(true)
    isVisibleTopBarBack.invoke(false)
    isVisibleBottom.invoke(true)
    isChooseTopBar.invoke(true)
    isActionInTopBar.invoke(true)
    sharedViewModel.isVisibleSetting.value = false

    Column(verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .clickable {
                viewModel.chooseList.clear()
                viewModel.chooseListTv.clear()
            }) {

        ToggleMovieOrTv(viewModel,
            isVisibleMovies = {
                LikedScreenItemsMovies(detailResponse = data,
                    sharedViewModel = sharedViewModel,
                    toDetail = { toDetail.invoke(it) })
                viewModel.getFavorite()
            }, isVisibleTv = {
                LikedScreenItemsTv(detailResponse = dataTv,
                    sharedViewModel = sharedViewModel,
                    toDetailTv = { toDetailTv.invoke(it) })
                viewModel.getTvFavorite()
            })

        OpenDialog(viewModel = viewModel, sharedViewModel)
    }
}


@Composable
fun ToggleMovieOrTv(
    viewModel: LikedViewModel,
    isVisibleMovies: @Composable () -> Unit,
    isVisibleTv: @Composable () -> Unit,
) {
    val toggleChangeMovies = rememberSaveable { mutableStateOf(false) }
    val toggleChangeTv = rememberSaveable { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedButton(
            onClick = {
                toggleChangeMovies.value = true
                toggleChangeTv.value = false
            },
            shape = RoundedCornerShape(20),
            border = if (toggleChangeMovies.value) BorderStroke(
                1.dp,
                MaterialTheme.colorScheme.primaryContainer
            ) else BorderStroke(
                1.dp,
                MaterialTheme.colorScheme.primary
            ),
            colors = buttonColors(
                if (toggleChangeMovies.value) MaterialTheme.colorScheme.primaryContainer
                else MaterialTheme.colorScheme.primary
            ),
        ) {
            Text(
                text = "Movies",
                color = if (toggleChangeMovies.value) MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.surface
            )

        }

        OutlinedButton(
            onClick = {
                toggleChangeTv.value = true
                toggleChangeMovies.value = false
            },
            shape = RoundedCornerShape(20),

            border = if (toggleChangeTv.value) BorderStroke(
                1.dp, MaterialTheme.colorScheme.primaryContainer
            )
            else BorderStroke(1.dp, MaterialTheme.colorScheme.primary),

            colors = buttonColors(
                if (toggleChangeTv.value) MaterialTheme.colorScheme.primaryContainer
                else MaterialTheme.colorScheme.primary
            )

        ) {

            Text(
                text = "Tv",
                color = if (toggleChangeTv.value) MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.surface
            )
        }
    }

    if (toggleChangeMovies.value) {
        isVisibleMovies()
    } else if (toggleChangeTv.value) {
        isVisibleTv()
    }
}


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun LikedScreenView(
    onClick: () -> Unit,
    movieImage: String,
    movieVoteAverage: String,
    movieName: String,
    id: Int,
    sharedViewModel: SharedViewModel,
    onClickNav: (id: Int) -> Unit,
    viewModel: LikedViewModel = hiltViewModel()
) {

    val change = rememberSaveable { mutableStateOf(true) }


    ConstraintLayout(modifier = Modifier.clickable {
        onClick.invoke()

        if (sharedViewModel.isChooseClick.value) {
            if (change.value) {
                viewModel.chooseList.add(id)
                viewModel.chooseListTv.add(id)
                change.value = false
            } else {
                viewModel.chooseList.removeIf { it == id }
                viewModel.chooseListTv.removeIf { it == id }
                change.value = true
            }
        } else {
            onClickNav.invoke(id)
        }

    }) {
        val (imageCons, voteCons, nameCons, choose) = createRefs()
        AsyncImage(model = "${Constant.IMAGE_BASE_W500}$movieImage",
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(height = 150.dp, width = 120.dp)
                .constrainAs(imageCons) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                })

        if (sharedViewModel.isChooseClick.value) {
            Image(painter = if (change.value) painterResource(id = R.drawable.empty_circle) else painterResource(
                id = R.drawable.check_circle
            ),
                contentDescription = "",
                modifier = Modifier
                    .constrainAs(choose) {
                        top.linkTo(parent.top, 3.dp)
                        end.linkTo(parent.end, 3.dp)
                    }
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colorScheme.tertiary, CircleShape),
                contentScale = ContentScale.Crop)
        }

        Text(
            text = "IMDb ${
                movieVoteAverage.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
            }", color = MaterialTheme.colorScheme.surface, modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(topStart = 8.dp, bottomEnd = 8.dp)
                )
                .wrapContentWidth()
                .padding(horizontal = 5.dp)
                .constrainAs(voteCons) {
                    start.linkTo(imageCons.start)
                    top.linkTo(imageCons.top)
                }, style = TextStyle(fontWeight = FontWeight.W800)
        )

        Text(text = movieName,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.constrainAs(nameCons) {
                top.linkTo(imageCons.bottom, 8.dp)
                start.linkTo(imageCons.start)
                end.linkTo(imageCons.end)
            })
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun LikedScreenItemsMovies(
    detailResponse: List<DetailResponse>?,
    sharedViewModel: SharedViewModel,
    toDetail: (id: Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier.padding(horizontal = 6.dp, vertical = 6.dp)
    ) {
        items(detailResponse ?: emptyList()) {
            LikedScreenView(
                onClick = {},
                movieImage = it.poster_path.orEmpty(),
                movieVoteAverage = it.vote_average.toString(),
                movieName = it.title?.let { title ->
                    if (title.count() >= 13) title.substring(0, 13).plus("...")
                    else title
                }.orEmpty(),
                id = it.id ?: 0,
                onClickNav = { toDetail.invoke(it) },
                sharedViewModel = sharedViewModel
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun LikedScreenItemsTv(
    detailResponse: List<TvDetailResponse>?,
    sharedViewModel: SharedViewModel,
    toDetailTv: (id: Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier.padding(horizontal = 6.dp, vertical = 6.dp)
    ) {
        items(detailResponse ?: emptyList()) {
            LikedScreenView(
                onClick = {},
                movieImage = it.poster_path.orEmpty(),
                movieVoteAverage = it.vote_average.toString(),
                movieName = it.name?.let { title ->
                    if (title.count() >= 13) title.substring(0, 13).plus("...")
                    else title
                }.orEmpty(),
                id = it.id ?: 0,
                onClickNav = { toDetailTv.invoke(it) },
                sharedViewModel = sharedViewModel
            )
        }
    }
}


@Composable
fun OpenDialog(
    viewModel: LikedViewModel, sharedViewModel: SharedViewModel
) {

    if (sharedViewModel.isOpenDialog.value) {
        AlertDialog(onDismissRequest = {
            sharedViewModel.isOpenDialog.value = false
        }, icon = { Icon(Icons.Filled.Favorite, contentDescription = null) }, title = {
            Text(text = "Warning")
        }, text = {
            if (viewModel.chooseList.size > 0 || viewModel.chooseListTv.size > 0) Text("are you sure you want to delete")
            else Text("please select movie")
        }, confirmButton = {
            if (viewModel.chooseList.size > 0 || viewModel.chooseListTv.size > 0) {
                Button(onClick = {
                    sharedViewModel.isOpenDialog.value = false
                    viewModel.chooseList.onEach { viewModel.delete(it) }
                    viewModel.chooseListTv.onEach { viewModel.deleteTv(it) }
                    viewModel.getFavorite()
                    viewModel.getTvFavorite()
                }) {
                    Text("Yes")
                }
            } else {
                Button(
                    onClick = { sharedViewModel.isOpenDialog.value = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Okay")
                }
            }
        }, dismissButton = {
            if (viewModel.chooseList.size > 0 || viewModel.chooseListTv.size > 0) {
                Button(onClick = { sharedViewModel.isOpenDialog.value = false }) {
                    Text("No")
                }
            }

        })
    }
}








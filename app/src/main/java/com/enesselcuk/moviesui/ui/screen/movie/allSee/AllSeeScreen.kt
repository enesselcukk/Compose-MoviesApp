package com.enesselcuk.moviesui.ui.screen.movie.allSee


import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.enesselcuk.moviesui.data.model.response.Result
import com.enesselcuk.moviesui.util.Constant.IMAGE_BASE_W500
import java.math.RoundingMode
import java.util.Locale
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Local

@Composable
fun AllSeeScreenHome(
    toDetail: ((id: Int) -> Unit)? = null,
    titleName: String? = "",
    isVisibleBottom: ((visible: Boolean) -> Unit)? = null,
    isActionInTopBar : (isVisible:Boolean) -> Unit,
    viewModel: AllSeeViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.allMovies(titleName?.lowercase().orEmpty(), Locale.getDefault().language)
    }

    val data = viewModel.pageFlow.collectAsLazyPagingItems()

    isVisibleBottom?.invoke(false)
    isActionInTopBar.invoke(false)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        AllScreenViewItems(data, toDetail = { toDetail?.invoke(it) })

    }
}

@Composable
fun AllScreenView(
    image: String? = null,
    vote: Double? = null,
    name:String?=null,
    click: () -> Unit
) {
    ConstraintLayout(modifier = Modifier.clickable { click.invoke() }) {
        val (imageCons, voteCons, nameCons) = createRefs()


        AsyncImage(
            model = "${IMAGE_BASE_W500}$image",
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
            text = "IMDb ${vote?.toBigDecimal()?.setScale(1, RoundingMode.UP)?.toDouble()}",
            color = Color.White,
            modifier = Modifier
                .background(
                    Color.Red,
                    RoundedCornerShape(topStart = 8.dp, bottomEnd = 8.dp)
                )
                .wrapContentWidth()
                .padding(horizontal = 5.dp)
                .constrainAs(voteCons) {
                    start.linkTo(imageCons.start)
                    top.linkTo(imageCons.top)
                },
            style = TextStyle(fontWeight = FontWeight.W800)
        )

        Text(
            text = name.orEmpty(),
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllScreenViewItems(items: LazyPagingItems<Result>, toDetail: (id: Int) -> Unit) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier.padding(horizontal = 6.dp)
    ) {
        items(items.itemCount) { index ->
            items[index].let {
                AllScreenView(
                    image = it?.poster_path,
                    vote = it?.vote_average,
                    name = it?.title,
                    click = {
                        toDetail.invoke(it?.id ?: 0)
                    }
                )
            }
        }


        items.apply {

            /*
               val error = when {
                   loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                   loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                   loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                   else -> null
               }
                */

            val loading = when {
                loadState.prepend is LoadState.Loading -> loadState.prepend as LoadState.Loading
                loadState.append is LoadState.Loading -> loadState.append as LoadState.Loading
                loadState.refresh is LoadState.Loading -> loadState.refresh as LoadState.Loading
                else -> null
            }

            if (loading != null) {
                repeat((0..20).count()) {
                    item {
                        Box() {
                            ShimmerAnimation()
                        }
                    }
                }
            }
        }

    }
}


@Composable
fun ProgressBarView() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun ShimmerAnimation(
) {
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(

        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(

            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )

    val shimmerColorShades = listOf(
        Color.White.copy(0.9f),
        Color.LightGray.copy(0.2f),
        Color.LightGray.copy(0.9f)
    )

    val brush = Brush.linearGradient(
        colors = shimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    ShimmerItem(brush = brush)

}


@Composable
fun ShimmerItem(
    brush: Brush
) {

    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(250.dp)
                .background(brush = brush)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .padding(vertical = 8.dp)
                .background(brush = brush)
        )
    }
}

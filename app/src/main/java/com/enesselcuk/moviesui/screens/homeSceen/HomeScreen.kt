package com.enesselcuk.moviesui.screens.homeSceen

import android.content.Context
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.enesselcuk.moviesui.R
import com.enesselcuk.moviesui.source.data.marvel
import com.enesselcuk.moviesui.source.data.moviesRecommended
import com.enesselcuk.moviesui.ui.theme.MoviesUiTheme


@Composable
fun HomeScreen(modifier: Modifier = Modifier, context: Context? = null) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
    ) {
        SearchBar()
        Spacer(modifier = modifier.height(5.dp))
        HomeTopMoviesCard()
        Spacer(modifier = modifier.height(5.dp))
        HomeSections(title = R.string.recommended, titleSeeAll = R.string.see_all) {
            RecommendedMoviesRow(modifier, context!!)
        }
        HomeSections(title = R.string.marvel, titleSeeAll = R.string.see_all) {
            MarvelMoviesRow(modifier, context!!)
        }
        Spacer(modifier = modifier.height(5.dp))
    }
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    MoviesUiTheme {
        HomeScreen(Modifier)
    }
}


@Composable
fun HomeSections(
    @StringRes title: Int,
    @StringRes titleSeeAll: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        ConstraintLayout(
            modifier
                .fillMaxSize()
                .padding(bottom = 10.dp)
        ) {
            val (recommended, seeAll) = createRefs()
            Text(
                text = stringResource(id = title),
                modifier.constrainAs(recommended) {
                    top.linkTo(parent.top, 8.dp)
                    start.linkTo(parent.start, 8.dp)
                    end.linkTo(seeAll.start, 8.dp)
                    width = Dimension.fillToConstraints
                },
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = stringResource(id = titleSeeAll),
                modifier.constrainAs(seeAll) {
                    top.linkTo(parent.top, 8.dp)
                    end.linkTo(parent.end, 8.dp)
                },
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        content()
    }
}

// Recommended
@Composable
fun RecommendedMovies(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    @StringRes text: Int,
    @StringRes textTime: Int,
    @StringRes textPoint: Int,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.black),
                shape = RoundedCornerShape(
                    topEnd = 5.dp,
                    topStart = 5.dp,
                    bottomEnd = 5.dp,
                    bottomStart = 5.dp
                )
            )
            .clickable { onClick.invoke(image) },
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(width = 100.dp, height = 120.dp)
                .clip(RoundedCornerShape(topEndPercent = 5, topStartPercent = 5))

        )
        Text(
            text = stringResource(id = text),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Row(
            modifier = modifier
                .width(100.dp)
                .padding(bottom = 5.dp)
        ) {
            Text(
                text = stringResource(id = textTime),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                modifier = modifier
                    .padding(start = 5.dp)
                    .widthIn(25.dp, 30.dp),

                )

            Text(
                text = stringResource(id = textPoint),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Right,
                modifier = modifier.padding(start = 50.dp)
            )
        }
    }
}

@Composable
fun RecommendedMoviesRow(modifier: Modifier = Modifier, context: Context) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        contentPadding = PaddingValues(horizontal = 3.dp)
    ) {
        items(moviesRecommended) { recommended ->
            RecommendedMovies(
                modifier,
                recommended.moviesImage,
                recommended.moviesName,
                recommended.moviesHour,
                recommended.moviesPoint,
                onClick = {
                    Toast.makeText(context, recommended.moviesName, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}
/*------------------------------------------------------------------------------------------------*/

@Composable
fun MarvelMovies(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    @StringRes moviesName: Int,
    @StringRes moviesHour: Int,
    @StringRes moviesPoint: Int,
    onClick: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .border(
                width = 2.dp,
                color = colorResource(id = R.color.black),
                shape = RoundedCornerShape(5.dp),
            )
            .clickable { onClick.invoke(image) }
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(width = 100.dp, height = 120.dp)
                .clip(RoundedCornerShape(topEnd = 5.dp, topStart = 5.dp))

        )
        Text(
            text = stringResource(id = moviesName), fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            maxLines = 2
        )
        Row(
            modifier = modifier
                .width(100.dp)
                .padding(bottom = 5.dp)
        ) {
            Text(
                text = stringResource(id = moviesHour),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                modifier = modifier
                    .padding(start = 5.dp)
                    .widthIn(25.dp, 30.dp),
            )

            Text(
                text = stringResource(id = moviesPoint),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Right,
                modifier = modifier.padding(start = 50.dp)
            )
        }
    }
}

@Composable
fun MarvelMoviesRow(modifier: Modifier = Modifier, context: Context) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        contentPadding = PaddingValues(horizontal = 3.dp)
    ) {
        items(marvel) { item ->
            MarvelMovies(
                modifier = modifier,
                image = item.moviesImage,
                moviesName = item.moviesName,
                moviesHour = item.moviesHour,
                moviesPoint = item.moviesPoint,
                onClick = { Toast.makeText(context, item.moviesName, Toast.LENGTH_SHORT).show() }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun RecommendedMoviesPreview() {
    MoviesUiTheme {
        RecommendedMovies(
            Modifier,
            R.drawable.marvel,
            R.string.app_name,
            textTime = R.string.timeMovies,
            textPoint = R.string.pointMovies,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MarvelMoviesPreview() {
    MoviesUiTheme {
        MarvelMovies(
            Modifier,
            R.drawable.marvel,
            R.string.app_name,
            R.string.timeMovies,
            R.string.pointMovies,
            onClick = {}
        )
    }
}
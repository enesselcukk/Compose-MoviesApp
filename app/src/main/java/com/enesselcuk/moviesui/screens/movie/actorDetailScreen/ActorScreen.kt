package com.enesselcuk.moviesui.screens.movie.actorDetailScreen

import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.enesselcuk.moviesui.R
import com.enesselcuk.moviesui.screens.movie.actorDetailScreen.view.ActorMoviesItems
import com.enesselcuk.moviesui.screens.movie.actorDetailScreen.view.ActorMoviesTitle
import com.enesselcuk.moviesui.screens.movie.actorDetailScreen.view.ActorTvItems
import com.enesselcuk.moviesui.screens.movie.actorDetailScreen.view.ActorTvTitle
import com.enesselcuk.moviesui.source.model.response.ActorDetailResponse
import com.enesselcuk.moviesui.util.Constant.IMAGE_BASE
import com.enesselcuk.moviesui.util.dateCurrent

@Composable
fun ActorScreen(
    id: Int,
    viewModel: ActorViewModel = hiltViewModel(),
    isVisibleBottom: (item: Boolean) -> Unit,
    isVisibleTopBar: (item: Boolean) -> Unit,
    isVisibleTopBarBack: (item: Boolean) -> Unit,
    screenName: (name: String) -> Unit,
    itemClick: (id: Int?) -> Unit,
    isActionInTopBar : (isVisible:Boolean) -> Unit,
    itemTvClick: (id: Int?) -> Unit
) {

    isActionInTopBar.invoke(false)

    viewModel.getActor(id = id, language = "tr")
    viewModel.getActorMovie(id = id, language = "tr")
    viewModel.getActorTv(id = id, language = "tr")

    val data = viewModel.actorDetailFlow.collectAsStateWithLifecycle()
    val movieData = viewModel.actorMovieDetailFlow.collectAsStateWithLifecycle()
    val tvData = viewModel.actorTvDetailFlow.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {

        ActorScreenView(data.value)
        ActorMoviesTitle(id = stringResource(id = R.string.actor_movies), movies = {
            ActorMoviesItems(movieData.value, itemClick = { itemClick.invoke(it) })
        })
        ActorTvTitle(id = stringResource(id = R.string.actor_Tv), movies = {
            ActorTvItems(tvData.value, itemClick = { itemTvClick.invoke(it) })
        })
        isVisibleBottom.invoke(false)
        isVisibleTopBar.invoke(true)
        isVisibleTopBarBack.invoke(true)
        screenName.invoke("Actors")

    }

}

@Composable
private fun ActorScreenView(
    actorData: ActorDetailResponse?
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (image, name, acContent,birth,job,place_birth) = createRefs()

        AsyncImage(model = "$IMAGE_BASE${actorData?.profile_path}",
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(height = 240.dp, width = 200.dp)
                .clip(RoundedCornerShape(16.dp))
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, 3.dp)
                })

        Text(
            text = actorData?.name.orEmpty(), modifier = Modifier.constrainAs(name) {
                top.linkTo(image.top, 10.dp)
                start.linkTo(image.end,10.dp)
                end.linkTo(parent.end,3.dp)
            }, color = colorResource(id = R.color.black),
            fontWeight = FontWeight.Bold
        )

        Text(
            text = actorData?.birthday?.dateCurrent().orEmpty(), modifier = Modifier.constrainAs(birth) {
                top.linkTo(name.bottom, 3.dp)
                start.linkTo(image.end,10.dp)
                end.linkTo(parent.end,3.dp)
            }, color = colorResource(id = R.color.black),
            fontWeight = FontWeight.Bold
        )

        Text(
            text = actorData?.known_for_department.orEmpty(), modifier = Modifier.constrainAs(job) {
                top.linkTo(birth.bottom, 3.dp)
                start.linkTo(image.end,10.dp)
                end.linkTo(parent.end,3.dp)
            }, color = colorResource(id = R.color.black),
            fontWeight = FontWeight.Bold
        )

        Text(
            text = actorData?.place_of_birth.orEmpty(), modifier = Modifier.constrainAs(place_birth) {
                top.linkTo(job.bottom, 3.dp)
                start.linkTo(image.end,10.dp)
                end.linkTo(parent.end,3.dp)
            }, color = colorResource(id = R.color.black),
            fontWeight = FontWeight.Bold
        )

        ActorContent(content = actorData?.biography.orEmpty(),
            titleContent = "Biography",
            modifier = Modifier.constrainAs(acContent) {
                top.linkTo(image.bottom, 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ActorContent(content: String, titleContent: String, modifier: Modifier) {
    if (!content.isNullOrEmpty()) {
        ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp)),

            ) {
            val (title, icon, actContent) = createRefs()

            Text(text = titleContent, modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(actContent.top)
            })

            var expanded by remember { mutableStateOf(false) }

            Icon(painter = if (expanded) painterResource(id = R.drawable.arrow_up) else painterResource(
                id = R.drawable.arrow_down
            ), contentDescription = "", modifier = Modifier
                .constrainAs(icon) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(actContent.top)
                }
                .clickable { expanded = !expanded })

            AnimatedContent(modifier = Modifier
                .constrainAs(actContent) {
                    top.linkTo(title.bottom, 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(), targetState = expanded, transitionSpec = {
                fadeIn(
                    animationSpec = tween(
                        150, 150
                    )
                ) with fadeOut(animationSpec = tween(150)) using SizeTransform { initialSize, targetSize ->
                    if (targetState) {
                        keyframes {
                            IntSize(targetSize.width, initialSize.height) at 150
                            durationMillis = 300
                        }
                    } else {
                        keyframes {
                            IntSize(initialSize.width, targetSize.height) at 150
                            durationMillis = 300
                        }
                    }
                }
            }) { targetExpanded ->
                if (targetExpanded) {
                    Text(text = content)
                }
            }

        }
    }
}

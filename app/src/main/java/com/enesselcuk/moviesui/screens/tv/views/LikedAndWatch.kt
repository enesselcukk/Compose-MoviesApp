package com.enesselcuk.moviesui.screens.tv.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.enesselcuk.moviesui.R
import com.enesselcuk.moviesui.screens.tv.TvDetailViewModel
import com.enesselcuk.moviesui.source.model.response.TvDetailResponse


@Composable
fun LikedAndWatch(
    movies: TvDetailResponse?,
    isLiked: Boolean,
    onClick: (playerId: Int) -> Unit,
    viewModel: TvDetailViewModel
) {
    val ifLiked = rememberSaveable { mutableStateOf(false) }
    ifLiked.value = isLiked

    ConstraintLayout(
        modifier = Modifier.fillMaxWidth(),
    ) {

        val (preview, like, more) = createRefs()

        OutlinedButton(
            onClick = {onClick.invoke(movies?.id ?: 0)},
            modifier = Modifier
                .constrainAs(preview) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, 8.dp)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(like.start, 8.dp)
                },
            shape = RoundedCornerShape(20),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = stringResource(id = R.string.preview),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.padding(horizontal = 3.dp))
            Icon(
                painter = painterResource(id = R.drawable.play_circle),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = ""
            )
        }

        Icon(
            painter = if (ifLiked.value) painterResource(id = R.drawable.added) else painterResource(
                id = R.drawable.add
            ),
            contentDescription = "",
            modifier = Modifier
                .constrainAs(like) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(preview.end, 8.dp)
                }
                .clickable {
                    if (!isLiked) {
                        viewModel.setLikedTv(movies!!)
                        ifLiked.value = true
                    }
                },
            tint = MaterialTheme.colorScheme.primary
        )

        Icon(
            painter = painterResource(id = R.drawable.more),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = "",
            modifier = Modifier
                .constrainAs(more) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end, 6.dp)
                }
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape),
        )
    }
}
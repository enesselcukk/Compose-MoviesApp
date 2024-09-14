package com.enesselcuk.moviesui.ui.screen.searchScreen.views

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.enesselcuk.moviesui.ui.screen.searchScreen.SearchViewModel
import com.enesselcuk.moviesui.util.Constant.IMAGE_BASE



@Composable
fun Search(
    @StringRes titles: Int?,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel,
    content: @Composable () -> Unit

) {

    if (viewModel.isVisible.value) {
        Column(modifier = modifier) {
            ConstraintLayout(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 10.dp)
            ) {
                val (title) = createRefs()
                Text(
                    text = stringResource(id = titles!!),
                    modifier.constrainAs(title) {
                        top.linkTo(parent.top, 8.dp)
                        start.linkTo(parent.start, 8.dp)
                        bottom.linkTo(parent.bottom, 8.dp)
                        width = Dimension.fillToConstraints
                    },
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
            }
            content()
        }
    }

}

@Composable
private fun PeopleView(
    modifier: Modifier = Modifier,
    peopleImage: String? = null,
    peopleName: String? = null,
    click: (() -> Unit)? = null
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { click?.invoke() }
    ) {
        AsyncImage(
            model = "${IMAGE_BASE}$peopleImage",
            // painter = painterResource(id = R.drawable.marvel),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .clip(CircleShape)
                .size(80.dp, 80.dp),
        )
        Text(
            text = peopleName!!, modifier.padding(vertical = 5.dp, horizontal = 5.dp),
            style = TextStyle(fontSize = 9.sp)
        )
    }

}

@Composable
fun PeopleRow(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel,
    onCLick: (id: Int) -> Unit
) {
    val data = viewModel.searchPeopleFlow.collectAsStateWithLifecycle()

    LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        items(data.value?.results ?: emptyList()) {
            PeopleView(modifier, it.profile_path, it.name, click = { onCLick.invoke(it.id) })
        }
    }
}

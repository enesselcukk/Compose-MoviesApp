package com.enesselcuk.moviesui.screens.homeSceen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enesselcuk.moviesui.R
import com.enesselcuk.moviesui.ui.theme.MoviesUiTheme


// Most View
@Composable
fun HomeTopMoviesCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            text = "Most View",
            style = MaterialTheme.typography.h6,
        )

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Image(
                painterResource(R.drawable.marvel),
                contentDescription = null,
                modifier = modifier.fillMaxWidth()
            )
            Image(
                painter = painterResource(id = R.drawable.play),
                contentDescription = null,
                alignment = Alignment.BottomEnd,
                modifier = modifier.padding(start = 330.dp)

            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun HomeTopMoviesCardPreview() {
    MoviesUiTheme {
        HomeTopMoviesCard()
    }
}
package com.enesselcuk.moviesui.screens.movie.searchScreen.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enesselcuk.moviesui.R
import com.enesselcuk.moviesui.screens.movie.searchScreen.SearchViewModel

@Composable
fun SearchView(modifier: Modifier = Modifier, searchViewModel: SearchViewModel) {
    val language = "en"
    val page = 1

    val textValue = rememberSaveable { mutableStateOf("") }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            value = textValue.value,
            onValueChange = {
                textValue.value = it
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = ""
                )
            },
            placeholder = {
                Text(text = "Search")
            },
            trailingIcon = {
                if (textValue.value.isNotEmpty()) {
                    OutlinedButton(
                        modifier = modifier.padding(end = 5.dp),
                        onClick = {
                            searchViewModel.getSearchMovies(language = language, page = page, query = textValue.value)
                            searchViewModel.getSearchPeople(language = language, page = page, query = textValue.value)
                            textValue.value = ""
                        },
                        shape = RoundedCornerShape(12.dp),
                        elevation = ButtonDefaults.elevatedButtonElevation(2.dp)

                    ) {
                        Text(
                            text = stringResource(id = R.string.search),
                            fontSize = 11.sp
                        )
                    }
                }
            }
        )
    }


}

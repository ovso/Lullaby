/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ovso.whitenoise.ui.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BabyChangingStation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.ovso.whitenoise.R
import io.github.ovso.whitenoise.data.Lullaby
import io.github.ovso.whitenoise.data.LullabyRepo
import io.github.ovso.whitenoise.ui.theme.WhiteNoiseTheme
import java.util.*

@Composable
fun Home() {
    val lullabies = remember { LullabyRepo.getLullabies() }
    WhiteNoiseTheme {
        Scaffold(
            topBar = { AppBar() }
        ) { innerPadding ->
            HomeContent(innerPadding, lullabies)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeContent(
    innerPadding: PaddingValues,
    lullabies: List<Lullaby>
) {
    Column {
        Header(
            modifier = Modifier.padding(20.dp)
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            contentPadding = innerPadding,
        ) {
            items(lullabies) { item ->
                LullabyItem(item = item)
                Divider(startIndent = 72.dp)
            }
        }
    }
}

@Composable
private fun AppBar() {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Rounded.BabyChangingStation,
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        },
        title = {
            Text(
                text = stringResource(R.string.app_title)
            )
        },
        backgroundColor = MaterialTheme.colors.primarySurface
    )
}

@Composable
fun Header(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f),
        contentColor = MaterialTheme.colors.primary,
        modifier = modifier.semantics { heading() }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun Header(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.white_noise_baby_sleep),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun LullabyMetadata(
    lullaby: Lullaby,
    modifier: Modifier = Modifier
) {
    val divider = "  •  "
    val tagDivider = "  "
    val text = buildAnnotatedString {
        append(divider)
        append(divider)
        val tagStyle = MaterialTheme.typography.overline.toSpanStyle().copy(
            background = MaterialTheme.colors.primary.copy(alpha = 0.1f)
        )
        lullaby.tags.forEachIndexed { index, tag ->
            if (index != 0) {
                append(tagDivider)
            }
            withStyle(tagStyle) {
                append(" ${tag.uppercase(Locale.getDefault())} ")
            }
        }
    }
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            modifier = modifier
        )
    }
}

@Composable
fun LullabyItem(
    item: Lullaby,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .clickable(
                onClick = {
                    Log.d("LullabyItem2", "onClick()")
                }
            )
            .fillMaxWidth()
            .padding(bottom = 15.dp, top = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(item.imageThumbId),
            contentDescription = null,
            modifier = modifier.size(50.dp),
        )
        Text(
            text = item.title
        )
    }
}

/*
@Preview("LullabyItem2")
@Composable
fun LullabyItemPreview() {
    LullabyItem(
        Lullaby(
            1L, "브람스 자장가", "ㅋㅋ", "", R.drawable.ic__18_fish, setOf("aa")
        ),
        modifier = Modifier
    )
}
*/

@Preview("Home")
@Composable
private fun HomePreview() {
    Home()
}

/*
@Preview("AppBar")
@Composable
private fun AppBarPreview() {
    AppBar()
}
*/

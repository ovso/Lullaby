package io.github.ovso.lullaby.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding
import io.github.ovso.domain.Lullaby
import io.github.ovso.domain.Result
import io.github.ovso.lullaby.R
import io.github.ovso.lullaby.data.LullabyModel
import io.github.ovso.lullaby.data.toLullabyModel
import kotlin.math.max

class HomeContent(val content: @Composable () -> Unit)

@Composable
fun HomeScreen(
  content: HomeContent
) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
          )
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
      )
    }
  ) { innerPadding ->
    val screenModifier = Modifier.padding(innerPadding)
    HomeScreenContent(
      homeContent = content,
      modifier = screenModifier
    )
  }
}

@Composable
fun rememberHomeContent(
  homeViewModel: HomeViewModel,
): HomeContent {
  val uiState by homeViewModel.uiState.collectAsState()

  val homeContent = HomeContent {
    val selectedLullabies by homeViewModel.selectedLullaby.collectAsState()
    Items(
      result = uiState.result,
      selectedLullabies = selectedLullabies,
      onLullabySelect = { homeViewModel.toggleSelection(it) },
    )
  }
  return homeContent
}

@Composable
private fun HomeScreenContent(
  homeContent: HomeContent,
  modifier: Modifier = Modifier
) {
  Column(modifier) {
    Divider(
      color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f)
    )
    Box(modifier = Modifier.weight(1f)) {
      // display the current tab content which is a @Composable () -> Unit
      homeContent.content()
    }
  }
}

private val homeContainerModifier = Modifier
  .fillMaxWidth()
  .wrapContentWidth(Alignment.CenterHorizontally)
  .navigationBarsPadding(start = false, end = false)

@Composable
private fun Items(
  result: Result<List<Lullaby>>,
  selectedLullabies: Set<LullabyModel>,
  onLullabySelect: (LullabyModel) -> Unit,
) {
  Column(homeContainerModifier.verticalScroll(rememberScrollState())) {
    HomeAdaptiveContentLayout {
      when (result) {
        is Result.Error -> {

        }
        is Result.Loading -> {

        }
        is Result.Success -> {
          result.data.forEach { item ->
            LullabyItem(
              title = item.title,
              author = item.author,
              selected = selectedLullabies.contains(item.toLullabyModel()),
              onToggle = {
                onLullabySelect(item.toLullabyModel())
              },
            )
          }
        }
      }
    }
  }
}

@Composable
private fun LullabyItem(
  title: String,
  author: String,
  selected: Boolean,
  onToggle: (Boolean) -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(Modifier.padding(horizontal = 16.dp)) {
    Row(
      modifier = modifier.toggleable(
        value = selected,
        onValueChange = {
          onToggle(it)
        }
      ),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Image(
        painter = painterResource(R.drawable.musical_note),
        contentDescription = null, // decorative
        modifier = Modifier
          .size(52.dp)
          .padding(10.dp)
      )
      Column(modifier = Modifier.weight(1F)) {
        Text(
          text = title,
          modifier = Modifier,
          style = MaterialTheme.typography.subtitle1
        )
        Text(
          text = "- ".plus(author).plus(" -"),
          modifier = Modifier.padding(5.dp),
          style = MaterialTheme.typography.subtitle2
        )
      }
      Spacer(Modifier.weight(0.01f))
      SelectButton(selected = selected)
    }
    Divider(
      modifier = modifier.padding(),
      color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f)
    )
  }
}

@Composable
private fun HomeAdaptiveContentLayout(
  modifier: Modifier = Modifier,
  topPadding: Dp = 0.dp,
  itemSpacing: Dp = 4.dp,
  itemMaxWidth: Dp = 450.dp,
  multipleColumnsBreakPoint: Dp = 600.dp,
  content: @Composable () -> Unit,
) {
  Layout(modifier = modifier, content = content) { measurables, outerConstraints ->
    // Convert parameters to Px. Safe to do as `Layout` measure block runs in a `Density` scope
    val multipleColumnsBreakPointPx = multipleColumnsBreakPoint.roundToPx()
    val topPaddingPx = topPadding.roundToPx()
    val itemSpacingPx = itemSpacing.roundToPx()
    val itemMaxWidthPx = itemMaxWidth.roundToPx()

    // Number of columns to display on the screen. This is harcoded to 2 due to
    // the design mocks, but this logic could change in the future.
    val columns = if (outerConstraints.maxWidth < multipleColumnsBreakPointPx) 1 else 2
    // Max width for each item taking into account available space, spacing and `itemMaxWidth`
    val itemWidth = if (columns == 1) {
      outerConstraints.maxWidth
    } else {
      val maxWidthWithSpaces = outerConstraints.maxWidth - (columns - 1) * itemSpacingPx
      (maxWidthWithSpaces / columns).coerceIn(0, itemMaxWidthPx)
    }
    val itemConstraints = outerConstraints.copy(maxWidth = itemWidth)

    // Keep track of the height of each row to calculate the layout's final size
    val rowHeights = IntArray(measurables.size / columns + 1)
    // Measure elements with their maximum width and keep track of the height
    val placeables = measurables.mapIndexed { index, measureable ->
      val placeable = measureable.measure(itemConstraints)
      // Update the height for each row
      val row = index.floorDiv(columns)
      rowHeights[row] = max(rowHeights[row], placeable.height)
      placeable
    }

    // Calculate maxHeight of the Interests layout. Heights of the row + top padding
    val layoutHeight = topPaddingPx + rowHeights.sum()
    // Calculate maxWidth of the Interests layout
    val layoutWidth = itemWidth * columns + (itemSpacingPx * (columns - 1))

    // Lay out given the max width and height
    layout(
      width = outerConstraints.constrainWidth(layoutWidth),
      height = outerConstraints.constrainHeight(layoutHeight)
    ) {
      // Track the y co-ord we have placed children up to
      var yPosition = topPaddingPx
      // Split placeables in lists that don't exceed the number of columns
      // and place them taking into account their width and spacing
      placeables.chunked(columns).forEachIndexed { rowIndex, row ->
        var xPosition = 0
        row.forEach { placeable ->
          placeable.placeRelative(x = xPosition, y = yPosition)
          xPosition += placeable.width + itemSpacingPx
        }
        yPosition += rowHeights[rowIndex]
      }
    }
  }
}

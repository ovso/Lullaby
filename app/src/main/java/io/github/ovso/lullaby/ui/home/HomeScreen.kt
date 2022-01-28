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
import io.github.ovso.lullaby.R
import io.github.ovso.lullaby.data.LullabyModel
import kotlin.math.max

/**
 * TabContent for a single tab of the screen.
 *
 * This is intended to encapsulate a tab & it's content as a single object. It was added to avoid
 * passing several parameters per-tab from the stateful composable to the composable that displays
 * the current tab.
 *
 * @param content content of the tab, a composable that describes the content
 */
class HomeContent(val content: @Composable () -> Unit)

/**
 * Stateless interest screen displays the tabs specified in [content] adapting the UI to
 * different screen sizes.
 *
 * @param content (slot) the tabs and their content to display on this screen, must be a
 * non-empty list, tabs are displayed in the order specified by this list
 */
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

/**
 * Remembers the content for each tab on the Interests screen
 * gathering application data from [HomeViewModel]
 */
@Composable
fun rememberHomeContent(
  homeViewModel: HomeViewModel,
): HomeContent {
  // UiState of the InterestsScreen
  val uiState by homeViewModel.uiState.collectAsState()

  // Describe the screen sections here since each section needs 2 states and 1 event.
  // Pass them to the stateless InterestsScreen using a tabContent.
  val homeContent = HomeContent {
    val selectedLullabies by homeViewModel.selectedLullaby.collectAsState()
    Items(
      items = uiState.lullabies,
      selectedLullabies = selectedLullabies,
      onLullabySelect = { homeViewModel.toggleSelection(it) },
    )
  }
  return homeContent
}

/**
 * @param homeContent (slot) tabs and their content to display, must be a non-empty list, tabs are
 * displayed in the order of this list
 */
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

/**
 * Modifier for UI containers that show interests items
 */
private val homeContainerModifier = Modifier
  .fillMaxWidth()
  .wrapContentWidth(Alignment.CenterHorizontally)
  .navigationBarsPadding(start = false, end = false)

/**
 * Display a sectioned list of topics
 *
 * @param items (state) topics to display, grouped by sections
 * @param selectedLullabies (state) currently selected topics
 * @param onLullabySelect (event) request a topic+section selection be changed
 */
@Composable
private fun Items(
  items: List<LullabyModel>,
  selectedLullabies: Set<LullabyModel>,
  onLullabySelect: (LullabyModel) -> Unit,
) {
  Column(homeContainerModifier.verticalScroll(rememberScrollState())) {
    HomeAdaptiveContentLayout {
      items.forEach { item ->
        LullabyItem(
          title = item.title,
          selected = selectedLullabies.contains(item),
          onToggle = {
            onLullabySelect(item)
          },
        )
      }
    }
  }
}

@Composable
private fun LullabyItem(
  title: String,
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
      Text(
        text = title,
        modifier = Modifier
          .padding(16.dp)
          .weight(1f), // Break line if the title is too long
        style = MaterialTheme.typography.subtitle1
      )
      Spacer(Modifier.weight(0.01f))
      SelectButton(selected = selected)
    }
    Divider(
      modifier = modifier.padding(start = 72.dp),
      color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f)
    )
  }
}

/**
 * Custom layout for the Interests screen that places items on the screen given the available size.
 *
 * For example: Given a list of items (A, B, C, D, E) and a screen size that allows 2 columns,
 * the items will be displayed on the screen as follows:
 *     A B
 *     C D
 *     E
 */
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

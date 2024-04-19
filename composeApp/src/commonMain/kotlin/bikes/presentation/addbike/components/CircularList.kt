package bikes.presentation.addbike.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp

@Composable
fun CircularList(
    colorList: List<Color>,
    goToColor: Int,
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit
) {
    var startingIndex = 0
    if (goToColor > -1) {
        startingIndex = colorList.map { it.toArgb() }.indexOf(goToColor)
    }
    val listState = rememberLazyListState(startingIndex)
    var selectedIndex by remember {
        mutableStateOf(startingIndex)
    }
    LazyRow(
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(36.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(colorList.size, itemContent = {
            val index = it % colorList.size
            // item composable
            Canvas(modifier = Modifier
                .size(25.dp)
                .border(
                    border =
                    if (selectedIndex == index) {
                        BorderStroke(2.dp, White)
                    } else {
                        BorderStroke(width = 0.dp, color = colorList[index])
                    }, shape = CircleShape
                )
                .selectable(
                    selected = selectedIndex == index,
                    onClick = {
                        onItemClick(colorList[index].toArgb())
                        selectedIndex = index
                    }
                ), onDraw = {
                drawCircle(color = colorList[index])
            })
        })
    }
}
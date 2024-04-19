package bikes.presentation.addbike.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import ui.theme.Brown
import ui.theme.CoralBlue
import ui.theme.Green
import ui.theme.LightBlue
import ui.theme.LightBrown
import ui.theme.Orange
import ui.theme.Pink
import ui.theme.Red
import ui.theme.SimpleGray
import ui.theme.SkyBlue
import ui.theme.Yellow

@Composable
fun HorizontalColorPicker(
    colorList: List<Color> = listOf(
        SimpleGray, Green, Red, Yellow, SkyBlue, Orange, CoralBlue,
        LightBrown, Pink, LightBlue, Brown
    ),
    onColorClicked: (clickedClicked: Int) -> Unit = {},
    goToColor: Int = Brown.toArgb(),
    modifier: Modifier = Modifier
) {
    CircularList(
        colorList = colorList,
        goToColor = goToColor,
        onItemClick = onColorClicked,
        modifier = Modifier.fillMaxWidth()
    )
}
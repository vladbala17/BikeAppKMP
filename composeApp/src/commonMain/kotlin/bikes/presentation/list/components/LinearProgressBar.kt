package bikes.presentation.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.loading_bolt
import bikeappkmp.composeapp.generated.resources.loading_circle
import bikeappkmp.composeapp.generated.resources.loading_wrench
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.theme.Blue
import ui.theme.GrayBlue


@OptIn(ExperimentalResourceApi::class)
@Composable
fun LinearProgressBar(
    progress: Float = 0.9f,
    progressColor: Color = Blue,
    backgroundColor: Color = GrayBlue,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Transparent)
            .height(25.dp),
    ) {
        Box(
            modifier = Modifier
                .background(backgroundColor)
                .height(6.dp)
                .fillMaxWidth()
                .align(Alignment.Center)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(progressColor)
                    .height(6.dp)
                    .fillMaxWidth(progress)
            )
            Image(
                painter = painterResource(Res.drawable.loading_wrench),
                contentDescription = "loading wrench",
                modifier = Modifier
                    .fillMaxHeight()
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.loading_circle),
                contentDescription = "loading cercle",
                modifier = Modifier.fillMaxHeight()
            )
            Image(
                painter = painterResource(Res.drawable.loading_bolt),
                contentDescription = "loading bolt",
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}
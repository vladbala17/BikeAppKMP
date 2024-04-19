package bikes.presentation.addbike.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import bikes.domain.model.BikeType
import bikes.domain.model.PagerBike
import kotlin.math.absoluteValue

@ExperimentalFoundationApi
@Composable
fun BikesPager(
    bikesList: List<PagerBike>,
    bikeName: String = BikeType.Electric.type,
    onPageChanged: (Int) -> Unit = {}
) {
    val pagerState =
        rememberPagerState(pageCount = { bikesList.size }, initialPage = bikesList.size / 2)
    LaunchedEffect(key1 = pagerState, block = {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onPageChanged(page)
        }
    })
    Column(
        modifier = Modifier.wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(start = 80.dp, end = 80.dp)
        ) { page ->
            BikeFactory(
                modifier = Modifier.graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue


                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                },
                bodyColor = Color(bikesList[page].color),
                bodyType = bikesList[page].type
            )
        }
        Text(text = bikeName, color = White)
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp, top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(4) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) MaterialTheme.colors.primary else MaterialTheme.colors.onPrimary
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }

    }
}
package rides.presentation.addride.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerticalPager(
    itemsList: List<String>,
    onPageChanged: (Int) -> Unit = {}
) {

    val pagerState = rememberPagerState(pageCount = { itemsList.size })

    LaunchedEffect(key1 = pagerState, block = {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onPageChanged(page)
        }
    })

    VerticalPager(
        state = pagerState,
        pageSize = PageSize.Fixed(40.dp),
        contentPadding = PaddingValues(top = 40.dp, bottom = 40.dp),
        modifier = Modifier.height(100.dp)
    ) { page ->
        Text(
            itemsList[page],
            style = MaterialTheme.typography.headlineMedium,
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
            })
    }

}
package com.vlad.bikegarage.bikes.presentation.addbikes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.vlad.bikegarage.R

@Composable
fun MTBBike(bodyColor: Color = Color.Red, modifier: Modifier) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.bike_mtb_big_wheels),
            contentDescription = stringResource(R.string.mtb_bike_name),
            modifier = Modifier.aspectRatio(16f / 9f),
        )
        Image(
            painter = painterResource(id = R.drawable.bike_mtb_middle),
            contentDescription = stringResource(R.string.mtb_bike_name),
            colorFilter = ColorFilter.tint(bodyColor),
            modifier = Modifier.aspectRatio(16f / 9f)
        )
        Image(
            painter = painterResource(id = R.drawable.bike_mtb_over),
            contentDescription = stringResource(R.string.mtb_bike_name),
            modifier = Modifier.aspectRatio(16f / 9f)
        )
    }
}
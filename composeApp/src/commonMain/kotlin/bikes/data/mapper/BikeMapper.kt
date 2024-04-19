package com.vlad.bikegarage.bikes.data.local.mapper

import bikes.domain.model.Bike
import database.BikeEntity

fun Bike.toBikeEntity(): BikeEntity {
    return BikeEntity(
        bikeId = id,
        name = name,
        wheelSize = wheelSize,
        serviceIn = serviceIn,
        bikeType = bikeType.type,
        isDefault = if (isDefault) {
            1
        } else {
            0
        },
        bikeColor = bikeColor
    )
}
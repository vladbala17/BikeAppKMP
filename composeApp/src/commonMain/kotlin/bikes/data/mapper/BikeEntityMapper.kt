package com.vlad.bikegarage.bikes.data.local.mapper

import bikes.domain.model.Bike
import bikes.domain.model.BikeType
import database.BikeEntity

fun BikeEntity.toBike(): Bike {
    return Bike(
        id = bikeId,
        name = name,
        wheelSize = wheelSize,
        serviceIn = serviceIn,
        isDefault = if (isDefault > 0) {
            true
        } else {
            false
        },
        bikeType = BikeType.fromString(bikeType),
        bikeColor = bikeColor
    )
}
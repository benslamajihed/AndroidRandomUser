package com.jbs.ttechnique.data.model

import androidx.room.Embedded

data class Location(
    @Embedded val street: Street = Street(),
    val city: String = "", // New York
    val state: String = "", // New York
    val country: String = "", // United States
    val postcode: String = "", // null
    @Embedded val coordinates: Coordinates = Coordinates(),
    @Embedded val timezone: Timezone = Timezone()
)
package com.jbs.ttechnique.data.model

import androidx.room.Entity

@Entity(primaryKeys = arrayOf("number", "name"))
data class Street(
    val number: Int = 0, // 6057
    val nameStreet: String = "" // Avondale Ave
)
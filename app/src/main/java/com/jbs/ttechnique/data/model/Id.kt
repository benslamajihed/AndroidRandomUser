package com.jbs.ttechnique.data.model


import androidx.annotation.NonNull
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

//@Entity(tableName = "userId", primaryKeys = arrayOf("name", "value"))
@Entity
data class Id(
    @SerializedName("name")
    var nameId: String = "", // SSN
    @NonNull
    var value: String = "" // 557-48-8854
)
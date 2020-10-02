package com.jbs.ttechnique.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("results")
    var results: List<User> = listOf(),
    var info: Info = Info()
)
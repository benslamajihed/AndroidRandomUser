package com.jbs.ttechnique.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User(
    var gender: String = "", // male
    @Embedded var nameUser: Name = Name(),
    @Embedded var location: Location = Location(),
    var email: String = "", // karl.johnson@example.com
    @Embedded var login: Login = Login(),
    @Embedded var dob: Dob = Dob(),
    @Embedded var registered: Registered = Registered(),
    var phone: String = "", // (068)-320-4900
    var cell: String = "", // (476)-843-3163
    @PrimaryKey
    @SerializedName("id")
    @Embedded var idUser: Id = Id(),
    @Embedded var picture: Picture = Picture(),
    var nat: String = "" // US
)
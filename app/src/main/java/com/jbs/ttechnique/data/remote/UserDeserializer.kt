package com.jbs.ttechnique.data.remote

import com.google.gson.*
import com.jbs.ttechnique.data.model.*
import java.lang.reflect.Type

class UserDeserializer : JsonDeserializer<User> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): User {
        val user = User()
        val gson = Gson()
        json?.asJsonObject?.let {
            context?.apply {
                val id = deserialize(it.getAsJsonObject("id"), Id::class.java) as Id

                user.idUser = id
                user.email = it.get("email")?.asString!!
                user.cell = it.get("cell")?.asString!!
                user.gender = it.get("gender")?.asString!!
                user.phone = it.get("phone")?.asString!!
                user.nat = it.get("nat")?.asString!!

                user.dob = gson.fromJson(it.get("dob"), Dob::class.java) as Dob // TODO date
                user.location = gson.fromJson(it.get("location"), Location::class.java) as Location
                user.nameUser = gson.fromJson(it.get("name"), Name::class.java) as Name
                user.login = gson.fromJson(it.get("login"), Login::class.java) as Login
                user.registered = gson.fromJson(it.get("registered"), Registered::class.java) as Registered //TODO
                user.picture = gson.fromJson(it.get("picture"), Picture::class.java) as Picture

            }
        }
        return user
    }
}
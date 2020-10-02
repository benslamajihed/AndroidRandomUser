package com.jbs.ttechnique.data.remote

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.jbs.ttechnique.data.model.Info
import com.jbs.ttechnique.data.model.User
import com.jbs.ttechnique.data.model.UserResponse
import com.jbs.ttechnique.utils.USER_ID_INVALID
import timber.log.Timber
import java.lang.reflect.Type


class UserListDeserializer : JsonDeserializer<UserResponse> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): UserResponse {

        val usersResponse: UserResponse = UserResponse()
        val userList: MutableList<User> = ArrayList()
        val jsonObject = json?.asJsonObject
        jsonObject?.let {

        }
        val results = jsonObject?.getAsJsonArray("results")
        results?.forEach {
            val user = context?.deserialize(it, User::class.java) as User
            if (user.idUser.value != USER_ID_INVALID) {
                userList.add(user)
            }
        }

        val info = context?.deserialize(jsonObject?.getAsJsonObject("info"), Info::class.java) as Info

        Timber.d(jsonObject.toString())
        usersResponse.info = info
        usersResponse.results = userList

        return usersResponse
    }
}
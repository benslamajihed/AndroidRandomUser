package com.jbs.ttechnique.data.remote

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.jbs.ttechnique.data.model.Id
import com.jbs.ttechnique.utils.USER_ID_INVALID
import java.lang.reflect.Type

class IdDeserializer : JsonDeserializer<Id> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Id {
        val id: Id = Id()
        val value: String?
        val jsonObject = json?.asJsonObject
        val name: String? = jsonObject?.get("name")?.asString


        val element = jsonObject?.get("value")

        if (element == null || element.isJsonNull || element.asString.isNullOrBlank()) {
            value = USER_ID_INVALID
        } else {
            value = element.asString
        }

        id.nameId = if (name.isNullOrBlank()) "" else name
        id.value = if (value.isNullOrBlank()) USER_ID_INVALID else value

        return id
    }
}
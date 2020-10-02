package com.jbs.ttechnique.data.remote

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import timber.log.Timber
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class DateDeserializer : JsonDeserializer<Date?> {
    @Throws(JsonParseException::class)
    override fun deserialize(element: JsonElement, arg1: Type?, arg2: JsonDeserializationContext?): Date? {
        val date = element.asString
        val formatter = SimpleDateFormat("M/d/yy hh:mm a")
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        return try {
            formatter.parse(date)
        } catch (e: ParseException) {
            Timber.e(e)
            null
        }
    }
}
package com.kong.baselibraryapp

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

class GsonUtilK {
    companion object {
        val gson = GsonBuilder().registerTypeAdapterFactory(
            NullStringToEmptyAdapterFactory()
        ).create()
    }

    class NullStringToEmptyAdapterFactory : TypeAdapterFactory {
        override fun <T : Any> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {
            val rawType = type.rawType as Class<T>
            return if (rawType != String::class.java) {
                null
            } else (StringNullAdapter() as TypeAdapter<T>)
        }
    }

    class StringNullAdapter : TypeAdapter<String>() {
        override fun write(writer: JsonWriter, value: String?) {
            if (value == null) {
                writer.nullValue()
                return
            }
            writer.value(value)
        }

        @Suppress("UNREACHABLE_CODE")
        override fun read(reader: JsonReader): String {
            TODO("Not yet implemented")
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull()
                return ""
            }
            return reader.nextString()
        }
    }

}
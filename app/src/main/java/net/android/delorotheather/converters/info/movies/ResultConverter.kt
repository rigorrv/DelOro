package net.android.delorotheather.converters.info.movies

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.android.delorotheather.model.movies.Result


//info
class ResultConverter {

    @TypeConverter
    fun stringToResult(json: String): List<Result>? {
        val gson = Gson()
        val type = object : TypeToken<List<Result>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun ResultToString(Result: List<Result>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Result>>() {}.type
        return gson.toJson(Result, type)
    }
}
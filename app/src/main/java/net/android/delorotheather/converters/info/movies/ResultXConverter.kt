package net.android.delorotheather.converters.info.movies

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.android.delorotheather.model.movies.ResultX

//info
class ResultXConverter {

    @TypeConverter
    fun stringToResult(json: String): List<ResultX>? {
        val gson = Gson()
        val type = object : TypeToken<List<ResultX>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun ResultToString(ResultX: List<ResultX>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ResultX>>() {}.type
        return gson.toJson(ResultX, type)
    }
}
package net.android.delorotheather.converters.info.movies


import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.android.delorotheather.model.movies.ResultXX


//info
class ResultXXConverter {


    @TypeConverter
    fun stringToResult(json: String): List<ResultXX>? {
        val gson = Gson()
        val type = object : TypeToken<List<ResultXX>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun ResultToString(ResultXX: List<ResultXX>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ResultXX>>() {}.type
        return gson.toJson(ResultXX, type)
    }
}
package net.android.delorotheather.converters.info.info

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.android.delorotheather.model.info.Movies

class MoviesConverter {


    @TypeConverter
    fun stringToMovies(json: String): Movies? {
        val gson = Gson()
        val type = object : TypeToken<Movies>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun MoviesToString(Movies: Movies): String {
        val gson = Gson()
        val type = object : TypeToken<Movies>() {}.type
        return gson.toJson(Movies, type)
    }

}
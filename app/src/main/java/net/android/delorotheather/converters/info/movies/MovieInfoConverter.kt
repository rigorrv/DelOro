package net.android.delorotheather.converters.info.movies

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.android.delorotheather.model.movies.MovieInfo

//Info
class MovieInfoConverter {


    @TypeConverter
    fun stringToMovieInfo(json: String): MovieInfo? {
        val gson = Gson()
        val type = object : TypeToken<MovieInfo>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun MovieInfoToString(MovieInfo: MovieInfo): String {
        val gson = Gson()
        val type = object : TypeToken<MovieInfo>() {}.type
        return gson.toJson(MovieInfo, type)
    }
}
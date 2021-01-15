package net.android.delorotheather.converters.info.movies

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.android.delorotheather.model.movies.AuthorDetails

//info
class AuthorDetailsConverter {


    @TypeConverter
    fun stringToAuthorDetails(json: String): AuthorDetails? {
        val gson = Gson()
        val type = object : TypeToken<AuthorDetails>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun AuthorDetailsToString(AuthorDetails: AuthorDetails): String {
        val gson = Gson()
        val type = object : TypeToken<AuthorDetails>() {}.type
        return gson.toJson(AuthorDetails, type)
    }
}
package net.android.delorotheather.converters.info.info

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.android.delorotheather.model.info.Home

class HomeConverter {


    @TypeConverter
    fun stringToHome(json: String): Home? {
        val gson = Gson()
        val type = object : TypeToken<Home>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun HomeToString(Home: Home): String {
        val gson = Gson()
        val type = object : TypeToken<Home>() {}.type
        return gson.toJson(Home, type)
    }

}
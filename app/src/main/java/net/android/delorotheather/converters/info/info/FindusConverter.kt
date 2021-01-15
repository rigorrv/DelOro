package net.android.delorotheather.converters.info.info

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.android.delorotheather.model.info.Findus

class FindusConverter {


    @TypeConverter
    fun stringToFindus(json: String): Findus? {
        val gson = Gson()
        val type = object : TypeToken<Findus>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun FindusToString(Findus: Findus): String {
        val gson = Gson()
        val type = object : TypeToken<Findus>() {}.type
        return gson.toJson(Findus, type)
    }

}
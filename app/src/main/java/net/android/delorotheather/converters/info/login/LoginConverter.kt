package net.android.delorotheather.converters.info.login

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.android.delorotheather.model.login.Login

class LoginConverter {

    @TypeConverter
    fun fromLoginList(countryLang: List<Login?>?): String? {
        val type = object : TypeToken<List<Login?>?>() {}.type
        return Gson().toJson(countryLang, type)
    }

    @TypeConverter
    fun toLoginList(countryLangString: String?): List<Login>? {
        val type = object : TypeToken<List<Login?>?>() {}.type
        return Gson().fromJson<List<Login>>(countryLangString, type)
    }


    @TypeConverter
    fun stringListToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToStringList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}
package net.android.delorotheather.converters.info.info

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.android.delorotheather.model.info.Login

class LoginInfoConverter {


    @TypeConverter
    fun stringToLogin(json: String): Login? {
        val gson = Gson()
        val type = object : TypeToken<Login>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun LoginToString(Login: Login): String {
        val gson = Gson()
        val type = object : TypeToken<Login>() {}.type
        return gson.toJson(Login, type)
    }

}
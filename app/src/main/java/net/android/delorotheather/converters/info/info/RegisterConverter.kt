package net.android.delorotheather.converters.info.info

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.android.delorotheather.model.info.Register

class RegisterConverter {


    @TypeConverter
    fun stringToRegister(json: String): Register? {
        val gson = Gson()
        val type = object : TypeToken<Register>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun RegisterToString(Register: Register): String {
        val gson = Gson()
        val type = object : TypeToken<Register>() {}.type
        return gson.toJson(Register, type)
    }

}
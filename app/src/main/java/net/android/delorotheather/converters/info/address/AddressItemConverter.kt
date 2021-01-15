package net.android.delorotheather.converters.info.address

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.android.delorotheather.model.address.AddressJsonItem

class AddressItemConverter {

    @TypeConverter
    fun stringToComments(json: String): List<AddressJsonItem>? {
        val gson = Gson()
        val type = object : TypeToken<List<AddressJsonItem>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun CommentsToString(address: List<AddressJsonItem>): String {
        val gson = Gson()
        val type = object : TypeToken<List<AddressJsonItem>>() {}.type
        return gson.toJson(address, type)
    }
}
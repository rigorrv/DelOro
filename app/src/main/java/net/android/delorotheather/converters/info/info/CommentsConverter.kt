package net.android.delorotheather.converters.info.info

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.android.delorotheather.model.info.Comments

class CommentsConverter {


    @TypeConverter
    fun stringToComments(json: String): Comments? {
        val gson = Gson()
        val type = object : TypeToken<Comments>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun CommentsToString(Comments: Comments): String {
        val gson = Gson()
        val type = object : TypeToken<Comments>() {}.type
        return gson.toJson(Comments, type)
    }

}
package net.android.delorotheather.converters.info.user

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.android.delorotheather.model.users.UserItem

class UserItemConverter {


    @TypeConverter
    fun stringToUserItem(json: String): Array<UserItem>? {
        val gson = Gson()
        val type = object : TypeToken<Array<UserItem>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun UserItemToString(UserItem: UserItem): String {
        val gson = Gson()
        val type = object : TypeToken<Array<Array<UserItem>>>() {}.type
        return gson.toJson(UserItem, type)
    }
}
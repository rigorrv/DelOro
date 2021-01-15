package net.android.delorotheather.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import net.android.delorotheather.converters.info.address.AddressItemConverter
import net.android.delorotheather.converters.info.info.*
import net.android.delorotheather.converters.info.movies.*
import net.android.delorotheather.converters.info.user.UserConverter
import net.android.delorotheather.model.address.AddressJsonItem
import net.android.delorotheather.model.info.JsonInfo
import net.android.delorotheather.model.movies.CommentsMovie
import net.android.delorotheather.model.movies.FindMovie
import net.android.delorotheather.model.movies.MoviewTopList
import net.android.delorotheather.model.users.UserItem


@Database(
    entities = [JsonInfo::class, MoviewTopList::class, CommentsMovie::class,
        FindMovie::class, UserItem::class, AddressJsonItem::class],
    version = 1
)
@TypeConverters(
    //Info API

    CommentsConverter::class,
    ContactConverter::class,
    FindusConverter::class,
    HomeConverter::class,
    LoginInfoConverter::class,
    MoviesConverter::class,
    RegisterConverter::class,

    //Movie converter
    ResultConverter::class,
    ResultXConverter::class,
    ResultXXConverter::class,

    //Login API
    UserConverter::class,

    AddressItemConverter::class

)
abstract class AppDataBase : RoomDatabase() {
    abstract fun postDao(): PostDao
}